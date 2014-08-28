package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.Utils.Utils;
import donnu.zolotarev.SpaceShip.Weapons.Modificator.IWeaponModificator;

public class Minigun extends Guns implements IGun {

    public Minigun(boolean heroWeapon, int bullitType, IWeaponModificator modificator) {
        super(heroWeapon,bullitType,modificator);
        ATTACK_INTERVAL = 1;
        i = max;
    }
    private int i = 0;
    private int max = 20;
    @Override
    public boolean shoot() {
        shootDelay--;
        if (shootDelay < 0){
            shootDelay = ATTACK_INTERVAL;
            i--;
            if (i < 0){
                i = max;
                shootDelay = 2*max;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void initGunPos() {
        weaponPosIterator.add(new WeaponPos(null,0,0,0));
    }

    @Override
    public void fire(WeaponPos weaponPos) {
        weaponPos.anlge += (int)Utils.random(-5f,5f);
        super.fire(weaponPos);
    }
}
