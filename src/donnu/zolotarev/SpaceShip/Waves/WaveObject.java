package donnu.zolotarev.SpaceShip.Waves;

public class WaveObject {

    private final int kind;
    private final int count;
    private final float delay;
    private boolean wait = false;

    public WaveObject(int kind, int count, float delay) {
        this.kind = kind;
        this.count = count;
        this.delay = delay;
        if(delay<0){
            wait = true;
        }
    }




    public int getKind() {
        return kind;
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
