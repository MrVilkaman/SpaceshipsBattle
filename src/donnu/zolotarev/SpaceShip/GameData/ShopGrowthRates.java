package donnu.zolotarev.SpaceShip.GameData;

public class ShopGrowthRates {
    public enum RatesModels{
        LINEARLY,
        PARABOLA,
        SPECIAL_1,
    }

    static int getPriceForLevel(RatesModels models,int base,float diff,float level){
        float i = 0;
        switch (models){
            case LINEARLY:
                i = base+diff*level;
                break;
            case PARABOLA:
                i = base+diff*level*level;
                break;
            case SPECIAL_1:
                i = base*(1 +diff*level);
                break;
        }

        return (int)i;
    }
}
