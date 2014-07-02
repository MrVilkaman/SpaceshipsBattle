package donnu.zolotarev.SpaceShip.Bullets;

import donnu.zolotarev.SpaceShip.Weapons.SimpleGun;

public class SimpleBullet extends BulletBase {

    public SimpleBullet(SimpleGun simpleGun) {
        super(simpleGun);
        DEFAULT_SPEED = 500;
    }
}

