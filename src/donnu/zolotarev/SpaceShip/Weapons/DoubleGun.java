package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.Weapons.Modificator.IWeaponModificator;

import java.util.Random;

public class DoubleGun extends Guns implements IGun {

    public DoubleGun(boolean heroWeapon, int bullitType,IWeaponModificator modificator) {
        super(heroWeapon,bullitType,modificator);
        ATTACK_INTERVAL = 6;
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
