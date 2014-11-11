package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.Weapons.Modificator.IWeaponModificator;

import java.util.Random;

public class DoubleGun extends Guns  {


    public DoubleGun(boolean heroWeapon, int bullitType,IWeaponModificator[] modificator) {
        super(heroWeapon,bullitType,modificator);
        ATTACK_INTERVAL = 6;
        if (!heroWeapon){
            Random random = new Random(this.hashCode());
            ATTACK_INTERVAL *= 13 + random.nextInt(5);
        }

        applyModificator(modificator);
    }

    @Override
    protected void initGunPos() {
        weaponPosIterator.add(new WeaponPos(null,0,-5,0));
        weaponPosIterator.add(new WeaponPos(null,0,5,0));
    }
}
