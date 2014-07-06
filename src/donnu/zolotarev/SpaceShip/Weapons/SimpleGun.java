package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.Bullets.SimpleBullet;
import donnu.zolotarev.SpaceShip.Bullets.SimpleBullet2;
import donnu.zolotarev.SpaceShip.WeaponPos;

public class SimpleGun extends Guns {
    private static SimpleGun instance;
    protected int ATTACK_INTERVAL;
    protected int shootDelay = 0;
    private boolean isShoot = false;

    public SimpleGun() {
        instance = this;
        ///
        ATTACK_INTERVAL = 5;
    }

    private void GetNewBullet(final float x, final float y, final float direction) {
        //    final SimpleBullet bullet;
        if (direction == 0){
            SimpleBullet.getBullet().init(x, y, direction);
        } else {
            SimpleBullet2.getBullet().init(x, y, direction);
        }
    }

    public void fire(WeaponPos weaponPos) {
        GetNewBullet(weaponPos.x, weaponPos.y-5, weaponPos.anlge);
        GetNewBullet(weaponPos.x, weaponPos.y+5, weaponPos.anlge);
    }

    public boolean isShoot() {
        if (isShoot){
            shootDelay--;
            if (shootDelay < 0){
                shootDelay = ATTACK_INTERVAL;
                return true;
            }
        } else {
            shootDelay = 0;
        }
        return false;
    }

    public void setShoot(boolean isShoot) {
        this.isShoot = isShoot;
    }
}
