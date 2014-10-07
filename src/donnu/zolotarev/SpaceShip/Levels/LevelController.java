package donnu.zolotarev.SpaceShip.Levels;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LevelController {

    private static transient LevelController instance = new LevelController();

    private HashMap<Integer,LevelInfo> levels;
    private int excessCounter = 0;

    public static LevelController getInstance() {
        return instance;
    }

    public void load(String s){
        Gson gson = new Gson();
        excessCounter = 0;
        levels = gson.fromJson(s,new TypeToken<Map<Integer,LevelInfo>>(){}.getType());
        changeEnabled();
    }

    private LevelController() {
        excessCounter = 0;
        levels = new HashMap<Integer, LevelInfo>();
    }

    public void addLevel(int levelId, boolean isInfinity){
        if( !(WaveContainer.LEVEL_1 <= levelId && levelId <= WaveContainer.LEVEL_19)){
            excessCounter++;
        }
        levels.put(levelId, new LevelInfo(levelId, isInfinity));
    }

    public void changeStateById(int id, boolean win){
        LevelInfo item = levels.get(id);
        if (!item.isInfinity()  ){
            item.setWin(win || item.isWin());
            item.setNew(false);
        }
    }

    public boolean newestById(int id){
        LevelInfo item = levels.get(id);
        return item.isNew() && !item.isInfinity();
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(levels);
    }

    public void changeEnabled(){
        Iterator<Integer> iter =  levels.keySet().iterator();
        Boolean isWin = true;
        while (iter.hasNext()) {
            LevelInfo item = levels.get(iter.next());
            item.setEnabled(isWin);
            isWin = item.isWin();
        }
    }

    public LevelInfo getById(int type) {
        return levels.get(type);
    }

    public int getSize(){
       return levels.size() - excessCounter;
    }

    public LevelInfo getLevelInfoForAdapter(int pos){
        return levels.get(WaveContainer.LEVEL_1+pos);
    }

}
