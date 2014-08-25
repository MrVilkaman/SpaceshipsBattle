package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.Weapons.Modificator.IWeaponModificator;

import java.util.Random;

public class SimpleGun extends Guns implements IGun {

    public SimpleGun(boolean heroWeapon, int bullitType,IWeaponModificator modificator) {
        super(heroWeapon,bullitType,modificator);
        ATTACK_INTERVAL = 5;
        if (!heroWeapon){
            Random random = new Random(this.hashCode());
            ATTACK_INTERVAL *= 13 + random.nextInt(5);
        }
    }

    @Override
    public void fire(WeaponPos weaponPos) {
        GetNewBullet(weaponPos.x, weaponPos.y, weaponPos.anlge);
    }



}
