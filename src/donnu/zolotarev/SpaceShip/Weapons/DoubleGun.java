package donnu.zolotarev.SpaceShip.Weapons;

import java.util.Random;

public class DoubleGun extends Guns implements IGun {

    public DoubleGun(boolean heroWeapon, int bullitType) {
        super(heroWeapon,bullitType);
        ATTACK_INTERVAL = 3;
        if (!heroWeapon){
            Random random = new Random(this.hashCode());
            ATTACK_INTERVAL *= 13 + random.nextInt(5);
        }
    }

    @Override
    public void fire(WeaponPos weaponPos) {
        GetNewBullet(weaponPos.x, weaponPos.y-5, weaponPos.anlge);
        GetNewBullet(weaponPos.x, weaponPos.y+5, weaponPos.anlge);
    }



}
