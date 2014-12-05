package donnu.zolotarev.SpaceShip.Bullets;

import donnu.zolotarev.SpaceShip.AI.SpriteAI;
import donnu.zolotarev.SpaceShip.GameState.IAmDie;
import donnu.zolotarev.SpaceShip.GameState.IHeroDieListener;
import donnu.zolotarev.SpaceShip.Scenes.BaseGameScene;
import donnu.zolotarev.SpaceShip.Scenes.InfinityGameScene;
import donnu.zolotarev.SpaceShip.Textures.MusicLoader;
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

import java.util.ArrayList;

public abstract class BaseBullet implements ICollisionObject, IHaveCoords {

    public static final int TYPE_SIMPLE_BULLET = 0;
    public static final int TYPE_ROCKET = TYPE_SIMPLE_BULLET+1;
    public static final int TYPE_ROCKET_AUTO = TYPE_ROCKET+1;

    protected static BaseGameScene main;
    private static ObjectCollisionController bulletController;
    protected static ObjectCollisionController enemyController;
    private static Hero hero;

    protected static IAmDie iAmDie;
    private static IHeroDieListener dieListener;
    protected static MultiPool bulletsPool;

    protected SpriteAI sprite;
    private PhysicsHandler physicsHandler;

    private int DEFAULT_SPEED;
    private int DEFAULT_DAMAGE;
    protected float DEFAULT_ROTATE_ANGLE;
    private int damage;
    private boolean targetUnit;
    private float hw;
    private float hh;

    protected WaySpecifications waySpecifications;
    private float r;
    protected final int nameHash;

    public BaseBullet(int nameHash) {
        this.nameHash = nameHash;
        waySpecifications = new WaySpecifications();
    }

    public static void setDieListener(IHeroDieListener listener){
        dieListener = listener;
    }

    public static void setUnitDieListener(IAmDie iAmDie){
        BaseBullet.iAmDie = iAmDie;
    }

    public static void clearClass(){
      //  main = null;
        hero = null;
        bulletController = null;
        enemyController = null;
        bulletsPool = null;
        main = null;
        bulletController = null;
        enemyController = null;
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

    public void init(float x, float y, float direction, int bullitType, boolean unitTarget,
            IWeaponModificator[] weaponModificator){
        init(x, y, direction, bullitType, unitTarget, weaponModificator,0);
    }

    public void init(float x, float y, float direction, int bullitType, boolean unitTarget,
            IWeaponModificator[] weaponModificator, int bulletFrameNumber) {
        sprite.setCurrentTileIndex(bulletFrameNumber);
        sprite.setRotation(direction);
        sprite.setIgnoreUpdate(false);
        sprite.setVisible(true);
        bulletController.add(this);
        this.targetUnit = unitTarget;

        hw = sprite.getWidth()/2;
        hh = sprite.getHeight()/2;
        r = Math.min(hw,hh);
        r *= r;
        if (!waySpecifications.isUsed()){
            waySpecifications.setAll(DEFAULT_SPEED, DEFAULT_ROTATE_ANGLE);
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

        MusicLoader.playFire();
    }

    protected void attachToScene() {
        BaseGameScene scene = InfinityGameScene.getActiveScene();
        scene.attachChild(sprite);
        sprite.setZIndex(0);
        scene.sortChildren();
    }

    protected void createSettings() {

        physicsHandler = new PhysicsHandler(sprite);
        sprite.registerUpdateHandler(physicsHandler);
        sprite.setIgnoreUpdate(true);
    }

    @Override
    public void destroy(Boolean withAnimate){
        sprite.setVisible(false);
        sprite.setIgnoreUpdate(true);
        bulletController.remove(this);
        waySpecifications.setUsed(false);

        if (nameHash == SimpleBullet.NAME_HASH){
            bulletsPool.recyclePoolItem(TYPE_SIMPLE_BULLET,(SimpleBullet)this);
        }else  if (nameHash == Rocket.NAME_HASH){
            bulletsPool.recyclePoolItem(TYPE_ROCKET,(Rocket)this);
        }else {
            throw new RuntimeException("!!");
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
      /*  Iterator<BaseUnit> col = enemyController.haveCollision(this);
        while (col.hasNext()){*/
        ArrayList<BaseUnit> objects = enemyController.haveCollision(this);
        for (int i = objects.size()-1; 0<=i;i--){
            BaseUnit unit = objects.get(i);
            if (unit.addDamageAndCheckDeath(getDamage())){
                objects.remove(unit);
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
        //MusicLoader.getFire().play();
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

    @Override
    public float getRadiusSqr() {
        return r;
    }
}
