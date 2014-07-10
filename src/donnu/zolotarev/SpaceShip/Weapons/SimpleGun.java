package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.WeaponPos;

public class SimpleGun extends Guns {


    public SimpleGun(boolean heroWeapon) {
        super(heroWeapon);
        ATTACK_INTERVAL = 5;
        if (!heroWeapon){
            ATTACK_INTERVAL *= 20;
        }
    }

    public void fire(WeaponPos weaponPos) {
        GetNewBullet(weaponPos.x, weaponPos.y-5, weaponPos.anlge);
        GetNewBullet(weaponPos.x, weaponPos.y+5, weaponPos.anlge);
    }

}
