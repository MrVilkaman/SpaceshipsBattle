package donnu.zolotarev.SpaceShip.Waves;

import donnu.zolotarev.SpaceShip.GameState.IStatusGameInfo;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Utils.Constants;

public class SimpleWave extends BaseWaveController {
    private final IStatusGameInfo winListner;

    public SimpleWave(IStatusGameInfo winListner) {
        super();
        this.winListner = winListner;
    }

    private boolean isFirst = true;

    @Override
    public void restart(int vavleNumber) {
        super.restart(vavleNumber);
        isFirst = true;
    }

    @Override
    public void restart() {
        super.restart();
        isFirst = true;
    }

    @Override
    public void updateWave(float pSecondsElapsed) {
        if (!isEmpty()){
            if (_currentWave == null ){
                if ( BaseUnit.getEnemiesOnMap() <= Constants.LIMIL_UNIT_IN_MAP_TO_NEXT_WAVE){
                    winListner.onNextWave(0);
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
            if ( BaseUnit.getEnemiesOnMap() == 0 && isFirst){
                isFirst = false;
                winListner.onWinLevel();
            }
        }
    }
}
