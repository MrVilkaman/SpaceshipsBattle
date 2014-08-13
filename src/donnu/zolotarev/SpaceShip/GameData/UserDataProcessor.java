package donnu.zolotarev.SpaceShip.GameData;

import java.util.Random;

public class UserDataProcessor {
    private static UserDataProcessor instance;

    private Random random;
    private UserDataProcessor() {
        random = new Random();
    }

    public void processGold(boolean isNew,boolean isWin){
        UserData userData = UserData.get();
        int money = (isWin? 4:1)*(random.nextInt(5)+5)*5*(isNew?2:1);
        userData.setMoney(userData.getMoney()+money);
    }

    public void processGold(int score,boolean isWin){
        UserData userData = UserData.get();
        int money = score/3 *(isWin?2:1);
        userData.setMoney(userData.getMoney()+money);
    }

    public static UserDataProcessor get(){
        if (instance == null){
            instance = new UserDataProcessor();
        }
         return instance;
    }

    public boolean buy(int priceItem){
        UserData userData = UserData.get();
        int lostMoney = userData.getMoney() - priceItem;
        if (lostMoney >= 0){
           userData.setMoney(lostMoney);
           return true;
        }
        return false;
    }
}
