package donnu.zolotarev.SpaceShip.Bullets;

import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.adt.pool.GenericPool;

public class SimpleBullet extends BulletBase {

    private static boolean isRegistredPool = false;

    private SimpleBullet() {
        DEFAULT_SPEED = 500;
        sprite = new Sprite(0,0, TextureLoader.getSimpleBulletTextureRegion(),
                SpaceShipActivity.getInstance().getEngine().getVertexBufferObjectManager()){
            @Override
            protected void onManagedUpdate(float pSecondsElapsed) {
                if(this.mX < 0) {
                    deleteBullet();
                } else if(this.mX + this.getWidth() > SpaceShipActivity.getCameraWidth()) {
                    deleteBullet();
                }

                if(this.mY < 0) {
                    deleteBullet();
                } else if(this.mY + this.getHeight() > SpaceShipActivity.getCameraHeight()) {
                    deleteBullet();
                }
                super.onManagedUpdate(pSecondsElapsed);
            }
        };
        settings();
    }

    private static void initPool() {
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

    public static SimpleBullet getBullet() {
        initPool();
        return (SimpleBullet) bulletsPool.obtainPoolItem(BulletBase.TYPE_SIMPLE_BULLET);
    }

}

