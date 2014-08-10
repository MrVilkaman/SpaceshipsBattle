package donnu.zolotarev.SpaceShip.Scenes;

import android.graphics.Point;
import donnu.zolotarev.SpaceShip.Bullets.SimpleBullet;
import donnu.zolotarev.SpaceShip.Bullets.SimpleBullet2;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.GameState.IWaveBar;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Units.Enemy1;
import donnu.zolotarev.SpaceShip.Units.Hero;
import donnu.zolotarev.SpaceShip.Waves.InfinityWave;
import donnu.zolotarev.SpaceShip.Waves.UnitWave;

import java.util.Random;

public class InfinityGameScene extends BaseGameScene  {


    public InfinityGameScene(IParentScene self) {
        super(self);
        start();
    }

    @Override
    public void addEnemy(int kind){
        BaseUnit enemy1 = BaseUnit.getEnemy(kind);
        Random random = new Random();
        enemy1.init(new Point(1300, random.nextInt(65) * 10));
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
        hero = new Hero(textHealthBarCallback);
        hero.init(new Point(0, 250));
    }

    @Override
    protected void initBulletsPools() {
        SimpleBullet.initPool();
        SimpleBullet2.initPool();
    }

    @Override
    protected void initUnitsPools() {
        Enemy1.initPool();
    }
}
