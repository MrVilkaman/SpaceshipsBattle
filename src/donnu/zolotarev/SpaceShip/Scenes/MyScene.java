package donnu.zolotarev.SpaceShip.Scenes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;
import donnu.zolotarev.SpaceShip.Activity.GameActivity;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.IActivityCallback;
import org.andengine.entity.scene.Scene;

public abstract class MyScene extends Scene implements IActivityCallback {

    protected static final String FILE_SYSTEM_DATA = "file_system_data";

    protected static final String FILE_GAME_DATA = "file_game_data";
    protected static final String FILE_LEVELS = "file_levels";

    protected static final String PREF_USER_STATS = "pref_user_stats";
    protected static final String PREF_HERO_STATS = "pref_hero_stats";
    protected static final String PREF_LEVELS = "pref_levels";
    private static final String PREF_SHOP_ITEMS = "pref_shop_items";

    private static final String PREF_LAST_CODE_VERSION = "pref_last_code_version";
    private static int codeVersion = -1;

    public MyScene(IParentScene parentScene) {
    }


    public void toast(final String msg){
        final GameActivity shipActivity =  GameActivity.getInstance();
        shipActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(shipActivity, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean actualCodeVersion(){
        PackageInfo packinfo = null;
        GameActivity shipActivity =  GameActivity.getInstance();
        try {
            packinfo = shipActivity.getPackageManager().getPackageInfo("donnu.zolotarev.SpaceShip", PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
        }


            codeVersion =  shipActivity.getSharedPreferences(FILE_GAME_DATA, Context.MODE_PRIVATE)
                    .getInt(PREF_LAST_CODE_VERSION,-1);

            shipActivity.getSharedPreferences(FILE_GAME_DATA, Context.MODE_PRIVATE).edit()
                    .putInt(PREF_LAST_CODE_VERSION,packinfo.versionCode ).commit();

     //   }

        return packinfo.versionCode == codeVersion;
    }


    public static void showAlert(Context ctx, int messageId, String title){
        showAlert(ctx, ctx.getString(messageId), title);
    }

    public static void showAlert(Context ctx, String message, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        if(title == null){
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setTitle("Alert");
        } else {
            builder.setTitle(title);
        }
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        builder.show();
    }

}
