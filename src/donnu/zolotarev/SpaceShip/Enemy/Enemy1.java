package donnu.zolotarev.SpaceShip.Enemy;

import donnu.zolotarev.SpaceShip.Scenes.MainScene;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import org.andengine.engine.Engine;
import org.andengine.entity.sprite.Sprite;

public class Enemy1  extends BaseUnit {

    private final Engine engine;

    public Enemy1(){
        engine =  MainScene.getEngine();
        sprite = new Sprite(0, 0, TextureLoader.getEnemyShip(), SpaceShipActivity
                .getInstance().getEngine().getVertexBufferObjectManager());
        attachToScene();
        sprite.setRotation(180);
    }
}
