package donnu.zolotarev.SpaceShip.Weapons.Modificator;

public interface IWeaponModificator {
    public enum Mode{
        Add,
        Change
    }

    public int addDamage(int damage);
    public Mode getMode();
}
