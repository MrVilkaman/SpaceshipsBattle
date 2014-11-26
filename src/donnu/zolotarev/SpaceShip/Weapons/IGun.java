package donnu.zolotarev.SpaceShip.Weapons;

import java.util.ArrayList;

public interface IGun  {
    public void fire(WeaponPos weaponPos);
    public ArrayList<WeaponPos> getWeaponPos();
    public void reload();
    public boolean shoot();

}
