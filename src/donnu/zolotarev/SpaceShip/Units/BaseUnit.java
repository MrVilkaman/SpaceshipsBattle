package donnu.zolotarev.SpaceShip.Units;

import android.graphics.Point;
import donnu.zolotarev.SpaceShip.Scenes.BaseGameScene;
import donnu.zolotarev.SpaceShip.Scenes.MainScene;
import donnu.zolotarev.SpaceShip.Utils.ICollisionObject;
import donnu.zolotarev.SpaceShip.Utils.IHaveCoords;
import donnu.zolotarev.SpaceShip.Utils.ObjectController;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import donnu.zolotarev.SpaceShip.Weapons.WeaponController;
import org.andengine.engine.Engine;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.adt.pool.GenericPool;
import org.andengine.util.adt.pool.MultiPool;

public abstract class BaseUnit implements ICollisionObject {

    public static final int TYPE_ENEMY_1 = 0;
    private static final String TAG = "BaseUnit";

    //public static final int TYPE_SIMPLE_BULLET_2 = TYPE_ENEMY_1 + 1;
    protected static BaseGameScene mainScene;
    protected static Engine engine;
    private static MultiPool unitsPool;
    private static ObjectController unitsController;
    private static int enemiesOnMap = 0;

    protected Sprite sprite;
    protected PhysicsHandler physicsHandler;
    protected WeaponController weaponController;

    protected int defaultHealth;
    protected int defaultSpeed;

    protected int health;
    protected int speed;

    private float R = 0;
    private float cy;
    private float cx;

    public static void resetPool(){
        unitsPool = null;
        enemiesOnMap = 0;
    }

    protected static void registredPool(Class base,GenericPool genericPool){
        if (unitsPool == null){
            unitsPool = new MultiPool();
            mainScene = MainScene.getActiveScene();
            engine = MainScene.getEngine();
            unitsController = mainScene.getEnemyController();
        }

        if (base.getSimpleName().equals(Enemy1.class.getSimpleName())){
            unitsPool.registerPool(TYPE_ENEMY_1, genericPool);
        }
    }

    public void init(Point point){
        health = defaultHealth;
        speed = (int)Utils.random(defaultSpeed*0.8f,defaultSpeed*1.2f);

        setStartPosition(point);
        physicsHandler.setVelocityX(- 1 * speed);
        sprite.setIgnoreUpdate(false);
        sprite.setVisible(true);
        unitsController.add(this);
        setSize();
        enemiesOnMap++;
    }

    protected void setSize(){
        cx =  sprite.getWidth()/2;
        cy =  sprite.getHeight()/2;
        R = cy*cy;
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
        unitsController.remove(this);

        if (getClass().getSimpleName().equals(Enemy1.class.getSimpleName())){
            unitsPool.recyclePoolItem(TYPE_ENEMY_1,(Enemy1)this);
        }
    }

    @Override
    public IShape getShape() {
        return sprite;
    }

    @Override
    public boolean checkHit(IHaveCoords object) {
        float xx = (sprite.getX() + cx) - object.getCenterX();
        float yy = (sprite.getY() + cy) - object.getCenterY();
        return xx*xx +yy*yy < R;
    }

    public abstract boolean addDamageAndCheckDeath(int damage);

    protected abstract void loadWeapon();

    public abstract void canFire(boolean b);

    public static int getEnemiesOnMap() {
        return enemiesOnMap;
    }


    public static BaseUnit getEnemy(int type) {
        return ((BaseUnit)unitsPool.obtainPoolItem(type));
    }
}
