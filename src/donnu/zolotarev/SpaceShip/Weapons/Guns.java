package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.Bullets.BulletBase;
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
        int type;
        if (direction == 0){
            type = BulletBase.TYPE_SIMPLE_BULLET;
        } else {
            type = BulletBase.TYPE_SIMPLE_BULLET_2;
        }
        BulletBase.getBullet(type).init(x, y, direction, targetUnit);
    }
}
