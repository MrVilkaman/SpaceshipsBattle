package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.Bullets.BulletBase;
import donnu.zolotarev.SpaceShip.Bullets.SimpleBullet;
import donnu.zolotarev.SpaceShip.Hero;
import donnu.zolotarev.SpaceShip.WeaponPos;
import org.andengine.util.adt.pool.GenericPool;

public class SimpleGun {
    private static SimpleGun instance;
    private static  GenericPool<SimpleBullet> bulletsPool;
    private final Hero hero;

    public SimpleGun(Hero h){
        hero = h;
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

    public void fire(){
        WeaponPos[] weaponsPos = hero.getWeaponPos();
        for (WeaponPos weaponPos : weaponsPos) {
            GetNewBullet(weaponPos.x,weaponPos.y,weaponPos.anlge);
        }
    }

    public void deleteBullet(BulletBase bullet){
        bullet.setVisible(false); //это не обязательно делать здесь.
        bullet.setIgnoreUpdate(true); //можно в классе пули создать метод, например, kill()
        if (bullet.getClass().getSimpleName().equals(SimpleBullet.class.getSimpleName())){
            bulletsPool.recyclePoolItem((SimpleBullet)bullet);
        }


    }


}
