package donnu.zolotarev.SpaceShip.Weapons.Modificator;

public class RotateAngleModificator extends DamageModificator {
    public RotateAngleModificator(float damage, Mode mode) {
        super(damage, mode);
    }

    @Override
    public Target getTarget() {
        return Target.ROTATE_ANGLE;
    }
}
