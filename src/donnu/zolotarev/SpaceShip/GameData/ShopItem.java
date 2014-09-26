package donnu.zolotarev.SpaceShip.GameData;

public class ShopItem {

    public enum ItemShopType {
        AMMO,
        DEFENCE
    }

    public interface GrowthFunction{
        public int getPriceForLevel(int level);
    }

    private int priceItemBuy;
    private int priceBuy;
    // count = -1 - Предмет НЕ куплен
    // count >= 0 = Предмет куплен, Количество оставшихся патронов.
    private int count;
    private String title;
    private String description;
    private ItemShopType type;
    private boolean isHaveAmmo = false;


    public ShopItem(String title, String description, ItemShopType type,int priceBuy) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.priceBuy = priceBuy;
        count = -1;
    }

    public int getPriceBuy() {
        if (!alreadyBought()){
            return priceBuy;
        }else{
            if (isHaveAmmo){
                return priceItemBuy;
            }else{
                return -1;
            }
        }
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
            count = 0;
        }else{
            // todo Сделать коэфициент
            count = 5;
        }
    }


}
