package donnu.zolotarev.SpaceShip;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class LevelController {


    private final ArrayList<LevelInfo> levels;

    public LevelController(String s){
        Gson gson = new Gson();
        levels = gson.fromJson(s,new TypeToken<Collection<LevelInfo>>(){}.getType());
    }

    public LevelController() {
        levels = new ArrayList<LevelInfo>();
    }

    public void addLevel(int levelId, int x, int y, boolean isInfinity){
        levels.add(new LevelInfo(levelId, x, y, isInfinity));
    }

    public Iterator<LevelInfo> getIterator() {
        return levels.iterator();
    }

    public void changeStateById(int id, boolean win){
        LevelInfo item = levels.get(id);
        if (!item.isInfinity()){
            item.setWin(win);
            item.setNew(false);
        }
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(levels);
    }
}
