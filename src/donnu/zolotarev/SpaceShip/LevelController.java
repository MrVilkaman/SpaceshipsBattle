package donnu.zolotarev.SpaceShip;

import java.util.ArrayList;
import java.util.Iterator;

public class LevelController {


    private final ArrayList<LevelInfo> levels;

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


}
