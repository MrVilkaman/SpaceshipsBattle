package donnu.zolotarev.SpaceShip.GameState;

public interface IParentScene {
    public static final String STATUS_CODE = "code";
    public static final int EXIT_WIN = 0;
    public static final int EXIT_USER = -1;
    public static final int EXIT_DIE= 1;
    int EXIT_SHOP = 2;
    public static final int EXIT_RESTART = 3;

    public void returnToParentScene(int statusCode);
    public void restart(int statusCode);
}
