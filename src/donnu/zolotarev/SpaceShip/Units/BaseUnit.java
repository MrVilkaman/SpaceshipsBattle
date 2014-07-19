package donnu.zolotarev.SpaceShip.Units;

import android.graphics.Point;
import donnu.zolotarev.SpaceShip.Utils.ICollisionObject;
import donnu.zolotarev.SpaceShip.Utils.ObjectController;
import donnu.zolotarev.SpaceShip.Scenes.MainScene;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Weapons.WeaponController;
import org.andengine.engine.Engine;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.adt.pool.GenericPool;
import org.andengine.util.adt.pool.MultiPool;

public abstract class BaseUnit implements ICollisionObject {

    public static final int TYPE_ENEMY_1 = 0;
    //public static final int TYPE_SIMPLE_BULLET_2 = TYPE_ENEMY_1 + 1;

    private static MultiPool unitsPool;
    private static ObjectController unitsController;
    protected Sprite sprite;
    protected PhysicsHandler physicsHandler;
    protected WeaponController weaponController;
    protected static MainScene mainScene;
    protected static Engine engine;

    protected int health;
    protected int SPEED;

    private static int enemiesOnMap = -0;

    protected static void registredPool(Class base,GenericPool genericPool){
        if (unitsPool == null){
            unitsPool = new MultiPool();
            mainScene = MainScene.getAcitveScene();
            engine = MainScene.getEngine();
            unitsController = mainScene.getEnemyController();
        }
        if (base.getSimpleName().equals(Enemy1.class.getSimpleName())){
            unitsPool.registerPool(TYPE_ENEMY_1, genericPool);
        }
    }

    public void init(Point point){
        setStartPosition(point);
        physicsHandler.setVelocityX(-1*SPEED);
        sprite.setIgnoreUpdate(false);
        sprite.setVisible(true);
        unitsController.add(this);

        enemiesOnMap++;
    }

    protected void attachToScene() {
        mainScene.attachChild(sprite);
    }

    protected   void setStartPosition(Point point){
        sprite.setX(point.x);
        sprite.setY(point.y);
    }

    public PhysicsHandler registerPhysicsHandler() {
        physicsHandler = new PhysicsHandler(sprite);
        sprite.registerUpdateHandler(physicsHandler);
        return physicsHandler;
    }

    @Override
    public void destroy(){
        enemiesOnMap--;
        sprite.setVisible(false); //это не обязательно делать здесь.
        sprite.setIgnoreUpdate(true); //можно в классе пули создать метод, например, kill()
        SpaceShipActivity.getInstance().runOnUpdateThread(new Runnable() {
            @Override
            public void run() {
                Scene mainScene = MainScene.getAcitveScene();
                mainScene.detachChild(sprite);
            }});
        if (getClass().getSimpleName().equals(Enemy1.class.getSimpleName())){
            unitsPool.recyclePoolItem(TYPE_ENEMY_1,(Enemy1)this);
        }
    }

    @Override
    public IShape getShape() {
        return sprite;
    }


    public abstract boolean addDamageAndCheckDeath(int damage);

    protected abstract void loadWeapon();

    public abstract void canFire(boolean b);

    public static int getEnemiesOnMap() {
        return enemiesOnMap;
    }


    public static BaseUnit getBullet(int type) {
        return ((BaseUnit)unitsPool.obtainPoolItem(type));
    }
}
