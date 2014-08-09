package donnu.zolotarev.SpaceShip.GameData;

import java.util.Random;

public class UserDataProcessor {
    private static UserDataProcessor instance;

    private final UserData userData;
    private Random random;
    private UserDataProcessor() {
        userData = UserData.get();
        random = new Random();
    }

    public void processGold(boolean isNew,boolean isWin){
        int money = (isWin? 4:1)*(random.nextInt(5)+5)*5*(isNew?2:1);
        userData.setMoney(userData.getMoney()+money);
    }

    public static UserDataProcessor get(){
        if (instance == null){
            instance = new UserDataProcessor();
        }
         return instance;
    }
}
