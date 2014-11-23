package donnu.zolotarev.SpaceShip.Weapons.Modificator;

public class BulletFrameNumberModificator implements IWeaponModificator {

    private int frameCount = 0;

    public BulletFrameNumberModificator(int frameCount) {
        this.frameCount = frameCount;
    }

    @Override
    public float use(float damage) {
        return frameCount;
    }

    @Override
    public Mode getModificator() {
        return null;
    }

    @Override
    public Target getTarget() {
        return Target.SPEED_FIRE;
    }
}
