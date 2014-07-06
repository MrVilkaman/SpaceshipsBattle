package donnu.zolotarev.SpaceShip.Bullets;

import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.adt.pool.GenericPool;

import java.util.Iterator;

public class SimpleBullet2 extends BulletBase {
    private static boolean isRegistredPool = false;

    private SimpleBullet2() {
        super();
        final SimpleBullet2 self = this;
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
