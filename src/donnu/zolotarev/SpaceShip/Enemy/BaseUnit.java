package donnu.zolotarev.SpaceShip.Enemy;

import android.graphics.Point;
import donnu.zolotarev.SpaceShip.Scenes.MainScene;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;

public class BaseUnit {

    protected Sprite sprite;
    protected PhysicsHandler physicsHandler;

    protected void attachToScene() {
        Scene mainScene = MainScene.getAcitveScene();
        mainScene.attachChild(sprite);
        mainScene.registerTouchArea(sprite);
    }

    public void setStartPosition(Point point){
        sprite.setX(point.x);
        sprite.setY(point.y);
    }

    public PhysicsHandler registerPhysicsHandler() {
        physicsHandler = new PhysicsHandler(sprite);
        sprite.registerUpdateHandler(physicsHandler);
        return physicsHandler;
    }
}
