package donnu.zolotarev.SpaceShip.Units;

public class WaySpecifications {
    //Полетные характеристики
    private float maxRotationAndle;
    private int speed;

    public WaySpecifications(int speed, float maxRotationAndle) {
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
}
