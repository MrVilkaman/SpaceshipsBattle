package donnu.zolotarev.SpaceShip.GameData;

public class ShopItem {

    public enum ItemShopType {
        AMMO,
        DEFENCE
    }

    private int priceItemBuy;
    private int priceBuy;
    // count = -1 - Предмет НЕ куплен
    // count >= 0 = Предмет куплен, Количество оставшихся патронов или уровень, если isHaveAmmo = false.
    private int count;
    private int titleResId;
    private int descriptionResId;
    private ItemShopType type;
    private boolean isHaveAmmo = false;
    private final int maxLevel;
    private final ShopGrowthRates.RatesModels models;
    private final float priceDiff;

    private final ShopGrowthRates.RatesModels modelsEffect;
    private final int effectBase;
    private final float effectDiff;

    public ShopItem(int titleResId, int descriptionResId, ItemShopType type,int maxLevel
            ,ShopGrowthRates.RatesModels models,int priceBuy,float priceDiff
            ,ShopGrowthRates.RatesModels modelsEffect,int effectBase,float effectDiff) {
        this.titleResId = titleResId;
        this.descriptionResId = descriptionResId;
        this.type = type;
        this.priceBuy = priceBuy;
        this.priceDiff = priceDiff;
        this.maxLevel = maxLevel;
        this.models = models;
        this.modelsEffect = modelsEffect;
        this.effectBase = effectBase;
        this.effectDiff = effectDiff;
        count = 0;
    }

    public int getPriceBuy() {
        if (isHaveAmmo){
            return -1;
        }else{
            return ShopGrowthRates.getPriceForLevel(models, priceBuy,priceDiff,count);
        }
    }

    public int getCount() {
        return count;
    }

    public int getTitle() {
        return titleResId;
    }

    public int getDescriptionResId() {
        return descriptionResId;
    }

    public boolean isUseAmmo() {
        return isHaveAmmo;
    }

    public boolean alreadyBought(){
        return count != -1;
    }

    void buy() {
        if (!isUseAmmo()){
            count++;
        }else{
            // todo Сделать коэфициент
            count = 5;
        }
    }

    public boolean haveNext(){
        return count != maxLevel;
    }

    public int getLevelMax() {
        return maxLevel;
    }

    public int getEffect() {
        return ShopGrowthRates.getPriceForLevel(modelsEffect, effectBase,effectDiff,count);
    }

    public int getEffectRec(int base) {
        int value = base;
        int i = 0;
        while (i != count){
            value += ShopGrowthRates.getPriceForLevel(modelsEffect, effectBase,effectDiff,i);
            i++;
        }
        return value;
    }

}
