package donnu.zolotarev.SpaceShip.Weapons.Modificator;

public class SpeedModificator extends DamageModificator{


    private float parsent;
    public SpeedModificator(float v) {
        super(0,Mode.PERCENT);
        parsent = -v;
    }

    @Override
    public float use(float base) {
        damage = (int)base;
        return super.use(parsent);
    }

    @Override
    public Target getTarget() {
        return Target.SPEED_FIRE;
    }
}
