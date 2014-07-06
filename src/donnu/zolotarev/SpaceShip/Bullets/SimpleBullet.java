package donnu.zolotarev.SpaceShip.Bullets;

import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.adt.pool.GenericPool;

import java.util.Iterator;

public class SimpleBullet extends BulletBase {

    private static boolean isRegistredPool = false;

    private SimpleBullet() {
        initCharacteristics(700, 40);
        final SimpleBullet self = this;
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


                Iterator<BaseUnit> col = main.getEnemyController().haveCollision(self);

                while (col.hasNext()){
                    BaseUnit unit = col.next();
                    if (unit.addDamageAndCheckDeath(getDamage())){
                        unit.destroy();
                        col.remove();
                    }
                    self.deleteBullet();
                    main.getBulletController().remove(self);
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

