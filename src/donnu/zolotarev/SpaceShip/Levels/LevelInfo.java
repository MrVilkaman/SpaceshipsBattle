package donnu.zolotarev.SpaceShip.Levels;

public class LevelInfo {

    private int levelId;
    private int x;
    private int y;
    private boolean isInfinity;
    private boolean isWin;
    private boolean isNew;
    private boolean isEnabled = false;

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

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("levelId= ")
                .append(levelId).append(" x= ").append(x).append(" y= ").append(y)
                .append(" isEnabled= ").append(isEnabled).append(" isWin=").append(isWin).append(" isInfinity= ").append(isInfinity)
                .append(" isNew= ").append(isNew);
        return builder.toString();
    }

    //
    /*
    * характеристики уровня
    * */
}
