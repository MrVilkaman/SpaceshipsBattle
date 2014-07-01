package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.Bullets.SimpleBullet;
import org.andengine.util.adt.pool.GenericPool;

public class SimpleGun {
    private static SimpleGun instance;

    private static  GenericPool<SimpleBullet> bulletsPool;

    public SimpleGun(){
       instance = this;
        bulletsPool = new GenericPool<SimpleBullet>() {

            @Override
            protected SimpleBullet onAllocatePoolItem() {
                return new SimpleBullet(instance);
            }
        };
    }

    private SimpleBullet GetNewBullet(final float x, final float y, final float direction){
        final SimpleBullet bullet = bulletsPool.obtainPoolItem();
        bullet.init(x, y, direction);
        return bullet;
    }

    public void deleteBullet(SimpleBullet bullet){
        bullet.setVisible(false); //это не обязательно делать здесь.
        bullet.setIgnoreUpdate(true); //можно в классе пули создать метод, например, kill()
        bulletsPool.recyclePoolItem(bullet);
    }


}
