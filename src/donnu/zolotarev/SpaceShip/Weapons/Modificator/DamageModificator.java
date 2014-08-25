package donnu.zolotarev.SpaceShip.Weapons.Modificator;

public class DamageModificator implements IWeaponModificator{

    private final int damage;

    public DamageModificator(int damage) {
      this.damage = damage;
    }

    @Override
    public int addDamage(int damage) {
        return damage + this.damage;
    }
}
