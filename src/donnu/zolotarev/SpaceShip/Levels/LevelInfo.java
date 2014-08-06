package donnu.zolotarev.SpaceShip.Levels;

public class LevelInfo {

    private int levelId;
    private int x;
    private int y;
    private boolean isInfinity;
    private boolean isWin;
    private boolean isNew;

    public int getLevelId() {
        return levelId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public LevelInfo(int levelId, int x, int y, boolean isInfinity) {
        this.levelId = levelId;
        this.x = x;
        this.y = y;
        this.isInfinity = isInfinity;
        isNew = true;
    }

    public boolean isWin() {
        return isWin;
    }

    public boolean isInfinity() {
        return isInfinity;
    }

    public void setWin(boolean isWin) {
        this.isWin = isWin;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }


    //
    /*
    * характеристики уровня
    * */
}
