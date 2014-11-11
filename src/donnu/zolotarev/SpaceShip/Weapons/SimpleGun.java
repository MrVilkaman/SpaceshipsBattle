package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;
import donnu.zolotarev.SpaceShip.Weapons.Modificator.IWeaponModificator;

import java.util.Random;

public class SimpleGun extends Guns implements IGun {

    public SimpleGun(boolean heroWeapon, int bullitType,IWeaponModificator modificator) {
        super(heroWeapon,bullitType,modificator);
        switch (bullitType){
            case BaseBullet.TYPE_ROCKET_AUTO:
                ATTACK_INTERVAL = 50;
                break;
            default:
                ATTACK_INTERVAL = 5;
        }
        if (!heroWeapon){
            Random random = new Random(this.hashCode());

            switch (bullitType){
                case BaseBullet.TYPE_ROCKET_AUTO:
                    ATTACK_INTERVAL *= 3;
                    break;
                case BaseBullet.TYPE_ROCKET:
                    ATTACK_INTERVAL *= 25;
                    break;
                default:
                    ATTACK_INTERVAL *= 12 + random.nextInt(5);
            }
        }

        if (modificator != null){
            switch (modificator.getTarget()){
                case SPEED_FIRE:
                    ATTACK_INTERVAL = (int)modificator.use(ATTACK_INTERVAL);
                    break;
            }
        }
    }

    @Override
    protected void initGunPos() {
        weaponPosIterator.add(new WeaponPos(null,0,0,0));
    }


}
