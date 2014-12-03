package donnu.zolotarev.SpaceShip.Utils;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;

public class VibroHelper {

    private static Vibrator v;

    public static void launch(Activity context) {
        v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public static void clear() {
       v = null;
    }

    public static void vibroGetDamage(){

        try {
            if (v != null ){
                v.vibrate(100);
            }
        } catch (Exception e){
        }


    }
}
