package donnu.zolotarev.SpaceShip.Waves;

import donnu.zolotarev.SpaceShip.IWaveBar;
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
                    _currentWave  = getNextWave();
                    _currentWave.startWave();
                    iWaveBar.onNextWave();
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
            //            if (BaseUnit.getEnemiesOnMap() == 0 && !isVictory){
            //isVictory = true;

           /* shipActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(shipActivity, "ТЫ выиграл!", Toast.LENGTH_SHORT).show();
                    //  acitveScene.setIgnoreUpdate(true);
                    //  bulletController.cleer();
                }
            });*/
            //            }
        }
    }


}
