package donnu.zolotarev.SpaceShip.Utils;

import android.graphics.PointF;

import java.util.Random;

public class Utils {
    private static float dx;
    private static float dy;
    private static float angle;
    private static Random random = new Random();

    public static double degreeToRad(double degree){
        return (degree* Math.PI)/180;
    }

    public static float random(float lower, float upper){
        return Math.round(Math.random() * (upper - lower)) + lower;
    }

    public static boolean equals(float numb1,float numb2){
        return equals(numb1,numb2,0.0001f);
    }

    public static boolean equals(float numb1,float numb2, float accuracy){
        return (Math.abs(numb1 - numb2) <= accuracy);
    }

    public static boolean inRadius(float numb1,float numb2){
        return Math.abs(numb1)<= Math.abs(numb2) ;
    }

    public static int getSign(float v){
         if (v>=0){
            return 1;
         }else{
            return -1;
         }
    }

    public static float getAngle(float x1, float y1,float x2,float y2 ){
       return getAngle(x1, y1, x2, y2, true);
    }

    public static float getAngle(float x1, float y1,float x2,float y2, boolean flag ){
        dx = x2 - x1;
        dy = y2 - y1;
        dx = x2 - x1;
        dy = y2 - y1;
        angle = (float)( Math.atan2(dy, dx) / Math.PI * 180);
        if (flag){
            if (angle < 0)
            {
                angle = 360 + angle;
            }
            else if (angle >= 360)
            {
                angle = angle - 360;
            }
        }
        return angle;
    }

    public static float dAngleDegree(float a1,float a2){
        float da = a1 - a2;
        if (da > 180) {
            da = -360 + da;
        } else if (da < -180) {
            da = 360 + da;
        }
        return da;
    }

    public static float dAngleDegree2(float a1,float a2){
        float da = a1 - a2;
        /*if (da > 180) {
            da = -360 + da;
        } else if (da < -180) {
            da = 360 + da;
        }*/
        return da;
    }


    public static float distance(float x1, float y1, float x2, float y2){
        dx = x2 - x1;
        dy = y2 - y1;
        return (float)Math.sqrt(dx * dx + dy * dy);
    }

    public static float distance(PointF f, PointF f2){
        dx = f2.x - f.x;
        dy = f2.y - f.y;
        return (float)Math.sqrt(dx * dx + dy * dy);
    }

    public static float distanceSqr(float x1, float y1, float x2, float y2){
        dx = x2 - x1;
        dy = y2 - y1;
        return (dx * dx + dy * dy);
    }

    public static float distanceSqr(PointF f, PointF f2){
        dx = f2.x - f.x;
        dy = f2.y - f.y;
        return (dx * dx + dy * dy);
    }

    public static double toDegrees(float radians){
        return radians * 180 / Math.PI;
    }

    public static double toRadians(float degrees){
        return degrees * Math.PI / 180;
    }

    public static int getRandom(int i) {
        return (int)Math.round(Math.random()*i);
    }
}
