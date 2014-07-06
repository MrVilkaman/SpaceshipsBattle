package donnu.zolotarev.SpaceShip.Bullets;

import donnu.zolotarev.SpaceShip.ICollisionObject;
import donnu.zolotarev.SpaceShip.ObjectController;
import donnu.zolotarev.SpaceShip.Scenes.MainScene;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Utils;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.adt.pool.GenericPool;
import org.andengine.util.adt.pool.MultiPool;
import org.andengine.util.color.Color;

import java.util.Iterator;

public abstract class BulletBase implements ICollisionObject {

    public static final int TYPE_SIMPLE_BULLET = 0;
    public static final int TYPE_SIMPLE_BULLET_2 = 1;
    protected static MainScene main;
    private static ObjectController bulletController;
    private static ObjectController enemyController;

    protected Sprite sprite;
    private PhysicsHandler physicsHandler;
    protected static MultiPool bulletsPool;


    private int DEFAULT_SPEED;//1000;
    private int damage;

    protected static void registredPool(Class bulletBase,GenericPool genericPool){
        if (bulletsPool == null){
            bulletsPool = new MultiPool();
            main = MainScene.getAcitveScene();
            bulletController = main.getBulletController();
            enemyController = main.getEnemyController();
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

    public void init(float x, float y, float direction) {
        sprite.setPosition(x, y);
        sprite.setRotation(direction);
        physicsHandler.setVelocityY((float) (DEFAULT_SPEED * Math.sin(Utils.degreeToRad(direction))));
        physicsHandler.setVelocityX((float) (DEFAULT_SPEED * Math.cos(Utils.degreeToRad(direction))));
        sprite.setIgnoreUpdate(false);
        sprite.setVisible(true);
        bulletController.add(this);
    }

    protected void attachToScene() {
        MainScene.getAcitveScene().attachChild(sprite);
    }

    protected void createSettings() {
        sprite.setColor(Color.BLUE);
        physicsHandler = new PhysicsHandler(sprite);
        sprite.registerUpdateHandler(physicsHandler);
        sprite.setIgnoreUpdate(true);

    }


    public synchronized void deleteBullet(){
        sprite.setVisible(false); //это не обязательно делать здесь.
        sprite.setIgnoreUpdate(true); //можно в классе пули создать метод, например, kill()
     //   main.getBulletController().remove(this);
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
        Iterator<BaseUnit> col = enemyController.haveCollision(this);
        while (col.hasNext()){
            BaseUnit unit = col.next();
            if (unit.addDamageAndCheckDeath(getDamage())){
                unit.destroy();
                col.remove();
            }
            deleteBullet();
            bulletController.remove(this);
        }
    }

}
