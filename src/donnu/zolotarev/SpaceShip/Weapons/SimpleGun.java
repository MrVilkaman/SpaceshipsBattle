package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.Bullets.SimpleBullet;
import donnu.zolotarev.SpaceShip.Hero;
import donnu.zolotarev.SpaceShip.WeaponPos;

public class SimpleGun {
    private static SimpleGun instance;

    private final Hero hero;

    public SimpleGun(Hero h){
        hero = h;
       instance = this;
        SimpleBullet.initBullet();
    }

    private SimpleBullet GetNewBullet(final float x, final float y, final float direction){
        final SimpleBullet bullet = SimpleBullet.getBullet();
        bullet.init(x, y, direction);
        return bullet;
    }

    public void fire(){
        WeaponPos[] weaponsPos = hero.getWeaponPos();
        for (WeaponPos weaponPos : weaponsPos) {
            GetNewBullet(weaponPos.x,weaponPos.y,weaponPos.anlge);
        }
    }




}
