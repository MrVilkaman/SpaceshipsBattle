package donnu.zolotarev.SpaceShip.GameData;

public class ShopItem {



    public enum ItemShopType {
        AMMO,
        DEFENCE
    }

    private int priceBuy;
    // count = -1 - Предмет НЕ куплен
    // count >= 0 = Предмет куплен, Количество оставшихся патронов или уровень, если isHaveAmmo = false.
    private int count;
    private int titleResId;
    private ItemShopType type;
    private boolean isHaveAmmo = false;
    private final int maxLevel;
    private final ShopGrowthRates.RatesModels models;
    private int titleAmmoResI;
    private final float priceDiff;

    private final ShopGrowthRates.RatesModels modelsEffect;
    private final int effectBase;
    private final float effectDiff;

    public ShopItem(int titleResId, ItemShopType type, int maxLevel, ShopGrowthRates.RatesModels models, int priceBuy,
            float priceDiff, ShopGrowthRates.RatesModels modelsEffect, int effectBase, float effectDiff) {
        this.titleResId = titleResId;
        titleAmmoResI = -1;
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

    public ShopItem(int titleResId, int titleAmmoResID, ItemShopType type, int maxLevel, int priceBuy, float priceAmmo,
            int ammoCountBase, int ammoCountDiff) {
        this.titleResId = titleResId;
        this.titleAmmoResI = titleAmmoResID;
        this.type = type;
        this.priceBuy = priceBuy;
        this.priceDiff = priceAmmo;
        this.maxLevel = maxLevel;
        this.modelsEffect = null;
        this.models = null;
        this.effectBase = ammoCountBase;
        this.effectDiff = ammoCountDiff;
        isHaveAmmo = true;
        count = -1;
    }

    public int getPriceBuy() {
        if (isHaveAmmo){
            if (alreadyBought()){
                return (int)priceDiff;
            }else{
                return priceBuy;
            }
        }else{
            return ShopGrowthRates.getPriceForLevel(models, priceBuy,priceDiff,count);
        }
    }

    public int getCount() {
        return count;
    }

    int getId() {
        return titleResId;
    }

    int getResID() {
        return titleResId;
    }

    public int getTitle() {
        if (!isUseAmmo() || !alreadyBought()){
            return titleResId;
        }else{
            return titleAmmoResI;
        }
    }

    public boolean isUseAmmo() {
        return isHaveAmmo;
    }

    public boolean alreadyBought(){
        return count >=0;
    }

    void buy() {
        if (!isUseAmmo()){
            count++;
        }else{
            if (alreadyBought()){
                count += (int)effectDiff;
                count = count <= maxLevel?count:maxLevel;
            }else{
                count = (int)effectBase;
            }
        }
    }

    public boolean haveNext(){
        return count != maxLevel;
    }

    public int getLevelMax() {
        return maxLevel;
    }

    public int getEffect() {
        if (isUseAmmo()){
            return count;
        } else {
            return ShopGrowthRates.getPriceForLevel(modelsEffect, effectBase, effectDiff, count);
        }
    }

    public int getEffectRec(int base) {
        if (isHaveAmmo){
            new RuntimeException("Не предусмотрено!");
            return -1;
        } else {
            int value = base;
            int i = 0;
            while (i != count) {
                value += ShopGrowthRates.getPriceForLevel(modelsEffect, effectBase, effectDiff, i);
                i++;
            }
            return value;
        }
    }

    public int use(){
        count--;
        return count;
       /* if (count != 0){
            count--;
            return true;
        }else{
            return false;
        }*/
    }

    void setTitleResId(int titleResId) {
        this.titleResId = titleResId;
    }

    void setTitleAmmoResI(int titleAmmoResI) {
        this.titleAmmoResI = titleAmmoResI;
    }
}
