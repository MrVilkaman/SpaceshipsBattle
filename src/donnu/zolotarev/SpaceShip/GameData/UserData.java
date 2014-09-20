package donnu.zolotarev.SpaceShip.GameData;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class UserData {

    private static transient UserData instance;

    private int money = 0;

    private UserData() {
    }

    public static UserData get(){
        return instance;
    }

    public static void create(String s){
        if (!s.isEmpty()){
            try {
                Gson gson = new Gson();
                instance = gson.fromJson(s,new TypeToken<UserData>(){}.getType());
            } catch (JsonSyntaxException e) {
                instance = new UserData();
            }
        } else {
            instance = new UserData();
        }
    }

    public int getMoney() {
        return money;
    }

    void setMoney(int money) {
        this.money = money;
    }
}
