package donnu.zolotarev.SpaceShip.Scenes;

import android.content.Context;
import com.google.gson.Gson;
import donnu.zolotarev.SpaceShip.GameData.HeroFeatures;
import donnu.zolotarev.SpaceShip.GameData.UserData;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.Levels.LevelController;
import donnu.zolotarev.SpaceShip.Levels.WaveContainer;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.IHardKey;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import org.andengine.entity.scene.Scene;

public abstract class MyScene extends Scene implements IHardKey {

    protected static final String FILE_GAME_DATA = "file_game_data";
    protected static final String FILE_LEVELS = "file_levels";

    protected static final String PREF_USER_STATS = "pref_user_stats";
    protected static final String PREF_LEVELS = "pref_levels";

    public MyScene(IParentScene parentScene) {
    }

    public void saveGameState(){
        UserData data =  UserData.get();
        Gson gson = new Gson();

        SpaceShipActivity shipActivity =  SpaceShipActivity.getInstance();
        shipActivity.getSharedPreferences(FILE_GAME_DATA, Context.MODE_PRIVATE)
                .edit().putString(PREF_USER_STATS,gson.toJson(data))
                .commit();
    }

    public void loadGame(){
        SpaceShipActivity shipActivity =  SpaceShipActivity.getInstance();
        UserData.create(shipActivity.getSharedPreferences(FILE_GAME_DATA, Context.MODE_PRIVATE).
                getString(PREF_USER_STATS,""));

        // ToDo загрузка данных
        HeroFeatures.create("");

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
                .edit().putString(PREF_USER_STATS,"")
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
            levels.addLevel(WaveContainer.LEVEL_INFINITY, 100, 100, true);
            levels.addLevel(WaveContainer.LEVEL_1, 200,300, false);
            levels.addLevel(WaveContainer.LEVEL_2, 300,350, false);
            levels.addLevel(WaveContainer.LEVEL_3, 400,180, false);
            levels.addLevel(WaveContainer.LEVEL_4, 500,180, false);
            levels.addLevel(WaveContainer.LEVEL_5, 600,300, false);
            levels.addLevel(WaveContainer.LEVEL_6, 800,400, false);
            levels.addLevel(WaveContainer.LEVEL_TEST, 700,180, false);
            levels.changeEnabled();
        }

        return levels;
    }

}
