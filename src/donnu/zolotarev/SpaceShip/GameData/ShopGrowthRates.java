package donnu.zolotarev.SpaceShip.GameData;

public class ShopGrowthRates {
    public enum RatesModels{
        LINEARLY,
        PARABOLA
    }

    static int getPriceForLevel(RatesModels models,int base,int diff,int level){
        int i = 0;
        switch (models){
            case LINEARLY:
                i = base+diff*level;
                break;
            case PARABOLA:
                i = base+diff*level*level;
                base = i;

        }

        return i;
    }
}
