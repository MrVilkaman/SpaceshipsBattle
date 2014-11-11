package donnu.zolotarev.SpaceShip.Weapons.Modificator;

public class SpeedFireModificator extends DamageModificator{


    private float parsent;
    public SpeedFireModificator(float v, Mode m) {
        super(0,m);
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
