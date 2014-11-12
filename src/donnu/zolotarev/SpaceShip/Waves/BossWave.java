package donnu.zolotarev.SpaceShip.Waves;

import donnu.zolotarev.SpaceShip.GameState.IAddListener;
import donnu.zolotarev.SpaceShip.GameState.IStatusGameInfo;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;

public class BossWave  extends BaseWaveController implements IAddListener {
    private IStatusGameInfo winListner;

    @Override
    public void addListener(IStatusGameInfo winListner){
        this.winListner = winListner;
    }

    private boolean isFirst = false;

    @Override
    public void restart(int vavleNumber) {
        super.restart(vavleNumber);
        isFirst = false;
    }

    @Override
    public void restart() {
        super.restart();
        isFirst = false;
    }

    @Override
    public void updateWave(float pSecondsElapsed) {

        if ( BaseUnit.getEnemiesOnMap() != 0  ){
            isFirst = true;
        }else{
            if (isFirst){
                winListner.onWinLevel();
            }
        }

        if (!isEmpty()){
            if (_currentWave == null ){
                    winListner.onNextWave(0);
                    _currentWave  = getNextWave();
                    _currentWave.startWave();
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