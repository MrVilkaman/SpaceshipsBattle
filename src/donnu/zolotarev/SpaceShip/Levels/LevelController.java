package donnu.zolotarev.SpaceShip.Levels;

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
        changeEnabled();
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
        int counter = 0;
        for (int i = 0;i<levels.size();i++){
            int index = i-1;
            if(0<index){
                levels.get(i).setEnabled(levels.get(index).isWin());
            }else{
                levels.get(i).setEnabled(true);
            }
                levels.get(i).setEnabled(true);
        }
    }

    public LevelInfo getById(int type) {
        return  levels.get(type);
    }
}
