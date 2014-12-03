package donnu.zolotarev.SpaceShip.Levels;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class LevelController {

    private static final String KEY_ITEMS = "KEY_ITEMS";
    private static final String KEY_EXCESS_COUNTER = "KEY_EXCESS_COUNTER";
    private static transient LevelController instance;

    private LinkedHashMap<Integer,LevelInfo> levels;
    private int excessCounter = 0;

    public static LevelController getInstance() {
        if (instance == null){
            instance = new LevelController();
        }
        return instance;
    }

    public static void clearInstance(){
        instance.levels.clear();
        instance.levels = null;

        instance = null;
    }

    public void load(String s){
        Gson gson = new Gson();
        excessCounter = 0;
        try {
            JSONObject json = new JSONObject(s);
            excessCounter = json.getInt(KEY_EXCESS_COUNTER);
            levels = sort((LinkedHashMap<Integer,LevelInfo>)gson.fromJson(json.getString(KEY_ITEMS),new TypeToken<LinkedHashMap<Integer,LevelInfo>>(){}.getType()));

        changeEnabled();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void clear(){
        excessCounter = 0;
        levels.clear();

    }

    private LevelController() {
        levels = new LinkedHashMap<Integer, LevelInfo>();
    }

    public void addLevel(int levelId, boolean isInfinity){
        if( !(WaveContainer.LEVEL_MIN <= levelId && levelId <= WaveContainer.LEVEL_MAX)){
            excessCounter++;
        }
        levels.put(levelId, new LevelInfo(levelId, isInfinity)) ;
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
        JSONObject object = new JSONObject();
        try {
            object.put(KEY_ITEMS,gson.toJson(levels));
            object.put(KEY_EXCESS_COUNTER, excessCounter);
        } catch (JSONException e) {
        }
        return object.toString();
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

    private LinkedHashMap<Integer,LevelInfo> sort(LinkedHashMap<Integer,LevelInfo> o) {
        List<Map.Entry<Integer,LevelInfo>> entries =
                new ArrayList<Map.Entry<Integer,LevelInfo>>(o.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<Integer,LevelInfo>>() {
            public int compare(Map.Entry<Integer,LevelInfo> a, Map.Entry<Integer,LevelInfo> b) {
                return a.getKey().compareTo(b.getKey());
            }
        });
        LinkedHashMap<Integer,LevelInfo> sortedMap = new LinkedHashMap<Integer,LevelInfo>();
        for (Map.Entry<Integer,LevelInfo> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
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
