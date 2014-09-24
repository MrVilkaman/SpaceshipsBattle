package donnu.zolotarev.SpaceShip.Fragments;

import android.content.Context;
import donnu.zolotarev.SpaceShip.GameData.HeroFeatures;
import donnu.zolotarev.SpaceShip.GameData.ShopData;
import donnu.zolotarev.SpaceShip.GameData.UserData;
import donnu.zolotarev.SpaceShip.Levels.LevelController;
import donnu.zolotarev.SpaceShip.Levels.WaveContainer;

public abstract class BaseMenuFragment extends BaseFragment {

    protected static final String FILE_GAME_DATA = "file_game_data";
    protected static final String FILE_LEVELS = "file_levels";

    protected static final String PREF_USER_STATS = "pref_user_stats";
    protected static final String PREF_HERO_STATS = "pref_hero_stats";
    protected static final String PREF_LEVELS = "pref_levels";
    private static final String PREF_SHOP_ITEMS = "pref_shop_items";

    private static final String PREF_LAST_CODE_VERSION = "pref_last_code_version";
    private static int codeVersion = -1;

    public void loadGame(){

        UserData.create(getActivity().getSharedPreferences(FILE_GAME_DATA, Context.MODE_PRIVATE).
                getString(PREF_USER_STATS, ""));
        HeroFeatures.create(getActivity().getSharedPreferences(FILE_GAME_DATA, Context.MODE_PRIVATE).
                getString(PREF_HERO_STATS, ""));
        ShopData.create(getActivity().getSharedPreferences(FILE_GAME_DATA, Context.MODE_PRIVATE).
                getString(PREF_SHOP_ITEMS, ""));

    }

    public void saveLevels(LevelController levels){
        getActivity().getSharedPreferences(FILE_LEVELS, Context.MODE_PRIVATE)
                .edit().putString(PREF_LEVELS,levels.toJson())
                .commit();
    }

    public LevelController loadLevels(){
        LevelController levels;
        String levelsJson =  getActivity().getSharedPreferences(FILE_LEVELS, Context.MODE_PRIVATE)
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

            // levels.addLevel(WaveContainer.LEVEL_TEST, 200,600, false);
            // levels.addLevel(WaveContainer.LEVEL_MUSEUM, 400,600, false);
            levels.changeEnabled();
        }

        return levels;
    }
}
