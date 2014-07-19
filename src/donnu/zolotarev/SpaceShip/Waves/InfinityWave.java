package donnu.zolotarev.SpaceShip.Waves;

import donnu.zolotarev.SpaceShip.GameState.IWaveBar;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;

public class InfinityWave extends BaseWaveController implements IWaveController {

    private final IWaveBar iWaveBar;

    public InfinityWave(IWaveBar iWaveBar) {
        super();
        this.iWaveBar = iWaveBar;
    }

    public void updateWave(float pSecondsElapsed) {
        if (!isEmpty()){
            if (_currentWave == null ){
                if ( BaseUnit.getEnemiesOnMap() < 5){
                    iWaveBar.onNextWave();
                    _currentWave  = getNextWave();
                    _currentWave.startWave();
                }
            } else {
                _currentWave.update(pSecondsElapsed);

                if (_currentWave.isFinished()){
                    waveEnds();
                    _currentWave = null;
                }
            }
        } else {
            restart(1);
        }

    }


}
