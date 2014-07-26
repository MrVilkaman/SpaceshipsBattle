package donnu.zolotarev.SpaceShip.Bullets;

import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.adt.pool.GenericPool;

public class SimpleBullet extends BulletBase {

    private static boolean isRegistredPool = false;

    private SimpleBullet() {
        initCharacteristics(1000, 40);
        sprite = new Sprite(0,0, TextureLoader.getSimpleBulletTextureRegion(),
                SpaceShipActivity.getInstance().getEngine().getVertexBufferObjectManager()){
            @Override
            protected void onManagedUpdate(float pSecondsElapsed) {
                if(this.mX < 0) {
                    destroy();
                } else if(this.mX + this.getWidth() > SpaceShipActivity.getCameraWidth()) {
                    destroy();
                }

                if(this.mY < 0) {
                    destroy();
                } else if(this.mY + this.getHeight() > SpaceShipActivity.getCameraHeight()) {
                    destroy();
                }

                checkHit();

                super.onManagedUpdate(pSecondsElapsed);

            }
        };
        settings();
    }


    public static void initPool() {
        if (!isRegistredPool){
            isRegistredPool = true;
            registredPool(SimpleBullet.class,new GenericPool() {
                @Override
                protected SimpleBullet onAllocatePoolItem() {
                    return new SimpleBullet();
                }
            });
        }
    }

}

