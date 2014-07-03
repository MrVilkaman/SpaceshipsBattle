package donnu.zolotarev.SpaceShip.Bullets;

import org.andengine.util.adt.pool.GenericPool;

public class SimpleBullet extends BulletBase {

    private static boolean isRegistredPool = false;

    public SimpleBullet() {
        //        super(simpleGun);
        DEFAULT_SPEED = 500;
    }

    public static void initBullet() {
        if (!isRegistredPool){
            isRegistredPool = true;
            bulletsPool.registerPool(BulletBase.TYPE_SIMPLE_BULLET,new GenericPool() {
                @Override
                protected SimpleBullet onAllocatePoolItem() {
                    return new SimpleBullet();
                }
            });
        }
    }

    public static SimpleBullet getBullet() {
        return (SimpleBullet) bulletsPool.obtainPoolItem(BulletBase.TYPE_SIMPLE_BULLET);
    }
}

