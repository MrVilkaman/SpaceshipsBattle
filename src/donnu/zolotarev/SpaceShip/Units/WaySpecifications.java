package donnu.zolotarev.SpaceShip.Units;

public class WaySpecifications {
    //Полетные характеристики
    private boolean isUsed = false;

    private float maxRotationAndle;
    private int speed;

    public WaySpecifications(int i, float v) {
        setAll(i,v);
    }

    public WaySpecifications() {
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

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public void setAll(int default_speed, float default_rotate_angle) {
        speed  = default_speed;
        maxRotationAndle = default_rotate_angle;
        isUsed = true;
    }
}
