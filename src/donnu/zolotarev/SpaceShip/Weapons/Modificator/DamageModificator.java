package donnu.zolotarev.SpaceShip.Weapons.Modificator;

public class DamageModificator implements IWeaponModificator{

    private final int damage;
    private final Mode mode;

    public DamageModificator(int damage, Mode mode) {
      this.damage = damage;
        this.mode = mode;
    }

    @Override
    public int addDamage(int damage) {
        switch (mode){
            case Add:
                return damage + this.damage;
            case Change:
                return this.damage;
            default:
                return damage;
        }
    }

    @Override
    public Mode getMode() {
        return mode;
    }
}
