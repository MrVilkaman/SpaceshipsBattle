package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;
import donnu.zolotarev.SpaceShip.Weapons.Modificator.IWeaponModificator;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Guns implements IGun {
    private final int bullitType;
    private final IWeaponModificator modificator;
    protected ArrayList<WeaponPos> weaponPosIterator = new ArrayList<WeaponPos>();

    protected int ATTACK_INTERVAL;
    protected int shootDelay = 0;
    protected boolean isShoot = false;
    private boolean targetUnit;

    public Guns(boolean heroWeapon, int bullitType,IWeaponModificator modificator) {
        targetUnit = heroWeapon;
        this.bullitType = bullitType;
        this.modificator = modificator;
        initGunPos();
    }

    protected abstract void initGunPos();

    public void reload(){
        shootDelay = 0;
    }

    public boolean shoot() {
        shootDelay--;
        if (shootDelay < 0){
            shootDelay = ATTACK_INTERVAL;
            return true;
        }
        return false;
    }

    protected void GetNewBullet(final float x, final float y, final float direction) {
        BaseBullet.getBullet(bullitType).init(x, y, direction, targetUnit,modificator);
    }

    @Override
    public Iterator<WeaponPos> getWeaponPos() {
        return weaponPosIterator.iterator();
    }

    @Override
    public void fire(WeaponPos weaponPos) {
        GetNewBullet(weaponPos.x, weaponPos.y, weaponPos.anlge);
    }
}
