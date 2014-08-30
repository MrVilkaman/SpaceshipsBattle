package donnu.zolotarev.SpaceShip.Units;

import android.graphics.Point;
import android.graphics.PointF;
import donnu.zolotarev.SpaceShip.AI.SpriteAI;
import donnu.zolotarev.SpaceShip.Scenes.BaseGameScene;
import donnu.zolotarev.SpaceShip.Scenes.InfinityGameScene;
import donnu.zolotarev.SpaceShip.Utils.*;
import donnu.zolotarev.SpaceShip.Weapons.WeaponController;
import org.andengine.engine.Engine;
import org.andengine.util.adt.pool.GenericPool;
import org.andengine.util.adt.pool.MultiPool;

public abstract class BaseUnit implements ICollisionObject {

    public static final int TYPE_ENEMY_SINGLE_GUN_L_1 = 0;
    public static final int TYPE_ENEMY_SINGLE_GUN_L_2 = TYPE_ENEMY_SINGLE_GUN_L_1 + 1;
    public static final int TYPE_ENEMY_SINGLE_GUN_L_3 = TYPE_ENEMY_SINGLE_GUN_L_2 + 1;
    public static final int TYPE_ENEMY_MINIGUN_L_1 = TYPE_ENEMY_SINGLE_GUN_L_1 + Constants.MAX_UNIT_LEVEL;

    public static final int TYPE_ENEMY_ROCKET_L_1 = TYPE_ENEMY_MINIGUN_L_1 + Constants.MAX_UNIT_LEVEL;
    public static final int TYPE_ENEMY_ROCKET_L_2 = TYPE_ENEMY_ROCKET_L_1 + 1;

    private static final String TAG = "BaseUnit";

    protected static BaseGameScene mainScene;
    protected static Engine engine;
    private static MultiPool unitsPool;
    private static ObjectController unitsController;
    private static int enemiesOnMap = 0;

    protected SpriteAI sprite;
   // protected PhysicsHandler physicsHandler;
    protected WeaponController weaponController;
    protected WaySpecifications waySpecifications;

    protected int unitLevel = -1;
    protected int health = 0;

    protected int defaultHealth;
    protected int defaultSpeed;
    protected float defaultMaxAngle;

    private float R = 0;
    private float cy;
    private float cx;
    protected int price = 0;


    public static void resetPool(){
        unitsPool = null;
        enemiesOnMap = 0;
    }

    protected static void registredPool(Class base,GenericPool genericPool){
        if (unitsPool == null){
            unitsPool = new MultiPool();
            mainScene = InfinityGameScene.getActiveScene();
            engine = InfinityGameScene.getEngine();
            unitsController = mainScene.getEnemyController();
        }

        if (base.getSimpleName().equals(EnemySingleGun.class.getSimpleName())){
            unitsPool.registerPool(TYPE_ENEMY_SINGLE_GUN_L_1, genericPool);
        }else if (base.getSimpleName().equals(EnemyWithMiniGun.class.getSimpleName())){
            unitsPool.registerPool(TYPE_ENEMY_MINIGUN_L_1, genericPool);
        }else if (base.getSimpleName().equals(EnemyRockerGun.class.getSimpleName())){
            unitsPool.registerPool(TYPE_ENEMY_ROCKET_L_1, genericPool);
        }
    }

    public void init(int level, Point point, float angle, WaySpecifications us){
        waySpecifications = us;
        init(level, point, angle);
    }

    public void init(int level, Point point){
        init(level, point, 180);
    };

    public void init(int level, Point point, float angle){
        if (unitLevel != level){
            unitLevel = level;
            loadParam(unitLevel);
            loadWeapon(unitLevel);
        }else{
            int f = 0;
            f =1;
        }

        health = defaultHealth;
        if (waySpecifications == null){
            waySpecifications = new WaySpecifications((int)Utils.random(defaultSpeed*0.8f,defaultSpeed*1.2f),defaultMaxAngle);
        }
        sprite.start(waySpecifications);
        setStartPosition(point);
        sprite.setRotation(angle);
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

    protected void setStartPosition(Point point){
        sprite.setX(point.x);
        sprite.setY(point.y);
    }

    @Override
    public void destroy(){
        enemiesOnMap--;
        sprite.setVisible(false);
        sprite.setIgnoreUpdate(true);
        sprite.restart();
        unitsController.remove(this);
        waySpecifications = null;
        if (getClass().getSimpleName().equals(EnemySingleGun.class.getSimpleName())){
            unitsPool.recyclePoolItem(TYPE_ENEMY_SINGLE_GUN_L_1,(EnemySingleGun)this);
        }
    }

    @Override
    public org.andengine.entity.sprite.Sprite getShape() {
        return sprite;
    }

    @Override
    public boolean checkHit(IHaveCoords object) {
        float xx = (sprite.getX() + cx) - object.getCenterX();
        float yy = (sprite.getY() + cy) - object.getCenterY();
        return xx*xx +yy*yy < R;
    }

    public boolean addDamageAndCheckDeath(int damage) {
        health -= damage;
        return health < 0;
    }

    protected abstract void loadWeapon(int level);

    protected abstract void loadParam(int level);

    public void canFire(boolean b){};

    public static int getEnemiesOnMap() {
        return enemiesOnMap;
    }


    public static BaseUnit getEnemy(int type) {
        return ((BaseUnit)unitsPool.obtainPoolItem(type));
    }

    public PointF  getPosition(){
        return new PointF(sprite.getX()+cx,sprite.getY()+cy);
    }

    public static void initPool() {
        EnemySingleGun.poolInit();
        EnemyWithMiniGun.poolInit();
        EnemyRockerGun.poolInit();
    }

    public int getMoney(){
        return price;
    }
}
