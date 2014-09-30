package donnu.zolotarev.SpaceShip.Fragments;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.gson.Gson;
import donnu.zolotarev.SpaceShip.GameData.HeroFeatures;
import donnu.zolotarev.SpaceShip.GameData.Shop;
import donnu.zolotarev.SpaceShip.GameData.UserData;
import donnu.zolotarev.SpaceShip.Levels.LevelController;
import donnu.zolotarev.SpaceShip.Levels.WaveContainer;

public abstract class BaseMenuFragment extends BaseFragment {

    protected static final String FILE_GAME_DATA = "file_game_data";
    protected static final String FILE_LEVELS = "file_levels";

    protected static final String PREF_USER_STATS = "pref_user_stats";
    protected static final String PREF_HERO_STATS = "pref_hero_stats";
    protected static final String PREF_LEVELS = "pref_levels";
    protected static final String PREF_SHOP_ITEMS = "pref_shop_items";

    private static final String PREF_LAST_CODE_VERSION = "pref_last_code_version";
    private static int codeVersion = -1;

    public void loadGame(){

        UserData.create(getActivity().getSharedPreferences(FILE_GAME_DATA, Context.MODE_PRIVATE).
                getString(PREF_USER_STATS, ""));
        HeroFeatures.create(getActivity().getSharedPreferences(FILE_GAME_DATA, Context.MODE_PRIVATE).
                getString(PREF_HERO_STATS, ""));
        Shop.create(getActivity(),getActivity().getSharedPreferences(FILE_GAME_DATA, Context.MODE_PRIVATE).
                getString(PREF_SHOP_ITEMS, ""));
        loadLevels();
    }

    public void saveGameState(){
        Gson gson = new Gson();
        getActivity().getSharedPreferences(FILE_GAME_DATA, Context.MODE_PRIVATE)
                .edit()
                .putString(PREF_USER_STATS,gson.toJson(UserData.get()))
                .putString(PREF_HERO_STATS,gson.toJson(HeroFeatures.get()))
                .putString(PREF_SHOP_ITEMS,Shop.get().toJson())
                .commit();
        saveLevels();
    }

    public void saveLevels(){
        getActivity().getSharedPreferences(FILE_LEVELS, Context.MODE_PRIVATE)
                .edit().putString(PREF_LEVELS,LevelController.getInstance().toJson())
                .commit();
    }

    public void loadLevels(){
        LevelController levels = LevelController.getInstance();
        String levelsJson =  getActivity().getSharedPreferences(FILE_LEVELS, Context.MODE_PRIVATE)
                .getString(PREF_LEVELS,"");
        if (!levelsJson.isEmpty()){
           levels.load(levelsJson);
        } else {
            //       levels.addLevel(WaveContainer.LEVEL_INFINITY, 100, 100, true);

            for (int i = WaveContainer.LEVEL_1; i <= WaveContainer.LEVEL_19; i++) {
                levels.addLevel(i, false);
            }

            // levels.addLevel(WaveContainer.LEVEL_TEST, 200,600, false);
            // levels.addLevel(WaveContainer.LEVEL_MUSEUM, 400,600, false);
            levels.changeEnabled();
        }
        levels.getById(2).setEnabled(true);
        levels.getById(3).setEnabled(true);
        levels.changeStateById(3,true);
        levels.getById(3).setWin(true);
        levels.getById(3).setNew(false);
    }

    public boolean haveCurrentGame(){
        String levelsJson =  getActivity().getSharedPreferences(FILE_LEVELS, Context.MODE_PRIVATE)
                .getString(PREF_LEVELS,"");
        return !levelsJson.isEmpty();
    }

    public boolean actualCodeVersion(){
        PackageInfo packinfo = null;
        try {
            packinfo = getActivity().getPackageManager().getPackageInfo("donnu.zolotarev.SpaceShip", PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
        }


        codeVersion =  getActivity().getSharedPreferences(FILE_GAME_DATA, Context.MODE_PRIVATE)
                .getInt(PREF_LAST_CODE_VERSION,-1);

        getActivity().getSharedPreferences(FILE_GAME_DATA, Context.MODE_PRIVATE).edit()
                .putInt(PREF_LAST_CODE_VERSION,packinfo.versionCode ).commit();

        //   }

        return packinfo.versionCode == codeVersion;
    }
}
