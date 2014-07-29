package donnu.zolotarev.SpaceShip.Waves;

import donnu.zolotarev.SpaceShip.GameState.IWaveBar;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Utils.Constants;

public class InfinityWave extends BaseWaveController implements IWaveController {

    private final IWaveBar iWaveBar;

    public InfinityWave(IWaveBar iWaveBar) {
        super();
        this.iWaveBar = iWaveBar;
    }

    public void updateWave(float pSecondsElapsed) {
        if (!isEmpty()){
            if (_currentWave == null ){
                if ( BaseUnit.getEnemiesOnMap() <= Constants.LIMIL_UNIT_IN_MAP_TO_NEXT_WAVE){
                    iWaveBar.onNextWave(0);
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
