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
        if (!item.isInfinity()){
            item.setWin(win);
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
        for (int i = 0;i<levels.size();i++){
            int index = i-2;
            int index2 = i-1;
           if(0<index){
              /*if (levels.get(index2).isWin()){
                  levels.get(i).setEnabled(true);
              }
               if (!levels.get(index).isWin()){
                   levels.get(i).setEnabled(false);
               }*/

               levels.get(i).setEnabled((levels.get(index2).isWin()|| levels.get(index).isWin()) && levels.get(i-3).isWin());
           }else{
               levels.get(i).setEnabled(true);
           }
        }
    }
}
