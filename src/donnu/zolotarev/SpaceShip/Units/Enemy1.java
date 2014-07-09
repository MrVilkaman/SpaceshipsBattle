package donnu.zolotarev.SpaceShip.Units;

import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import org.andengine.entity.sprite.Sprite;

public class Enemy1  extends BaseUnit {



    public Enemy1(){
        super();
        health = 500;
        ///
        sprite = new Sprite(0, 0, TextureLoader.getEnemyShip(), engine.getVertexBufferObjectManager()){
            @Override
            protected void onManagedUpdate(float pSecondsElapsed) {
                if(mX - getWidth()<200){
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
