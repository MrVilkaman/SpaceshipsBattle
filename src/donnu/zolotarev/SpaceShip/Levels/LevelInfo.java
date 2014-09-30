package donnu.zolotarev.SpaceShip.Levels;

public class LevelInfo {

    private int levelId;
    private boolean isInfinity;
    private boolean isWin;
    private boolean isNew;
    private boolean isEnabled = false;

    private int totalCoast = 0;
    private int totalWin = 0;
    private int totalLose = 0;
    private int totalRestart = 0;

    public int getLevelId() {
        return levelId;
    }

    public LevelInfo(int levelId, boolean isInfinity) {
        this.levelId = levelId;
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
                .append(" isEnabled= ").append(isEnabled).append(" isWin=").append(isWin).append(" isInfinity= ").append(isInfinity)
                .append(" isNew= ").append(isNew);
        return builder.toString();
    }

    public int getTotalCoast() {
        return totalCoast;
    }

    public int getTotalWin() {
        return totalWin;
    }

    public int getTotalLose() {
        return totalLose;
    }

    public int getTotalRestart() {
        return totalRestart;
    }

    public int getTotalGame(){
        return totalWin + totalLose + totalRestart;
    }

    public void addTotalWin() {
        totalWin++;
    }

    public void addTotalLose() {
        totalLose++;
    }

    public void addTotalRestart() {
        totalRestart++;
    }

    public void addTotalCoast(int value){
        totalCoast += value;
    }


    //
    /*
    * характеристики уровня
    * */
}
