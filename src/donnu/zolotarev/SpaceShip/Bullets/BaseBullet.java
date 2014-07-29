package donnu.zolotarev.SpaceShip.Bullets;

import donnu.zolotarev.SpaceShip.GameState.IHeroDieListener;
import donnu.zolotarev.SpaceShip.Scenes.MainScene;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Units.Hero;
import donnu.zolotarev.SpaceShip.Utils.ICollisionObject;
import donnu.zolotarev.SpaceShip.Utils.IHaveCoords;
import donnu.zolotarev.SpaceShip.Utils.ObjectController;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.adt.pool.GenericPool;
import org.andengine.util.adt.pool.MultiPool;
import org.andengine.util.color.Color;

import java.util.Iterator;

public abstract class BaseBullet implements ICollisionObject, IHaveCoords {

    public static final int TYPE_SIMPLE_BULLET = 0;
    public static final int TYPE_SIMPLE_BULLET_2 = TYPE_SIMPLE_BULLET + 1;
    protected static MainScene main;
    private static ObjectController bulletController;
    private static ObjectController enemyController;
    private static Hero hero;

    private static IHeroDieListener dieListener;

    protected Sprite sprite;
    private PhysicsHandler physicsHandler;
    protected static MultiPool bulletsPool;

    private int DEFAULT_SPEED;
    private int damage;
    private boolean targetUnit;
    private float hw;
    private float hh;

    public static void setDieListener(IHeroDieListener listener){
        dieListener = listener;
    }

    public static void resetPool(){
        bulletsPool = null;
    }

    protected static void registredPool(Class bulletBase,GenericPool genericPool){
        if (bulletsPool == null){
            bulletsPool = new MultiPool();
            main = MainScene.getActiveScene();
            bulletController = main.getBulletController();
            enemyController = main.getEnemyController();
            hero = main.getHero();
        }
        if (bulletBase.getSimpleName().equals(SimpleBullet.class.getSimpleName())){
            bulletsPool.registerPool(TYPE_SIMPLE_BULLET ,genericPool);
        }else if (bulletBase.getSimpleName().equals(SimpleBullet2.class.getSimpleName())){
            bulletsPool.registerPool(TYPE_SIMPLE_BULLET_2 ,genericPool);
        }
    }

    protected void settings(){
        createSettings();
        attachToScene();
    }

    public void init(float x, float y, float direction, boolean unitTarget) {
        sprite.setPosition(x, y);
        sprite.setRotation(direction);
        physicsHandler.setVelocityY((float) (DEFAULT_SPEED * Math.sin(Utils.degreeToRad(direction))));
        physicsHandler.setVelocityX((float) (DEFAULT_SPEED * Math.cos(Utils.degreeToRad(direction))));
        sprite.setIgnoreUpdate(false);
        sprite.setVisible(true);
        bulletController.add(this);
        this.targetUnit = unitTarget;

        hw = sprite.getWidth()/2;
        hh = sprite.getHeight()/2;
    }

    protected void attachToScene() {
        MainScene.getActiveScene().attachChild(sprite);
    }

    protected void createSettings() {
        sprite.setColor(Color.BLUE);
        physicsHandler = new PhysicsHandler(sprite);
        sprite.registerUpdateHandler(physicsHandler);
        sprite.setIgnoreUpdate(true);
    }

    @Override
    public  void destroy(){
        sprite.setVisible(false);
        sprite.setIgnoreUpdate(true);
        bulletController.remove(this);
        if (getClass().getSimpleName().equals(SimpleBullet.class.getSimpleName())){
            bulletsPool.recyclePoolItem(TYPE_SIMPLE_BULLET,(SimpleBullet)this);
        }else if (getClass().getSimpleName().equals(SimpleBullet2.class.getSimpleName())){
            bulletsPool.recyclePoolItem(TYPE_SIMPLE_BULLET_2,(SimpleBullet2)this);
        }
    }

    @Override
    public IShape getShape() {
        return sprite;
    }

    public int getDamage(){
      return damage;
    }

    protected  void initCharacteristics(int speed, int damage){
        this.damage = damage;
        this.DEFAULT_SPEED = speed;
    }

    protected void checkHit() {
       if(targetUnit){
           checkHitUnit();
       }else{
           checkHitHero();
       }
    }

    private void checkHitHero() {

        if (hero.checkHit(this)){
            if (hero.addDamageAndCheckDeath(getDamage()) && hero.isAlive()){
                dieListener.heroDie();
                hero.destroy();
            }
            destroy();
        }
    }

    private void checkHitUnit() {
        Iterator<BaseUnit> col = enemyController.haveCollision(this);
        while (col.hasNext()){
            BaseUnit unit = col.next();
            if (unit.addDamageAndCheckDeath(getDamage())){
                col.remove();
                unit.destroy();
                main.addToScore(10 + unit.hashCode()%10);
            }
            destroy();
        }
    }

    public static BaseBullet getBullet(int type) {
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
}
