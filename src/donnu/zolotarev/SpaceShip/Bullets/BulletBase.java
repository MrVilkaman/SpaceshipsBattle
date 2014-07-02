package donnu.zolotarev.SpaceShip.Bullets;

import donnu.zolotarev.SpaceShip.Scenes.MainScene;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Utils;
import donnu.zolotarev.SpaceShip.Weapons.SimpleGun;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.color.Color;

public class BulletBase extends Sprite {

    protected int DEFAULT_SPEED;//1000;
    private PhysicsHandler physicsHandler;


    public BulletBase(SimpleGun simpleGun) {
        super(0,0, TextureLoader.getSimpleBulletTextureRegion(), SpaceShipActivity.getInstance().getEngine().getVertexBufferObjectManager());
        createSettings();
        attachToScene();
    }

    public BulletBase(SimpleGun simpleGun,float x, float y) {
        super(x,y, TextureLoader.getSimpleBulletTextureRegion(), SpaceShipActivity.getInstance().getEngine().getVertexBufferObjectManager());
        createSettings();
        attachToScene();
    }


    public void init(float x, float y, float direction) {
        setPosition(x,y);
        setRotation(direction);
        physicsHandler.setVelocityY((float)(DEFAULT_SPEED * Math.sin(Utils.degreeToRad(direction))));
        physicsHandler.setVelocityX((float)(DEFAULT_SPEED * Math.cos(Utils.degreeToRad(direction))));
        setIgnoreUpdate(false);
    }

    public void attachToScene() {
        MainScene.getAcitveScene().attachChild(this);
    }

    public void createSettings() {
        setColor(Color.BLUE);
        physicsHandler = new PhysicsHandler(this);
        registerUpdateHandler(physicsHandler);
        setIgnoreUpdate(true);



    }

}
