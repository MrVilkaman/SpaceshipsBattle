package donnu.zolotarev.SpaceShip.Bullets;

import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.adt.pool.GenericPool;

public class Rocket extends BaseBullet {

    private static boolean isRegistredPool = false;

    private Rocket() {
        initCharacteristics(1000, 200);
        sprite = new Sprite(0,0, TextureLoader.getRocketAmmoTextureRegion(),
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


    public static void poolInit() {
        isRegistredPool = true;
        registredPool(Rocket.class,new GenericPool() {
                @Override
                protected Rocket onAllocatePoolItem() {
                    return new Rocket();
                }
            });
    }

}

