package donnu.zolotarev.SpaceShip.Waves;

public class WaveObject {

    private final IAddedEnemy.AddedEnemyParam unitParam;
    private final int count;
    private final float delay;
    private boolean wait = false;

    public WaveObject(IAddedEnemy.AddedEnemyParam kind, int count, float delay) {
        this.unitParam = kind;
        this.count = count;
        if(delay<=0){
            wait = true;
            this.delay = 0;
        }else{
            this.delay = delay;
        }
    }
    public WaveObject(int kind, int count, float delay) {
        this.unitParam = new IAddedEnemy.AddedEnemyParam(kind);
        this.count = count;
        if(delay<=0){
            wait = true;
            this.delay = 0;
        }else{
            this.delay = delay;
        }
    }



    public IAddedEnemy.AddedEnemyParam getUnitParam() {
        return unitParam;
    }

    public int getCount() {
        return count;
    }

    public float getDelay() {
        return delay;
    }

    public boolean isWait() {
        return wait;
    }
}
