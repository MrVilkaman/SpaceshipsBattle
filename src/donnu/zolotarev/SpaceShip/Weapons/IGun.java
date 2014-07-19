package donnu.zolotarev.SpaceShip.Weapons;

public interface IGun {
    public void fire(WeaponPos weaponPos);
    public void reload();
    public boolean shoot();

}
