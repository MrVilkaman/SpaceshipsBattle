package donnu.zolotarev.SpaceShip.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

public class AppUtils {

    public static boolean isOnline(Context ctx) {
        NetworkInfo netInfo = ((ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    public static void rateOnCall(Context context) {
        if (AppUtils.isOnline(context)) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id=donnu.zolotarev.SpaceShip"));
            context.startActivity(intent);
        }
    }
}
