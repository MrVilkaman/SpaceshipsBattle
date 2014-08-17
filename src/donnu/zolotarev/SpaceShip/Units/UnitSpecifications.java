package donnu.zolotarev.SpaceShip.Units;

public class UnitSpecifications {
    //Полетные характеристики
    private float maxRotationAndle;
    private int speed;

    private int health;

    public UnitSpecifications(int health,int speed,float maxRotationAndle) {
        this.health = health;
        this.speed = speed;
        this.maxRotationAndle = maxRotationAndle;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public float getMaxRotationAndle() {
        return maxRotationAndle;
    }

    public void setMaxRotationAndle(float maxRotationAndle) {
        this.maxRotationAndle = maxRotationAndle;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

}
