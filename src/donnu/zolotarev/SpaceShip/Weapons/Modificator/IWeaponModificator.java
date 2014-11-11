package donnu.zolotarev.SpaceShip.Weapons.Modificator;

public interface IWeaponModificator {
    public enum Mode{
        ADD,
        CHANGE,
        PERCENT
    }

    public enum Target{
        DAMAGE,
        SPEED_FIRE,
        ROTATE_ANGLE
    }

    public float use(float damage);
    public Mode getModificator();
    public Target getTarget();
}
