package donnu.zolotarev.SpaceShip.Units;

import android.graphics.Point;
import android.graphics.PointF;
import donnu.zolotarev.SpaceShip.AI.SpriteAI;
import donnu.zolotarev.SpaceShip.Activity.GameActivity;
import donnu.zolotarev.SpaceShip.Effects.Boom;
import donnu.zolotarev.SpaceShip.Effects.Shield;
import donnu.zolotarev.SpaceShip.GameState.IAmDie;
import donnu.zolotarev.SpaceShip.GameState.IHeroDieListener;
import donnu.zolotarev.SpaceShip.Scenes.BaseGameScene;
import donnu.zolotarev.SpaceShip.Scenes.InfinityGameScene;
import donnu.zolotarev.SpaceShip.Utils.*;
import donnu.zolotarev.SpaceShip.Weapons.WeaponController;
import org.andengine.engine.Engine;
import org.andengine.util.adt.pool.GenericPool;
import org.andengine.util.adt.pool.MultiPool;

public abstract class BaseUnit implements ICollisionObject, IHaveCoords {

    public static final int TYPE_ENEMY_SINGLE_GUN_L_1 = 0;
    public static final int TYPE_ENEMY_SINGLE_GUN_L_2 = TYPE_ENEMY_SINGLE_GUN_L_1 + 1;
    public static final int TYPE_ENEMY_SINGLE_GUN_L_3 = TYPE_ENEMY_SINGLE_GUN_L_2 + 1;

    public static final int TYPE_ENEMY_SINGLE_GUN_SHIELD_L_1 = TYPE_ENEMY_SINGLE_GUN_L_1 + Constants.MAX_UNIT_LEVEL;
    public static final int TYPE_ENEMY_SINGLE_GUN_SHIELD_L_2 = TYPE_ENEMY_SINGLE_GUN_L_2 + Constants.MAX_UNIT_LEVEL;
    public static final int TYPE_ENEMY_SINGLE_GUN_SHIELD_L_3 = TYPE_ENEMY_SINGLE_GUN_L_3 + Constants.MAX_UNIT_LEVEL;

    public static final int TYPE_ENEMY_MINIGUN_L_1 = TYPE_ENEMY_SINGLE_GUN_L_1 + Constants.MAX_UNIT_LEVEL_WITH_SHIELD;

    public static final int TYPE_ENEMY_MINIGUN_SHIELD_L_1 = TYPE_ENEMY_MINIGUN_L_1 +  Constants.MAX_UNIT_LEVEL;

    public static final int TYPE_ENEMY_ROCKET_L_1 = TYPE_ENEMY_MINIGUN_L_1 + Constants.MAX_UNIT_LEVEL_WITH_SHIELD;
    public static final int TYPE_ENEMY_ROCKET_L_2 = TYPE_ENEMY_ROCKET_L_1 + 1;

    public static final int TYPE_ENEMY_ROCKET_SHIELD_L_1 = TYPE_ENEMY_ROCKET_L_1 + Constants.MAX_UNIT_LEVEL;
    public static final int TYPE_ENEMY_ROCKET_SHIELD_L_2 = TYPE_ENEMY_ROCKET_L_2 + Constants.MAX_UNIT_LEVEL;

    public static final int TYPE_ENEMY_METEOR_L_1 = TYPE_ENEMY_ROCKET_L_1 + Constants.MAX_UNIT_LEVEL_WITH_SHIELD;

    public static final int TYPE_ENEMY_BOSS_1 = -3;

    private static final String TAG = "BaseUnit";

    protected static BaseGameScene mainScene;
    protected static Engine engine;
    private static MultiPool unitsPool;
    private static ObjectCollisionController unitsController;
    private static int enemiesOnMap = 0;
    private static IAmDie iAmDie;

    protected SpriteAI sprite;
   // protected PhysicsHandler physicsHandler;
    protected WeaponController weaponController;
    protected WaySpecifications waySpecifications;
    protected Shield shield;

    protected int unitLevel = -2;
    protected int health = 0;

    protected int defaultHealth;
    protected int defaultSpeed;
    protected float defaultMaxAngle;
    protected int defaultDamage = 600;
    protected int shieldPoint = 0;

    private float R = 0;
    private float cy;
    private float cx;
    protected int price = 0;
    protected static IHeroDieListener dieListener;
    static Hero hero;

    protected int damage;

    public static void setUnitDieListener(IAmDie iAmDie){
        BaseUnit.iAmDie = iAmDie;
    }

    public static void resetPool(){
        unitsPool = null;
        enemiesOnMap = 0;
    }

    protected static void registredPool(Class base,GenericPool genericPool){
        if (unitsPool == null){
            unitsPool = new MultiPool();
            mainScene = InfinityGameScene.getActiveScene();
            engine = GameActivity.engine();
            unitsController = mainScene.getEnemyController();
        }
        if (base.getSimpleName().equals(EnemySingleGun.class.getSimpleName())){
            unitsPool.registerPool(TYPE_ENEMY_SINGLE_GUN_L_1, genericPool);
        }else if (base.getSimpleName().equals(EnemyWithMiniGun.class.getSimpleName())){
            unitsPool.registerPool(TYPE_ENEMY_MINIGUN_L_1, genericPool);
        }else if (base.getSimpleName().equals(EnemyRockerGun.class.getSimpleName())){
            unitsPool.registerPool(TYPE_ENEMY_ROCKET_L_1, genericPool);
        }else if (base.getSimpleName().equals(Meteor.class.getSimpleName())){
            unitsPool.registerPool(TYPE_ENEMY_METEOR_L_1, genericPool);
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
        }

        health = defaultHealth;
        if (waySpecifications == null){
            waySpecifications = new WaySpecifications((int)Utils.random(defaultSpeed*0.8f,defaultSpeed*1.2f),defaultMaxAngle);
        }
        damage = (int)Utils.random(defaultDamage*0.8f,defaultDamage*1.2f);
        sprite.setRotation(angle);
        sprite.start(waySpecifications);
        setStartPosition(point);
        sprite.setIgnoreUpdate(false);
        sprite.setVisible(true);
        unitsController.add(this);
        setSize();
        enemiesOnMap++;


        if (shield == null){
            shield = Shield.useShield();
        }
            if (isHaveShuield()){
                shield.start(this);
            }else{
                shield.destroy();
            }

    }

    protected void setSize(){
        cx =  sprite.getWidth()/2;
        cy =  sprite.getHeight()/2;
        R = cy*cy;
    }

    protected void attachToScene() {
        mainScene.attachChild(sprite);
        sprite.setZIndex(0);
        mainScene.sortChildren();
    }

    protected void setStartPosition(Point point){
        sprite.setX(point.x);
        sprite.setY(point.y);
    }

    @Override
    public void destroy(Boolean withAnimate){
        enemiesOnMap--;
        sprite.setVisible(false);
        sprite.setIgnoreUpdate(true);
        sprite.restart();
        unitsController.remove(this);

        if (shield != null){
            shield.destroy();
        }
        waySpecifications = null;
        // todo УДАЛЯТЬ ДРУГИХ!
        if (getClass().getSimpleName().equals(EnemySingleGun.class.getSimpleName())){
            unitsPool.recyclePoolItem(TYPE_ENEMY_SINGLE_GUN_L_1,(EnemySingleGun)this);
        }else if (getClass().getSimpleName().equals(EnemyWithMiniGun.class.getSimpleName())){
            unitsPool.recyclePoolItem(TYPE_ENEMY_MINIGUN_L_1,(EnemyWithMiniGun)this);
        }else if (getClass().getSimpleName().equals(EnemyRockerGun.class.getSimpleName())){
            unitsPool.recyclePoolItem(TYPE_ENEMY_ROCKET_L_1,(EnemyRockerGun)this);
        }else if (getClass().getSimpleName().equals(Meteor.class.getSimpleName())){
            unitsPool.recyclePoolItem(TYPE_ENEMY_METEOR_L_1,(Meteor)this);
        }else {
           //new  Exception("Не известный тип!");
        }

        if (withAnimate){
            Boom.run(this);
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
        return xx*xx +yy*yy < R + object.getRadiusSqr();
    }

    public boolean addDamageAndCheckDeath(int damage) {
        if (shield != null){
            damage = shield.addDamage(damage);
        }
        health -= damage;
        return health < 0;
    }

    public boolean isAlive(){
        return 0 < health;
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
        Meteor.poolInit();
    }

    public int getMoney(){
        return price;
    }

    public static void setDieListener(IHeroDieListener listener){
        dieListener = listener;
    }

    protected void checkHitHero() {
        if (hero != null){
            if (checkHit(hero)){
                if (hero.addDamageAndCheckDeath(getDamage()) && hero.isAlive()){
                    dieListener.heroDie();
                    hero.destroy(true);
                }
                destroy(true);
                if (iAmDie != null){
                    iAmDie.destroyed(this);
                }
            }
        }
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public float getCenterX() {
        return sprite.getX() + cx;
    }

    @Override
    public float getCenterY() {
        return sprite.getY() + cy;
    }

    public int getShieldPoint() {
        return shieldPoint;
    }

    public boolean isHaveShuield() {
        return 0 < shieldPoint;
    }

    @Override
    public float getRadiusSqr() {
        return R;
    }
}
