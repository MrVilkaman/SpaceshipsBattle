package donnu.zolotarev.SpaceShip;

import donnu.zolotarev.SpaceShip.Scenes.MainScene;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import org.andengine.engine.Engine;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

public class Hero {


    private final Sprite sprite;

    public Hero(Engine shipActivity) {
        sprite = new Sprite(300, 100, TextureLoader.getShip(), shipActivity.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                return true;
            }
        };
    }

    public void attachToScene(MainScene mainScene) {
        mainScene.attachChild(sprite);
        mainScene.registerTouchArea(sprite);
    }


    public Sprite getSprite() {
        return sprite;
    }
}
