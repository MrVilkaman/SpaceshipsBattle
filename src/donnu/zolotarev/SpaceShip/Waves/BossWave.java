package donnu.zolotarev.SpaceShip.Waves;

import android.graphics.Point;
import donnu.zolotarev.SpaceShip.GameState.IAddListener;
import donnu.zolotarev.SpaceShip.GameState.IStatusGameInfo;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;

import java.util.Random;

public class BossWave  extends BaseWaveController implements IAddListener {
    private IStatusGameInfo winListner;
    private BaseUnit boss;

    public BossWave(IStatusGameInfo winListner) {
        super();
        this.winListner = winListner;
    }

    public BossWave() {
    }

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
          //      if ( BaseUnit.getEnemiesOnMap() <= Constants.LIMIL_UNIT_IN_MAP_TO_NEXT_WAVE){
                    winListner.onNextWave(0);
                    _currentWave  = getNextWave();
                    _currentWave.startWave();
            //    }
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

    public void addBoss(BaseUnit unit){
        boss = unit;
    }

    public void startBoss(){
        Random random = new Random();
        boss.init(-1,new Point(1300, random.nextInt(60) * 10),135 + 15*random.nextInt(6));
    }


}