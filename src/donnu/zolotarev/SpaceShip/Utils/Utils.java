package donnu.zolotarev.SpaceShip.Utils;

public class Utils {
    public static double degreeToRad(double degree){
        return (degree* Math.PI)/180;
    }


    public static float random(float lower, float upper){
        return Math.round(Math.random() * (upper - lower)) + lower;
    }
}
