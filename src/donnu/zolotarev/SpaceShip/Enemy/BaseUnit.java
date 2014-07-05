package donnu.zolotarev.SpaceShip.Enemy;

import android.graphics.Point;
import donnu.zolotarev.SpaceShip.ICollisionObject;
import donnu.zolotarev.SpaceShip.Scenes.MainScene;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;

public abstract class BaseUnit implements ICollisionObject {

    protected Sprite sprite;
    protected PhysicsHandler physicsHandler;
    private MainScene mainScene;

    protected void attachToScene() {
        mainScene = MainScene.getAcitveScene();
        mainScene.attachChild(sprite);
//        mainScene.registerTouchArea(sprite);
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

    public synchronized void destroy(){
        sprite.setVisible(false); //это не обязательно делать здесь.
        sprite.setIgnoreUpdate(true); //можно в классе пули создать метод, например, kill()
        SpaceShipActivity.getInstance().runOnUpdateThread(new Runnable() {
            @Override
            public void run() {
                Scene mainScene = MainScene.getAcitveScene();
                mainScene.detachChild(sprite);
            }});
            //        mainScene.unregisterTouchArea(sprite);
     //   mainScene.getEnemyController().remove(this);
    }

    @Override
    public IShape getShape() {
        return sprite;
    }
}
