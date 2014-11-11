package donnu.zolotarev.SpaceShip.Bullets;

import donnu.zolotarev.SpaceShip.AI.SpriteAI;
import donnu.zolotarev.SpaceShip.GameState.IAmDie;
import donnu.zolotarev.SpaceShip.GameState.IHeroDieListener;
import donnu.zolotarev.SpaceShip.Scenes.BaseGameScene;
import donnu.zolotarev.SpaceShip.Scenes.InfinityGameScene;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Units.Hero;
import donnu.zolotarev.SpaceShip.Units.WaySpecifications;
import donnu.zolotarev.SpaceShip.Utils.ICollisionObject;
import donnu.zolotarev.SpaceShip.Utils.IHaveCoords;
import donnu.zolotarev.SpaceShip.Utils.ObjectCollisionController;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import donnu.zolotarev.SpaceShip.Weapons.Modificator.IWeaponModificator;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.adt.pool.GenericPool;
import org.andengine.util.adt.pool.MultiPool;

import java.util.Iterator;

public abstract class BaseBullet implements ICollisionObject, IHaveCoords {

    public static final int TYPE_SIMPLE_BULLET = 0;
    public static final int TYPE_ROCKET = TYPE_SIMPLE_BULLET+1;
    public static final int TYPE_ROCKET_AUTO = TYPE_ROCKET+1;
    protected static BaseGameScene main;
    private static ObjectCollisionController bulletController;
    protected static ObjectCollisionController enemyController;
    private static Hero hero;

    private static IHeroDieListener dieListener;

    protected SpriteAI sprite;
    private PhysicsHandler physicsHandler;
    protected static MultiPool bulletsPool;

    private int DEFAULT_SPEED;
    private int DEFAULT_DAMAGE;
    protected float DEFAULT_ROTATE_ANGLE;
    private int damage;
    private boolean targetUnit;
    private float hw;
    private float hh;
    protected static IAmDie iAmDie;

    protected WaySpecifications waySpecifications;

    public static void setDieListener(IHeroDieListener listener){
        dieListener = listener;
    }

    public static void setUnitDieListener(IAmDie iAmDie){
        BaseBullet.iAmDie = iAmDie;
    }

    public static void resetPool(){
        bulletsPool = null;
    }

    protected static void registredPool(Class bulletBase,GenericPool genericPool){
        if (bulletsPool == null){
            bulletsPool = new MultiPool();
            main = InfinityGameScene.getActiveScene();
            bulletController = main.getBulletController();
            enemyController = main.getEnemyController();
            hero = main.getHero();
        }
        if (bulletBase.getSimpleName().equals(SimpleBullet.class.getSimpleName())){
            bulletsPool.registerPool(TYPE_SIMPLE_BULLET ,genericPool);
        }else if (bulletBase.getSimpleName().equals(Rocket.class.getSimpleName())){
            bulletsPool.registerPool(TYPE_ROCKET ,genericPool);
        }
    }

    protected void settings(){
        createSettings();
        attachToScene();
    }

    public void init(float x, float y, float direction, int bullitType, boolean unitTarget, IWeaponModificator[] weaponModificator) {

        sprite.setRotation(direction);
        sprite.setIgnoreUpdate(false);
        sprite.setVisible(true);
        bulletController.add(this);
        this.targetUnit = unitTarget;

        hw = sprite.getWidth()/2;
        hh = sprite.getHeight()/2;
        if (waySpecifications == null){
            waySpecifications = new WaySpecifications(DEFAULT_SPEED, DEFAULT_ROTATE_ANGLE);
        }
        sprite.start(waySpecifications);
        sprite.setPosition(x - hw, y - hh);

        damage = DEFAULT_DAMAGE;
        if (weaponModificator != null){
            for (IWeaponModificator m: weaponModificator) {
                switch (m.getTarget()){
                    case DAMAGE:
                        damage = (int)m.use(damage);
                        break;
                }
            }
        }
        this.damage = (int) Utils.random(damage*0.8f,damage*1.2f) ;
    }

    protected void attachToScene() {
        BaseGameScene scene = InfinityGameScene.getActiveScene();
        scene.attachChild(sprite);
        sprite.setZIndex(1);
        scene.sortChildren();
    }

    protected void createSettings() {

        physicsHandler = new PhysicsHandler(sprite);
        sprite.registerUpdateHandler(physicsHandler);
        sprite.setIgnoreUpdate(true);
    }

    @Override
    public  void destroy(Boolean withAnimate){
        sprite.setVisible(false);
        sprite.setIgnoreUpdate(true);
        bulletController.remove(this);
        waySpecifications = null;
        if (getClass().getSimpleName().equals(SimpleBullet.class.getSimpleName())){
            bulletsPool.recyclePoolItem(TYPE_SIMPLE_BULLET,(SimpleBullet)this);
        }
    }

    @Override
    public Sprite getShape() {
        return sprite;
    }

    public int getDamage(){
      return damage;
    }

    protected  void initCharacteristics(int speed, int damage, float angle){
        this.DEFAULT_DAMAGE = damage ;
        this.DEFAULT_SPEED = speed;
        DEFAULT_ROTATE_ANGLE = angle;
    }

    protected void checkHit() {
       if(targetUnit){
           checkHitUnit();
       }else{
           checkHitHero();
       }
    }

    protected void checkHitHero() {

        if (hero.checkHit(this)){
            if (hero.addDamageAndCheckDeath(getDamage()) && hero.isAlive()){
                dieListener.heroDie();
                hero.destroy(true);
            }
            destroy(true);
        }
    }

    private void checkHitUnit() {
        Iterator<BaseUnit> col = enemyController.haveCollision(this);
        while (col.hasNext()){
            BaseUnit unit = col.next();
            if (unit.addDamageAndCheckDeath(getDamage())){
                col.remove();
                unit.destroy(true);
                if (iAmDie != null){
                    iAmDie.destroyed(unit);
                }
            }
            destroy(true);
        }
    }

    public static BaseBullet getBullet(int type) {
        if (type == TYPE_ROCKET_AUTO){
            type = TYPE_ROCKET;
        }
        return ((BaseBullet)bulletsPool.obtainPoolItem(type));
    }

    @Override
    public float getCenterX() {
        return sprite.getX() + hw;
    }

    @Override
    public float getCenterY() {
        return sprite.getY() + hh;
    }

    @Override
    public boolean checkHit(IHaveCoords object) {
        return false;
    }

    public static void initPool() {
        SimpleBullet.poolInit();
        Rocket.poolInit();
    }

    public int getDefaultSpeed() {
        return DEFAULT_SPEED;
    }

    public int getDefaultDamage() {
        return DEFAULT_DAMAGE;
    }

}
