package donnu.zolotarev.SpaceShip.Enemy;

import donnu.zolotarev.SpaceShip.Scenes.MainScene;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import org.andengine.engine.Engine;
import org.andengine.entity.sprite.Sprite;

public class Enemy1  extends BaseUnit {

    private final Engine engine;

    public Enemy1(){
        health = 500;
        ///
        engine =  MainScene.getEngine();
        sprite = new Sprite(0, 0, TextureLoader.getEnemyShip(), SpaceShipActivity
                .getInstance().getEngine().getVertexBufferObjectManager()){
            @Override
            protected void onManagedUpdate(float pSecondsElapsed) {
                if(mX - getWidth()<200){
                  //destroy();
                    physicsHandler.setVelocityX(0);
                }
                super.onManagedUpdate(pSecondsElapsed);
            }
        };
        attachToScene();
        sprite.setRotation(180);

        registerPhysicsHandler();
       physicsHandler.setVelocityX(-100);
    }

    @Override
    public boolean addDamageAndCheckDeath(int damage) {
        health -= damage;
        return health < 0;
    }
}
