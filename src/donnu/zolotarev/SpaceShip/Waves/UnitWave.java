package donnu.zolotarev.SpaceShip.Waves;

import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Utils.Constants;

import java.util.ArrayList;

public class UnitWave {

    public float startDelay = 0;

    private IAddedEnemy _instance;
    private ArrayList<WaveObject> _enemies;
    private int _enemyIndex = 0;
    private WaveObject _enemy = null;
    private Boolean _isStarted = false;
    private float _interval = 0;
    private int _enemyCount = 0;


    public UnitWave(IAddedEnemy _instance){
        this._instance =  _instance;
        _enemies = new ArrayList<WaveObject>();
    }

    public void addEnemy(int kind, int count, float respawnInterval){
        _enemies.add(new WaveObject(kind,count,respawnInterval));
    }

    public void addDelay(float respawnInterval){
        addEnemy(0,0,respawnInterval);
    }


    public void startWave(){

        if (!_isStarted){
            _enemyIndex = 0;
            _interval = startDelay;
            _isStarted = true;
        }
    }

    public void update(float delta){
        if (_isStarted){
            if ((_interval -= delta)<=0){
                if (!nextEnemy()){
                    _isStarted = false;
                }
            }
        }
    }

    public boolean nextEnemy(){
        if(_enemyIndex < _enemies.size()){
            if (BaseUnit.getEnemiesOnMap() < Constants.LIMIL_UNIT_IN_MAP_TO_NEXT_UNIT){
                if(_enemy == null){
                    _enemy = _enemies.get(_enemyIndex);
                    _enemyCount = _enemy.getCount();
                }
                _instance.addEnemy(_enemy.getKind());
                _interval = _enemy.getDelay();

                if(--_enemyCount<= 0){
                    _enemyIndex++;
                    _enemy = null;
                }
            }
            return true;

        }
        return false;
    }

    public Boolean isFinished(){
        return !_isStarted;
    }

}
