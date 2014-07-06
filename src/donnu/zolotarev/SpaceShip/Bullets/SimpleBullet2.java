package donnu.zolotarev.SpaceShip.Bullets;

import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.adt.pool.GenericPool;

public class SimpleBullet2 extends BulletBase {
    private static boolean isRegistredPool = false;

    private SimpleBullet2() {
        super();
        initCharacteristics(1200,20);
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

                checkHit();

                super.onManagedUpdate(pSecondsElapsed);
            }
        };
        settings();
    }

    private static void initBullet() {
        if (!isRegistredPool){
            isRegistredPool = true;
            registredPool(SimpleBullet2.class,new GenericPool() {
                @Override
                protected SimpleBullet2 onAllocatePoolItem() {
                    return new SimpleBullet2();
                }
            });
        }
    }

    public static SimpleBullet2 getBullet() {
        initBullet();
        return (SimpleBullet2) bulletsPool.obtainPoolItem(BulletBase.TYPE_SIMPLE_BULLET_2);
    }
}
