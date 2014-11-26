package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;
import donnu.zolotarev.SpaceShip.Weapons.Modificator.IWeaponModificator;

import java.util.ArrayList;

public abstract class Guns implements IGun {
    private final int bullitType;
    private final IWeaponModificator[] modificator;
    protected ArrayList<WeaponPos> weaponPosIterator;

    protected int ATTACK_INTERVAL;
    protected int shootDelay = 0;
    protected boolean isShoot = false;
    private boolean targetUnit;
    private int bulletFrameNumber = 0;

    public Guns(boolean heroWeapon, int bullitType,IWeaponModificator[] modificator) {
        targetUnit = heroWeapon;
        this.bullitType = bullitType;
        this.modificator = modificator;
        weaponPosIterator = new ArrayList<WeaponPos>();
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
        BaseBullet.getBullet(bullitType).init(x, y, direction, bullitType, targetUnit,modificator,bulletFrameNumber);
    }

    @Override
    public ArrayList<WeaponPos> getWeaponPos() {
        return weaponPosIterator;
    }

    @Override
    public void fire(WeaponPos weaponPos) {
        GetNewBullet(weaponPos.x, weaponPos.y, weaponPos.anlge);
    }

    protected void applyModificator(IWeaponModificator[] modificator) {
        if (modificator != null){
            for (IWeaponModificator m :modificator) {
                switch (m.getTarget()){
                    case SPEED_FIRE:
                        ATTACK_INTERVAL = (int)m.use(ATTACK_INTERVAL);
                        break;
                    case BULLET_FRAME_NUMBER:
                        bulletFrameNumber = (int)m.use(0);
                        break;
                }
            }
        }
    }
}
