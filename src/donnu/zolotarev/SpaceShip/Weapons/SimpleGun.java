package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.WeaponPos;

import java.util.Random;

public class SimpleGun extends Guns {

    public SimpleGun(boolean heroWeapon, int bullitType) {
        super(heroWeapon,bullitType);
        ATTACK_INTERVAL = 5;
        if (!heroWeapon){
            Random random = new Random(this.hashCode());
            ATTACK_INTERVAL *= 18 + random.nextInt(5);
        }
    }

    public void fire(WeaponPos weaponPos) {
        GetNewBullet(weaponPos.x, weaponPos.y-5, weaponPos.anlge);
        GetNewBullet(weaponPos.x, weaponPos.y+5, weaponPos.anlge);
    }

}
