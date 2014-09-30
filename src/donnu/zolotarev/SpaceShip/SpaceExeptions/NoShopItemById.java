package donnu.zolotarev.SpaceShip.SpaceExeptions;

public class NoShopItemById extends RuntimeException {

    public NoShopItemById() {
        super("!!!!!!! - Нет товара с таким id");
        super.printStackTrace();
    }
}
