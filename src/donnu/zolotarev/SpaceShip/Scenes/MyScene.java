package donnu.zolotarev.SpaceShip.Scenes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;
import com.google.gson.Gson;
import donnu.zolotarev.SpaceShip.GameData.HeroFeatures;
import donnu.zolotarev.SpaceShip.GameData.ShopData;
import donnu.zolotarev.SpaceShip.GameData.UserData;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.Levels.LevelController;
import donnu.zolotarev.SpaceShip.Levels.WaveContainer;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.IActivityCallback;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
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

    public void saveGameState(){
        Gson gson = new Gson();

        SpaceShipActivity shipActivity =  SpaceShipActivity.getInstance();
        shipActivity.getSharedPreferences(FILE_GAME_DATA, Context.MODE_PRIVATE)
                .edit()
                .putString(PREF_USER_STATS,gson.toJson(UserData.get()))
                .putString(PREF_HERO_STATS,gson.toJson(HeroFeatures.get()))
                .putString(PREF_SHOP_ITEMS,gson.toJson(ShopData.get()))
                .commit();
    }

    public void loadGame(){
        SpaceShipActivity shipActivity =  SpaceShipActivity.getInstance();
        UserData.create(shipActivity.getSharedPreferences(FILE_GAME_DATA, Context.MODE_PRIVATE).
                getString(PREF_USER_STATS,""));

        HeroFeatures.create(shipActivity.getSharedPreferences(FILE_GAME_DATA, Context.MODE_PRIVATE).
                getString(PREF_HERO_STATS,""));
        ShopData.create(shipActivity.getSharedPreferences(FILE_GAME_DATA, Context.MODE_PRIVATE).
                getString(PREF_SHOP_ITEMS,""));

    }

    public void saveLevels(LevelController levels){
        SpaceShipActivity shipActivity =  SpaceShipActivity.getInstance();
        shipActivity.getSharedPreferences(FILE_LEVELS, Context.MODE_PRIVATE)
                .edit().putString(PREF_LEVELS,levels.toJson())
                .commit();
    }

    public boolean haveCurrentGame(){
        SpaceShipActivity shipActivity =  SpaceShipActivity.getInstance();
        String levelsJson =  shipActivity.getSharedPreferences(FILE_LEVELS, Context.MODE_PRIVATE)
                .getString(PREF_LEVELS,"");
        return !levelsJson.isEmpty();
    }

    public void clearCurrentGame(){
        SpaceShipActivity shipActivity =  SpaceShipActivity.getInstance();
        shipActivity.getSharedPreferences(FILE_GAME_DATA, Context.MODE_PRIVATE)
                .edit()
                .putString(PREF_USER_STATS,"")
                .putString(PREF_HERO_STATS,"")
                .putString(PREF_SHOP_ITEMS,"")
                .commit();
        shipActivity.getSharedPreferences(FILE_LEVELS, Context.MODE_PRIVATE)
                .edit().putString(PREF_LEVELS,"")
                .commit();
    }

    public LevelController loadLevels(){
        LevelController levels;
        SpaceShipActivity shipActivity =  SpaceShipActivity.getInstance();
        String levelsJson =  shipActivity.getSharedPreferences(FILE_LEVELS, Context.MODE_PRIVATE)
                .getString(PREF_LEVELS,"");
        if (!levelsJson.isEmpty()){
            levels = new LevelController(levelsJson);
        } else {
            levels = new LevelController();
     //       levels.addLevel(WaveContainer.LEVEL_INFINITY, 100, 100, true);

            int dx = 1;
            int minX = -2;
            int maxX = 2;
            int x = minX;
            int dy = 50;
            int y = 15;
            for (int i = WaveContainer.LEVEL_1; i <= WaveContainer.LEVEL_19; i++) {
                if(i == 11){
                    dy += 25;
                }
                y += dy;
               levels.addLevel(i,y,350+60*x, false);
                x += dx;
                if (x == maxX || x == minX){
                    dx *= -1;
                }
            }

            levels.addLevel(WaveContainer.LEVEL_TEST, 200,600, false);
            levels.addLevel(WaveContainer.LEVEL_MUSEUM, 400,600, false);
            levels.changeEnabled();
        }

        return levels;
    }

    public void toast(final String msg){
        final SpaceShipActivity shipActivity =  SpaceShipActivity.getInstance();
        shipActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(shipActivity, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean actualCodeVersion(){
        PackageInfo packinfo = null;
        SpaceShipActivity shipActivity =  SpaceShipActivity.getInstance();
        try {
            packinfo = shipActivity.getPackageManager().getPackageInfo("donnu.zolotarev.SpaceShip", PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
        }

        if (codeVersion == -1){
            codeVersion =  shipActivity.getSharedPreferences(FILE_GAME_DATA, Context.MODE_PRIVATE)
                    .getInt(PREF_LAST_CODE_VERSION,-1);

            shipActivity.getSharedPreferences(FILE_GAME_DATA, Context.MODE_PRIVATE).edit()
                    .putInt(PREF_LAST_CODE_VERSION,packinfo.versionCode ).commit();

        }

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
