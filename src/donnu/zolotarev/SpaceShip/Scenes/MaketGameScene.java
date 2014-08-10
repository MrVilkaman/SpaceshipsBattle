package donnu.zolotarev.SpaceShip.Scenes;

import android.graphics.Point;
import donnu.zolotarev.SpaceShip.Bullets.SimpleBullet;
import donnu.zolotarev.SpaceShip.Bullets.SimpleBullet2;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.GameState.IStatusGameInfo;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Units.Enemy1;
import donnu.zolotarev.SpaceShip.Units.Hero;
import donnu.zolotarev.SpaceShip.Waves.IWaveController;
import donnu.zolotarev.SpaceShip.Waves.SimpleWave;

import java.util.Random;

public class MaketGameScene extends BaseGameScene {

    public MaketGameScene(IParentScene self) {
        super(self);
    }

    @Override
    public void addNewWaveController(IWaveController controller) {
        ((SimpleWave)controller).addListener(new IStatusGameInfo() {
            @Override
            public void onNextWave(int count) {
                waveIndex++;
                textWaveBarCallback.onNextWave(waveIndex);
            }

            @Override
            public void onWinLevel() {
                toast("Победа!");
                returnToParentScene(IParentScene.EXIT_WIN);
            }
        });
        super.addNewWaveController(controller);
    }

    @Override
    protected void initWave() {
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

    @Override
    public void addEnemy(int kind) {
        BaseUnit enemy1 = BaseUnit.getEnemy(kind);
        Random random = new Random();
        enemy1.init(new Point(1300, random.nextInt(65) * 10));
    }
}
