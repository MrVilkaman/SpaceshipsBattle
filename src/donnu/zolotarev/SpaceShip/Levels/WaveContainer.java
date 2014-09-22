package donnu.zolotarev.SpaceShip.Levels;

import android.graphics.Point;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Utils.Constants;
import donnu.zolotarev.SpaceShip.Waves.IAddedEnemy;
import donnu.zolotarev.SpaceShip.Waves.IWaveController;
import donnu.zolotarev.SpaceShip.Waves.SimpleWave;
import donnu.zolotarev.SpaceShip.Waves.UnitWave;

public  class WaveContainer {
    public static final int LEVEL_INFINITY = 99;
    public static final int LEVEL_TEST = 100;
    public static final int LEVEL_MUSEUM = 101;

    public static final int LEVEL_1 = 1;
    public static final int LEVEL_2 = LEVEL_1+1;
    public static final int LEVEL_3 = LEVEL_2+1;
    public static final int LEVEL_4 = LEVEL_3+1;
    public static final int LEVEL_5 = LEVEL_4+1;
    public static final int LEVEL_6 = LEVEL_5+1;
    public static final int LEVEL_7 = LEVEL_6+1;
    public static final int LEVEL_8 = LEVEL_7+1;
    public static final int LEVEL_9 = LEVEL_8+1;
    public static final int LEVEL_10 = LEVEL_9+1;
    public static final int LEVEL_11 = LEVEL_10+1;
    public static final int LEVEL_12 = LEVEL_11+1;
    public static final int LEVEL_13 = LEVEL_12+1;
    public static final int LEVEL_14 = LEVEL_13+1;
    public static final int LEVEL_15 = LEVEL_14+1;
    public static final int LEVEL_16 = LEVEL_15+1;
    public static final int LEVEL_17 = LEVEL_16+1;
    public static final int LEVEL_18 = LEVEL_17+1;
    public static final int LEVEL_19 = LEVEL_18+1;


    public static IWaveController getWaveControllerById(int id,IAddedEnemy iAddedEnemy){
        return get(id,iAddedEnemy);
    }

    private static IWaveController get(int id,IAddedEnemy iAddedEnemy) {
        SimpleWave waveController;
        UnitWave unitWave;
        switch (id){
            case LEVEL_TEST:
                waveController = new SimpleWave();
                unitWave = new UnitWave(iAddedEnemy);
                //unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 1, 0.2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, 2, 0.3f);
                waveController.addWave(unitWave);
                return waveController;
            case LEVEL_MUSEUM:
                waveController = new SimpleWave();
                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, 0.2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_3, 1, 0.2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 1, 0.2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, 1, 0.2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_2, 1, 0.2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 1, 0.2f);
                waveController.addWave(unitWave);
                return waveController;
            case LEVEL_1:
                waveController = new SimpleWave();
                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 6, -1.0f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 4, 2.0f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 2, 0.2f);
                waveController.addWave(unitWave);
                return waveController;
            case LEVEL_2:
                waveController = new SimpleWave();

                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addDelay(2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 3, -1f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 2, 0.3f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 3, -1f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5, 2.5f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5, 2.5f);

                waveController.addWave(unitWave);
                return waveController;
            case LEVEL_3:
                waveController = new SimpleWave();

                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addDelay(2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5, 3f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, -1f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 3, 0.2f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 2, 3f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 3, 2f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 3, 3f);

                waveController.addWave(unitWave);
                return waveController;
            case LEVEL_4:
                waveController = new SimpleWave();

                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addDelay(2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5, 0.4f);
                unitWave.addDelay(2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 3, 4f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 3, 3f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 2, 0.4f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 2, 0.4f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5, 0.3f);

                waveController.addWave(unitWave);
                return waveController;
            case LEVEL_5:
                waveController = new SimpleWave();

                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addDelay(2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 3, 0.4f);
                for (int i = 0; i<3; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.4f, new Point(1100 -100*i,-110), 150);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.4f, new Point(1100 -100*i, Constants.CAMERA_HEIGHT+110), 210);
                }
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 3, 1.4f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5, 0.4f);
                unitWave.addDelay(6f);
                for (int i = 0; i<4; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.4f, new Point(1100 -100*i,-110), 150);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.4f, new Point(1100 -100*i, Constants.CAMERA_HEIGHT+110), 210);
                }
                unitWave.addDelay(6f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 3, 4f);


                waveController.addWave(unitWave);
                return waveController;
            case LEVEL_6:
                waveController = new SimpleWave();
                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addDelay(2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 1, -0.4f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5, 0.5f);
                unitWave.addDelay(3f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 2, 3.4f);
                for (int i = 0; i<2; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.4f, new Point(1200 -100*i,-110), 160);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.4f, new Point(1200 -100*i, Constants.CAMERA_HEIGHT+110), 200);
                }
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5, 0.5f);
                unitWave.addDelay(3f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 4, 4.2f);

                waveController.addWave(unitWave);
                return waveController;

            case LEVEL_7:
                waveController = new SimpleWave();
                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addDelay(2f);

                for (int j = 0; j<3;j++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 3, 0.3f + j*1.5f);
                    if (j==0){
                        unitWave.waitLastKilled();
                    }

                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 2, 0.1f);
                    for (int i = 0; i<3; i++) {
                        unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.1f, new Point(600+ j*200 +200*i,-100), 160);
                        unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.1f, new Point(600+ j*200 +200*i, Constants.CAMERA_HEIGHT+100), 200);
                    }
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, 0.1f);
                }

                waveController.addWave(unitWave);
                return waveController;
            case LEVEL_8:
                waveController = new SimpleWave();
                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addDelay(2f);


                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 2, 0.1f);
                    unitWave.addDelay(1f);
                for (int i = 0; i<2; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.1f, new Point(700,-100), 150);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, 0.1f, new Point(800,-100), 150);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 1, 0.1f, new Point(900,-100), 150);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, 0.1f, new Point(1000,-100), 150);
                    unitWave.addDelay(2f);
                }
                unitWave.waitLastKilled();
                for (int i = 0; i<2; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.1f, new Point(700,Constants.CAMERA_HEIGHT + 100), 210);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, 0.1f, new Point(800,Constants.CAMERA_HEIGHT + 100), 210);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 1, 0.1f, new Point(900,Constants.CAMERA_HEIGHT + 100), 210);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, 0.1f, new Point(1000,Constants.CAMERA_HEIGHT + 100), 210);
                    unitWave.addDelay(2f);
                }
                unitWave.waitLastKilled();
                for (int i = 0; i<2; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.1f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, 0.1f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 1, 0.1f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, 0.1f);
                    unitWave.addDelay(2f);
                }
                unitWave.waitLastKilled();

                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.1f, new Point(700,-100), 150);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.1f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.1f, new Point(700,Constants.CAMERA_HEIGHT + 100), 210);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, 0.1f, new Point(800,-100), 150);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, 0.1f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, 0.1f, new Point(800,Constants.CAMERA_HEIGHT + 100), 210);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 1, 0.1f, new Point(900,-100), 150);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 1, 0.1f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 1, 0.1f, new Point(900,Constants.CAMERA_HEIGHT + 100), 210);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, 0.1f, new Point(1000,-100), 150);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, 0.1f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, 0.1f, new Point(1000,Constants.CAMERA_HEIGHT + 100), 210);

                waveController.addWave(unitWave);
                return waveController;
            case LEVEL_9:
                waveController = new SimpleWave();
                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addDelay(2f);

                for (int i = 0;i<4;i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_3, 3+i, 2f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5+i, 0.1f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 1+i, 0.1f);
                    unitWave.addDelay(3f+i);
                }


                waveController.addWave(unitWave);
                return waveController;
            case LEVEL_10:
                waveController = new SimpleWave();
                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addDelay(2f);

                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2,5,1f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_3,5,1f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2,5,1f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_3,5,1f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1,5,1f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 20, 0.3f);

                waveController.addWave(unitWave);
                return waveController;
            case LEVEL_11:
                waveController = new SimpleWave();
                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addDelay(2f);

                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 7, 1f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 2, 2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 2, 1f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 3, 2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 4, 1f);
                unitWave.waitLastKilled();
                for (int i = 0;i<5;i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 2, 0.1f,new Point(1300 - 150*i ,-100), 160);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 2, 0.1f,new Point(1300 - 150*i ,Constants.CAMERA_HEIGHT + 100), 200);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 1, 0.1f);
                }
                unitWave.addDelay(3f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 5, 0.1f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 3, 3f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_3, 4, 2f);

                waveController.addWave(unitWave);
                return waveController;
            case LEVEL_12:
                waveController = new SimpleWave();
                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addDelay(2f);

                for (int i = 0; i < 7; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_3, 2, 1f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 2, 0.4f);
                }
                unitWave.addDelay(2f);
                for (int i = 0; i < 5; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 2, 1.3f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 2, 0.4f);
                }

                waveController.addWave(unitWave);
                return waveController;
            case LEVEL_13:
                waveController = new SimpleWave();
                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addDelay(2f);

                for (int i = 0; i < 4; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 1+i/2, 0.5f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 10+i, 0.3f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 2+i, 0.5f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 1+i/2, 0.5f);
                }

                waveController.addWave(unitWave);
                return waveController;

            case LEVEL_14:
                waveController = new SimpleWave();
                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addDelay(2f);

                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1,3, -1f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 3, 0.5f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1,2, 1.5f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 8, 1.1f);
                unitWave.waitLastKilled();
                for (int i = 0; i < 3; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5, 0.3f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, 1, 1f);
                }


                waveController.addWave(unitWave);
                return waveController;
            case LEVEL_15:
                waveController = new SimpleWave();
                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addDelay(2f);

                for (int i = 1; i < 5; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, i, 0.3f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, i, 0.3f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_3, i,0.8f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, i, 0.8f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, i, 0.8f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, i, 0.8f);
                    unitWave.addDelay(1);
                }
                waveController.addWave(unitWave);
                return waveController;
            case LEVEL_16:
                waveController = new SimpleWave();
                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addDelay(2f);

                for (int i = 0; i < 4; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, 0.3f,new Point(Constants.CAMERA_WIDTH +50,Constants.CAMERA_HEIGHT_HALF),180);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, 0.3f,new Point(Constants.CAMERA_WIDTH_HALF,Constants.CAMERA_HEIGHT + 100),270);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, 0.3f,new Point(-100,Constants.CAMERA_HEIGHT_HALF),0);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, 0.3f,new Point(Constants.CAMERA_WIDTH_HALF +100,-100),90);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 1, 0.3f,new Point(-50,Constants.CAMERA_HEIGHT + 50),181);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 1, 0.3f,new Point(Constants.CAMERA_WIDTH + 50,Constants.CAMERA_HEIGHT + 50),270);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 1, 0.3f,new Point(Constants.CAMERA_WIDTH + 50,-50),0);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 1, 0.3f,new Point(50,-50),90);

                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 1, 0.3f);
                    unitWave.addDelay(1f);
                }
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_3, 3, 0.3f);

                waveController.addWave(unitWave);
                return waveController;
            case LEVEL_17:
                waveController = new SimpleWave();
                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addDelay(2f);

                for (int i = 0;i<2;i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 2, 0.1f,new Point(1300 - 150*i ,-100), 160);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 2, 0.1f,new Point(1300 - 150*i ,Constants.CAMERA_HEIGHT + 100), 200);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 2, 0.4f);
                }
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_2, 2, -0.1f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 6, 0.1f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_2, 2, 1f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1,5, 1f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 2, 0.5f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 6, 0.1f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 2, 0.5f);

                waveController.addWave(unitWave);
                return waveController;
            case LEVEL_18:
                waveController = new SimpleWave();
                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addDelay(2f);

                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 100, 0.7f);

                waveController.addWave(unitWave);
                return waveController;

            default:
                waveController = new SimpleWave();

                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1 + id, 1.2f+(id%2/5f));
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5, 1.3f);
                unitWave.addDelay(4);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2,5 + id,1f);
                waveController.addWave(unitWave);
                int waveCount = (id+1)/3;
                for (int i = 0; i<waveCount; i++) {
                    unitWave = new UnitWave(iAddedEnemy);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 2 + i, 1.6f - i/10f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5*i, 2f - i/10f);

                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_3, 2 + 2*i, 1.8f - i/10f);
                    unitWave.addDelay(5f + i);
                    if (id > 6){
                        unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 2 + i, 1.3f);
                    }
                    waveController.addWave(unitWave);
                }

                return waveController;
            /*default:
                new Exception("Undefine id of wave controller");*/
        }
        //return null;
    }

}
