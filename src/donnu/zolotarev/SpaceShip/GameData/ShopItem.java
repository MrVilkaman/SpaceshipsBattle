package donnu.zolotarev.SpaceShip.GameData;

public class ShopItem {

    private final int maxLevel;
    private final ShopGrowthRates.RatesModels models;
    private final int priceDiff;

    public enum ItemShopType {
        AMMO,
        DEFENCE
    }

    private int priceItemBuy;
    private Integer priceBuy;
    // count = -1 - Предмет НЕ куплен
    // count >= 0 = Предмет куплен, Количество оставшихся патронов или уровень, если isHaveAmmo = false.
    private int count;
    private String title;
    private String description;
    private ItemShopType type;
    private boolean isHaveAmmo = false;


    public ShopItem(String title, String description, ItemShopType type,int maxLevel,ShopGrowthRates.RatesModels models,int priceBuy,int priceDiff) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.priceBuy = priceBuy;
        this.priceDiff = priceDiff;
        this.maxLevel = maxLevel;
        this.models = models;
        count = 0;
    }

   /* public ShopItem(String title, String description, ItemShopType type,int priceBuyGun,int countAddAmmo,int priceBuyAmmo) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.priceBuy = priceBuy;
        count = -1;
    }*/


    public int getPriceBuy() {
        if (isHaveAmmo){
            return -1;
        }else{

            return ShopGrowthRates.getPriceForLevel(models, priceBuy,priceDiff,count);
        }

        /*if (!alreadyBought()){
            return priceBuy;
        }else{
            if (isHaveAmmo){
                return ShopGrowthRates.getPriceForLevel(models, priceItemBuy,priceDiff,count);
            }else{
                return -1;
            }
        }*/
    }

    public int getCount() {
        return count;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
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



}
