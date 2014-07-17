package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.WeaponPos;

public interface IGun {
    public void fire(WeaponPos weaponPos);
    public void reload();
    public boolean shoot();

}
