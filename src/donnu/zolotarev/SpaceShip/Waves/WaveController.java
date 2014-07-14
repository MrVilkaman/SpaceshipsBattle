package donnu.zolotarev.SpaceShip.Waves;

import java.util.ArrayList;

public class WaveController {

    private ArrayList<UnitWave> unitWaves;
    private int currentIndex = 0;

    public WaveController() {
        unitWaves = new ArrayList<UnitWave>();
        currentIndex = 0;
    }

    public void addWave(UnitWave unitWave){
        unitWaves.add(unitWave);
    }

    public UnitWave getNextWave(){
        if (!isEmpty()){
            return unitWaves.get(currentIndex);
        }else{
            return null;
        }
    }

    public void waveEnds(){
        currentIndex++;
    }

    public boolean isEmpty(){
        return currentIndex == unitWaves.size();
    }

}
