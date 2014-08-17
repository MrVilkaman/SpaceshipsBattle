package donnu.zolotarev.SpaceShip.Units;

import android.graphics.Point;
import android.graphics.PointF;
import donnu.zolotarev.SpaceShip.EnemyAI.SpriteAI;
import donnu.zolotarev.SpaceShip.Scenes.BaseGameScene;
import donnu.zolotarev.SpaceShip.Scenes.InfinityGameScene;
import donnu.zolotarev.SpaceShip.Utils.ICollisionObject;
import donnu.zolotarev.SpaceShip.Utils.IHaveCoords;
import donnu.zolotarev.SpaceShip.Utils.ObjectController;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import donnu.zolotarev.SpaceShip.Weapons.WeaponController;
import org.andengine.engine.Engine;
import org.andengine.entity.shape.IShape;
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

    protected SpriteAI sprite;
   // protected PhysicsHandler physicsHandler;
    protected WeaponController weaponController;
    protected UnitSpecifications unitSpecifications;

    protected int defaultHealth;
    protected int defaultSpeed;
    protected float defaultMaxAngle;

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
            mainScene = InfinityGameScene.getActiveScene();
            engine = InfinityGameScene.getEngine();
            unitsController = mainScene.getEnemyController();
        }

        if (base.getSimpleName().equals(Enemy1.class.getSimpleName())){
            unitsPool.registerPool(TYPE_ENEMY_1, genericPool);
        }
    }

    public void init(Point point, float angle, UnitSpecifications us){
        unitSpecifications = us;
        init(point, angle);
    }

    public void init(Point point){
        init(point, 180);
    };

    public void init(Point point, float angle){
        if (unitSpecifications == null){
            unitSpecifications = new UnitSpecifications(defaultHealth,(int)Utils.random(defaultSpeed*0.8f,defaultSpeed*1.2f),defaultMaxAngle);
        }
        sprite.start(unitSpecifications);
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

    protected   void setStartPosition(Point point){
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
        unitSpecifications = null;
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

    public PointF  getPosition(){
        return new PointF(sprite.getX(),sprite.getY());
    }
}
