package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.Bullets.SimpleBullet;
import donnu.zolotarev.SpaceShip.Bullets.SimpleBullet2;
import donnu.zolotarev.SpaceShip.Hero;
import donnu.zolotarev.SpaceShip.WeaponPos;

public class SimpleGun {
    private static SimpleGun instance;

    private final Hero hero;

    public SimpleGun(Hero h){
        hero = h;
       instance = this;
    }

    private void GetNewBullet(final float x, final float y, final float direction){
    //    final SimpleBullet bullet;
        if (direction == 0){
            SimpleBullet.getBullet().init(x, y, direction);
        } else {
            SimpleBullet2.getBullet().init(x, y, direction);
        }
    }

    public void fire(){
        WeaponPos[] weaponsPos = hero.getWeaponPos();
        for (WeaponPos weaponPos : weaponsPos) {
            GetNewBullet(weaponPos.x,weaponPos.y,weaponPos.anlge);
        }
    }




}
