package donnu.zolotarev.SpaceShip.Weapons;

import java.util.Iterator;

public interface IGun  {
    public void fire(WeaponPos weaponPos);
    public Iterator<WeaponPos> getWeaponPos();
    public void reload();
    public boolean shoot();

}
