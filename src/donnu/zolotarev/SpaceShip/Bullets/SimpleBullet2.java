package donnu.zolotarev.SpaceShip.Bullets;

import org.andengine.util.adt.pool.GenericPool;

public class SimpleBullet2 extends BulletBase {
    private static boolean isRegistredPool = false;

    public SimpleBullet2() {
        //        super(simpleGun);
        DEFAULT_SPEED = 1000;
    }

    public static void initBullet() {
        if (!isRegistredPool){
            isRegistredPool = true;
            bulletsPool.registerPool(BulletBase.TYPE_SIMPLE_BULLET_2,new GenericPool() {
                @Override
                protected SimpleBullet2 onAllocatePoolItem() {
                    return new SimpleBullet2();
                }
            });
        }


    }

    public static SimpleBullet2 getBullet() {
        return (SimpleBullet2) bulletsPool.obtainPoolItem(BulletBase.TYPE_SIMPLE_BULLET_2);
    }
}
