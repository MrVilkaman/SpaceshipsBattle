package donnu.zolotarev.SpaceShip.Waves;

import java.util.ArrayList;

public abstract class BaseWaveController implements IWaveController {
    protected ArrayList<UnitWave> unitWaves;
    protected int currentIndex = 0;
    protected UnitWave _currentWave = null;

    public BaseWaveController() {
        unitWaves = new ArrayList<UnitWave>();
        currentIndex = 0;
    }

    public void addWave(UnitWave unitWave){
        unitWaves.add(unitWave);
    }

    protected UnitWave getNextWave(){
        if (!isEmpty()){
            return unitWaves.get(currentIndex);
        }else{
            return null;
        }
    }

    protected void waveEnds(){
        currentIndex++;
    }

    protected boolean isEmpty(){
        return currentIndex >= unitWaves.size();
    }

    public void restart(){
        restart(0);
    }

    public void restart(int vavleNumber ){
        if (vavleNumber < unitWaves.size()){
            currentIndex =  vavleNumber;
        }else {
            currentIndex = 0;
        }
    }

}
