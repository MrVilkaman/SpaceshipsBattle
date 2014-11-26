package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.Utils.Utils;
import donnu.zolotarev.SpaceShip.Weapons.Modificator.IWeaponModificator;

public class DoubleGun extends Guns  {


    public DoubleGun(boolean heroWeapon, int bullitType,IWeaponModificator[] modificator) {
        super(heroWeapon,bullitType,modificator);
        ATTACK_INTERVAL = 6;
        if (!heroWeapon){
            ATTACK_INTERVAL *= 13 + Utils.getRandom(5);
        }

        applyModificator(modificator);
    }

    @Override
    protected void initGunPos() {
        weaponPosIterator.add(new WeaponPos(null,0,-5,0));
        weaponPosIterator.add(new WeaponPos(null,0,5,0));
    }
}
