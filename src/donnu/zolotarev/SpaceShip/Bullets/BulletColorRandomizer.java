package donnu.zolotarev.SpaceShip.Bullets;

import donnu.zolotarev.SpaceShip.Weapons.Modificator.BulletFrameNumberModificator;
import donnu.zolotarev.SpaceShip.Weapons.Modificator.IWeaponModificator;

import java.util.Random;

public class BulletColorRandomizer {

    private static Random random = new Random();
    private static int heroColor = 0;

    public static IWeaponModificator getNextColorForEmeny(){
        int next;
        do{
           next = getNext();
        }while (next == heroColor);

        return new BulletFrameNumberModificator(next);
    }

    public static IWeaponModificator getNextColorForHero() {
        heroColor = getNext();
        return new BulletFrameNumberModificator(heroColor);
    }

    private static int getNext(){
        return random.nextInt(SimpleBullet.BULLET_FRAME_COUNT);
    }
}
