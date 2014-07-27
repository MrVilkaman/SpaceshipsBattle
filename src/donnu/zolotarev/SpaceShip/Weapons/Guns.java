package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;

public abstract class Guns {
    private final int bullitType;
    protected int ATTACK_INTERVAL;
    protected int shootDelay = 0;
    protected boolean isShoot = false;
    private boolean targetUnit;

    public Guns(boolean heroWeapon, int bullitType) {
        targetUnit = heroWeapon;
        this.bullitType = bullitType;
    }

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
        BaseBullet.getBullet(bullitType).init(x, y, direction, targetUnit);
    }
}
