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
    // count = -2 - Предмет НЕ куплен
    // count = -1 = Предмет куплен, НЕ нуждается в патронах!
    // count >= 0 = Предмет куплен, Количество оставшихся патронов.
    private int count;
    private int title;
    private int description;
    private ItemShopType type;
    private boolean isHaveAmmo = false;


    public ShopItem(int title, int description, ItemShopType type,int priceBuy) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.priceBuy = priceBuy;
    }

    public int getPriceItemBuy() {
        return priceItemBuy;
    }

    public void setPriceItemBuy(int priceItemBuy) {
        this.priceItemBuy = priceItemBuy;
    }

    public int getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(int priceBuy) {
        this.priceBuy = priceBuy;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTitle() {
        return title;
    }

    public int getDescription() {
        return description;
    }

}
