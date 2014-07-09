package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.Bullets.SimpleBullet;
import donnu.zolotarev.SpaceShip.Bullets.SimpleBullet2;
import donnu.zolotarev.SpaceShip.WeaponPos;

public abstract class Guns {
    protected int ATTACK_INTERVAL;
    protected int shootDelay = 0;
    protected boolean isShoot = false;
    private boolean targetUnit;

    public Guns(boolean heroWeapon) {
        targetUnit = heroWeapon;
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

    public abstract void fire(WeaponPos weaponPos);

    protected void GetNewBullet(final float x, final float y, final float direction) {
        //    final SimpleBullet bullet;
        if (direction == 0){
            SimpleBullet.getBullet().init(x, y, direction, targetUnit);
        } else {
            SimpleBullet2.getBullet().init(x, y, direction, targetUnit);
        }
    }
}
