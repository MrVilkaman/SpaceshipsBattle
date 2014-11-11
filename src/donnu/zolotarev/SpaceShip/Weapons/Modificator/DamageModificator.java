package donnu.zolotarev.SpaceShip.Weapons.Modificator;

public class DamageModificator implements IWeaponModificator{

    protected int damage;
    protected final Mode mode;

    public DamageModificator(int damage, Mode mode) {
        this.damage = damage;
        this.mode = mode;
    }

    @Override
    public float use(float damage) {
        switch (mode){
            case ADD:
                return damage + this.damage;
            case CHANGE:
                return this.damage;
            case PERCENT:
                return this.damage *( 1+ damage) ;
            default:
                return damage;
        }
    }

    @Override
    public Mode getModificator() {
        return mode;
    }

    @Override
    public Target getTarget() {
        return Target.DAMAGE;
    }
}
