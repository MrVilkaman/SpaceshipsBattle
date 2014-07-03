package donnu.zolotarev.SpaceShip.Bullets;

import org.andengine.util.adt.pool.GenericPool;

public class SimpleBullet extends BulletBase {

    public SimpleBullet() {
        //        super(simpleGun);
        DEFAULT_SPEED = 500;
    }

    public static void initBullet() {
        if (bulletsPool == null){
            bulletsPool = new GenericPool<SimpleBullet>() {
                @Override
                protected SimpleBullet onAllocatePoolItem() {
                    return new SimpleBullet();
                }
            };
        }
    }

    public static SimpleBullet getBullet() {
        return (SimpleBullet) bulletsPool.obtainPoolItem();
    }
}

