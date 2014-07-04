package donnu.zolotarev.SpaceShip.Bullets;

import donnu.zolotarev.SpaceShip.Scenes.MainScene;
import donnu.zolotarev.SpaceShip.Utils;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.adt.pool.GenericPool;
import org.andengine.util.adt.pool.MultiPool;
import org.andengine.util.color.Color;

public abstract class BulletBase {

    public static final int TYPE_SIMPLE_BULLET = 0;
    public static final int TYPE_SIMPLE_BULLET_2 = 1;

    protected Sprite sprite;
    //    private final SimpleGun gun;
    protected int DEFAULT_SPEED;//1000;
    private PhysicsHandler physicsHandler;
    protected static MultiPool bulletsPool;

   /* private static void initPool(){
        bulletsPool = new MultiPool();
    }*/

    protected static void registredPool(Class bulletBase,GenericPool genericPool){
        if (bulletsPool == null){
            bulletsPool = new MultiPool();
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
        physicsHandler.setVelocityY((float)(DEFAULT_SPEED * Math.sin(Utils.degreeToRad(direction))));
        physicsHandler.setVelocityX((float)(DEFAULT_SPEED * Math.cos(Utils.degreeToRad(direction))));
        sprite.setIgnoreUpdate(false);
        sprite.setVisible(true);
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


    protected void deleteBullet(){
        sprite.setVisible(false); //это не обязательно делать здесь.
        sprite.setIgnoreUpdate(true); //можно в классе пули создать метод, например, kill()
        if (getClass().getSimpleName().equals(SimpleBullet.class.getSimpleName())){
            bulletsPool.recyclePoolItem(TYPE_SIMPLE_BULLET,(SimpleBullet)this);
        }else if (getClass().getSimpleName().equals(SimpleBullet2.class.getSimpleName())){
            bulletsPool.recyclePoolItem(TYPE_SIMPLE_BULLET_2,(SimpleBullet2)this);
        }

    }

}
