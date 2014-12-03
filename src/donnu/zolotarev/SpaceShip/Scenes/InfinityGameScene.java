package donnu.zolotarev.SpaceShip.Scenes;

import android.graphics.Point;
import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;
import donnu.zolotarev.SpaceShip.GameData.UserDataProcessor;
import donnu.zolotarev.SpaceShip.GameState.IAmDie;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.GameState.IWaveBar;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Units.Hero;
import donnu.zolotarev.SpaceShip.Utils.Constants;
import donnu.zolotarev.SpaceShip.Waves.InfinityWave;
import donnu.zolotarev.SpaceShip.Waves.UnitWave;

import java.util.Random;

public class InfinityGameScene extends BaseGameScene implements IAmDie {


    public InfinityGameScene(IParentScene self) {
        super(self);
        start();
    }

    @Override
    public boolean addEnemy(AddedEnemyParam param){
        if (super.addEnemy(param)){
            BaseUnit enemy1 = BaseUnit.getEnemy(Constants.MAX_UNIT_LEVEL_WITH_SHIELD * (param.getKind() / Constants.MAX_UNIT_LEVEL_WITH_SHIELD));
            Random random = new Random();
            enemy1.init(param.getKind() % Constants.MAX_UNIT_LEVEL_WITH_SHIELD, new Point(1300, random.nextInt(65) * 10));
        }
            return true;
    }

    @Override
    protected void initWave() {
        waveController = new InfinityWave(new IWaveBar() {
            @Override
            public void onNextWave(int count) {
                waveIndex++;
                textWaveBarCallback.onNextWave(waveIndex);
            }
        });

        UnitWave unitWave = new UnitWave(this);
        unitWave.addEnemy(0, 7, 1.2f);
        unitWave.addEnemy(0, 4, 0.2f);
        unitWave.addEnemy(0,7,0.8f);
        waveController.addWave(unitWave);

        unitWave = new UnitWave(this);
        unitWave.addEnemy(0, 8, 0.4f);
        unitWave.addDelay(2);
        unitWave.addEnemy(0, 10, 0.6f);
        unitWave.addDelay(3);
        unitWave.addEnemy(0,20,0.9f);
        waveController.addWave(unitWave);

        unitWave = new UnitWave(this);
        unitWave.addEnemy(0, 10, 0.4f);
        unitWave.addEnemy(0, 20, 0.5f);
        unitWave.addEnemy(0, 20,0.9f);
        waveController.addWave(unitWave);
    }

    @Override
    protected void initHero() {
        hero = new Hero(textHealthBarCallback, setting.isAnalogflyControlMode());
        hero.init(0, new Point(0, 250));
    }

    @Override
    protected void initBulletsPools() {
        BaseBullet.initPool();
        BaseBullet.setUnitDieListener(this);
    }

    @Override
    protected void initUnitsPools() {
        BaseUnit.initPool();
    }

    @Override
    protected void beforeReturnToParent(int status) {
        UserDataProcessor dataProcessor = UserDataProcessor.get();
        if (status == IParentScene.EXIT_WIN || status == IParentScene.EXIT_DIE){
            score = dataProcessor.processGold(score,status == IParentScene.EXIT_WIN);
        }
    }

    @Override
    public void destroyed(BaseUnit unit) {

    }
}
