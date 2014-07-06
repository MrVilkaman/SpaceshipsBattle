package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.WeaponPos;

public class SimpleGun extends Guns {


    public SimpleGun() {
        ATTACK_INTERVAL = 5;
    }

    public void fire(WeaponPos weaponPos) {
        GetNewBullet(weaponPos.x, weaponPos.y-5, weaponPos.anlge);
        GetNewBullet(weaponPos.x, weaponPos.y+5, weaponPos.anlge);
    }

}
