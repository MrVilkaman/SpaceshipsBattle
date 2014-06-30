package donnu.zolotarev.SpaceShip.Scenes;

import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;

public class MainScene extends Scene {
    private SpaceShipActivity shipActivity;

    public MainScene() {
        //super();
        shipActivity = SpaceShipActivity.getInstance();
        setBackground(new Background(0.9f,0.9f,0.9f));

        Sprite sprite = new Sprite(300,100,TextureLoader.getShip(),shipActivity.getVertexBufferObjectManager());
        attachChild(sprite);
    }
}
