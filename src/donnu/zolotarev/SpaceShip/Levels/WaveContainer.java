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
    public static final int LEVEL_20 = LEVEL_19+1;
    public static final int LEVEL_21 = LEVEL_20+1;
    public static final int LEVEL_22 = LEVEL_21+1;
    public static final int LEVEL_23 = LEVEL_22+1;
    public static final int LEVEL_24 = LEVEL_23+1;
    public static final int LEVEL_25 = LEVEL_24+1;
    public static final int LEVEL_26 = LEVEL_25+1;
    public static final int LEVEL_27 = LEVEL_26+1;
    public static final int LEVEL_28 = LEVEL_27+1;
    public static final int LEVEL_29 = LEVEL_28+1;

    public static final int LEVEL_MIN = LEVEL_1;
    public static final int LEVEL_MAX = LEVEL_29;

    public static IWaveController getWaveControllerById(int id,IAddedEnemy iAddedEnemy){
        return get(id,iAddedEnemy);
    }

    private static IWaveController get(int id,IAddedEnemy iAddedEnemy) {
        SimpleWave waveController = new SimpleWave();
        UnitWave unitWave = new UnitWave(iAddedEnemy);
        unitWave.addDelay(2f);  // start delay

        switch (id){
            case LEVEL_TEST:

                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 10, 0.2f);
                unitWave.startFog();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_SHIELD_L_1, 2, 0.2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, 2, 0.2f);
                unitWave.stopFog();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 10, 0.2f);
                break;
            case LEVEL_MUSEUM:

                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, 0.2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_3, 1, 0.2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 1, 0.2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, 1, 0.2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_2, 1, 0.2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 1, 0.2f);
                break;
            case LEVEL_1:

                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 3, - 1.0f);
                unitWave.waitLastKilled();
                unitWave.addDelay(1.0f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 3, -1.0f);
                break;
            case LEVEL_2:

                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 6, -1.0f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 4, 2.0f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 2, 0.2f);
                break;
            case LEVEL_3:

                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 3, -1f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 2, 0.3f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 3, -1f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5, 2.5f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5, 2.5f);
                break;
            case LEVEL_4:

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
                break;
            case LEVEL_5:

                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5, 0.4f);
                unitWave.addDelay(2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 3, 4f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 3, 3f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 2, 0.4f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 2, 0.4f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5, 0.3f);
                break;
            case LEVEL_6:
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 2, 2f);
                for (int i = 0; i<3; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.4f, new Point(1000 -100*i,-110), 150);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.4f, new Point(1000 -100*i, Constants.CAMERA_HEIGHT+10), 210);
                    unitWave.waitLastKilled();
                }
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 3, 1.4f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 3, 0.2f);

            break;
            case LEVEL_7:

                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 3, 0.4f);
                for (int i = 0; i<3; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.4f, new Point(1100 -100*i,-110), 150);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.4f, new Point(1100 -100*i, Constants.CAMERA_HEIGHT+10), 210);
                }
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 3, 1.4f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5, 0.4f);
                unitWave.addDelay(6f);
                for (int i = 0; i<4; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.4f, new Point(1100 -100*i,-110), 150);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.4f, new Point(1100 -100*i, Constants.CAMERA_HEIGHT+10), 210);
                }
                unitWave.addDelay(6f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 3, 4f);
                break;
            case LEVEL_8:
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5, 0.4f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 1, - 0.4f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 5, 0.4f);
                for (int i = 0; i<6; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.01f, new Point(1100, Constants.CAMERA_HEIGHT+10), 210);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 1.2f, new Point(1100,-100), 150);
                }
                unitWave.addDelay(1.3f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 2, 0.4f);
            break;
            case LEVEL_9:

                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 1, 1.4f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5, 0.5f);
                unitWave.addDelay(2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 2, 2.2f);
                for (int i = 0; i<2; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.4f, new Point(1200 -100*i,-110), 160);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.4f, new Point(1200 -100*i, Constants.CAMERA_HEIGHT+10), 200);
                }
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5, 0.5f);
                unitWave.addDelay(3f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 4, 3.2f);
                break;
            case LEVEL_10:

                for (int j = 0; j<2;j++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 3, 0.3f + j*1.5f);
                    if (j==0){
                        unitWave.waitLastKilled();
                    }

                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 2, 0.1f);
                    for (int i = 0; i<3; i++) {
                        unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.01f, new Point(600+ j*200 +200*i,-100), 160);
                        unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 1, 0.4f, new Point(600+ j*200 +200*i, Constants.CAMERA_HEIGHT+100), 200);
                    }
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, 0.1f);

                    if (j==1){
                        unitWave.waitLastKilled();
                    }
                }
                break;
            case LEVEL_11:

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
                break;
            case LEVEL_12:
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1,3, -1f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 3, 0.5f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1,2, 1.5f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 8, 1.1f);
                unitWave.waitLastKilled();
                for (int i = 0; i < 3; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5, 0.3f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, 1, 1f);
                }
                break;

            case LEVEL_13:

                for (int i = 0;i<4;i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 3+i, 1.4f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5+i, 0.1f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 0+i, 0.1f*(1+i));
                    unitWave.addDelay(1f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1,1, 1f);;
                    unitWave.addDelay(2f+i);
                }
                break;
            case LEVEL_14:

                for (int i = 0;i<2;i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, 1, 0.01f, new Point(Constants.CAMERA_WIDTH +10,Constants.CAMERA_HEIGHT_HALF-50), 180);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, 1, 0.01f, new Point(Constants.CAMERA_WIDTH+10, Constants.CAMERA_HEIGHT+10), 205);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, 1, 2.2f, new Point(Constants.CAMERA_WIDTH+10,-100), 145);

                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, 1, 0.01f,new Point(-100 ,Constants.CAMERA_HEIGHT_HALF-50), 0);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, 1, 0.01f, new Point(-100, Constants.CAMERA_HEIGHT+10), 325);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, 1, 2.2f, new Point(-100,-100), 35);
                    unitWave.waitLastKilled();
                }

                for (int i = 0;i<2;i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, 1, 0.01f, new Point(Constants.CAMERA_WIDTH-100, Constants.CAMERA_HEIGHT+10), 205);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, 1, 0.01f, new Point(-100, Constants.CAMERA_HEIGHT+10), 325);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, 1, 2.2f, new Point(Constants.CAMERA_WIDTH_HALF -50,Constants.CAMERA_HEIGHT +10), 270);

                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, 1, 0.01f, new Point(Constants.CAMERA_WIDTH-100,-100), 145);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, 1, 0.01f,new Point(-100 ,-10), 35);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, 1, 2.2f, new Point(Constants.CAMERA_WIDTH_HALF,-50), 90);

                    unitWave.waitLastKilled();
                }
                break;
            case LEVEL_15:
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2,7,0.7f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1,20,0.5f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2,8,0.7f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1,20,0.5f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1,5,1f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 50, 0.3f);
            break;
            case LEVEL_16:

                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 7, 0.8f);
                unitWave.waitLastKilled();
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 2, 2f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 2, 1f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 3, 0.8f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 4, 1f);
                unitWave.waitLastKilled();
                for (int i = 0;i<5;i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 2, 0.1f,new Point(1300 - 150*i ,-100), 160);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 2, 0.1f,new Point(1300 - 150*i ,Constants.CAMERA_HEIGHT + 100), 200);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 2, 0.3f);
                }
                unitWave.addDelay(3f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 10, 0.17f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 3, 3f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 4, 1f);
                break;
            case LEVEL_17:
                for (int i = 0; i < 5; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1 + i/4, 0.3f,new Point(Constants.CAMERA_WIDTH +50,Constants.CAMERA_HEIGHT_HALF),180);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1 + i/4, 0.3f,new Point(Constants.CAMERA_WIDTH_HALF,Constants.CAMERA_HEIGHT + 100),270);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1 + i/4, 0.3f,new Point(-100,Constants.CAMERA_HEIGHT_HALF),0);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1 + i/4, 0.3f,new Point(Constants.CAMERA_WIDTH_HALF +100,-100),90);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 1, 0.3f,new Point(-50,Constants.CAMERA_HEIGHT + 50),181);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 1, 0.3f,new Point(Constants.CAMERA_WIDTH + 50,Constants.CAMERA_HEIGHT + 50),270);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 1, 0.3f,new Point(Constants.CAMERA_WIDTH + 50,-50),0);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 1, 0.3f,new Point(50,-50),90);

                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, i/2, 0.6f);
                    unitWave.addDelay(1f);
                }
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, 3, 0.3f);
                break;
           case LEVEL_18:

               for (int i = 0; i < 7; i++) {
                   unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 4, 0.6f);
                   unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 2, 0.4f);
               }
               unitWave.addDelay(2f);
               unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, 2, 0.5f);
               unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 2, 0.4f);
               for (int i = 0; i < 2; i++) {
                   unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 2, 1.3f);
                   unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 2, 0.4f);
               }
               unitWave.addDelay(2f);
               unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, 2, 0.5f);
               unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 2, 0.4f);
               for (int i = 0; i < 3; i++) {
                   unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 2, 1.3f);
                   unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 2, 0.4f);
               }
                break;
           /*  case LEVEL_13:

                for (int i = 0; i < 4; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 1+i/2, 0.5f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 10+i, 0.3f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 2+i, 0.5f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 1+i/2, 0.5f);
                }
                break;

            case LEVEL_15:

                for (int i = 1; i < 5; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, i, 0.3f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, i, 0.3f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_3, i,0.8f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, i, 0.8f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, i, 0.8f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, i, 0.8f);
                    unitWave.addDelay(1);
                }
                break;
            case LEVEL_16:

                for (int i = 0; i < 5; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1 + i/4, 0.3f,new Point(Constants.CAMERA_WIDTH +50,Constants.CAMERA_HEIGHT_HALF),180);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1 + i/4, 0.3f,new Point(Constants.CAMERA_WIDTH_HALF,Constants.CAMERA_HEIGHT + 100),270);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1 + i/4, 0.3f,new Point(-100,Constants.CAMERA_HEIGHT_HALF),0);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1 + i/4, 0.3f,new Point(Constants.CAMERA_WIDTH_HALF +100,-100),90);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 1, 0.3f,new Point(-50,Constants.CAMERA_HEIGHT + 50),181);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 1, 0.3f,new Point(Constants.CAMERA_WIDTH + 50,Constants.CAMERA_HEIGHT + 50),270);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 1, 0.3f,new Point(Constants.CAMERA_WIDTH + 50,-50),0);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 1, 0.3f,new Point(50,-50),90);

                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, i/2, 0.3f);
                    unitWave.addDelay(1f);
                }
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_3, 3, 0.3f);
                break;
            case LEVEL_17:

                for (int i = 0;i<2;i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 2, 0.01f,new Point(1300 - 150*i ,-100), 160);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 2, 0.01f,new Point(1300 - 150*i ,Constants.CAMERA_HEIGHT + 100), 230);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 1, 0.1f);
                }
                unitWave.waitLastKilled();
                break;
            case LEVEL_18:

                for (int i = 0;i<5;i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 10, 0.7f - i%10.0f);
                    if (i%2  == 1){
                        unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_2, i, 1f);
                    }else{
                        unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, i, 1f);
                    }
                }
                break;
            case LEVEL_19:

                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 10, 0.3f);
                unitWave.addDelay(1f);
                for (int i = 0;i<6;i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_SHIELD_L_2, 1, 0.1f,new Point(300+130*i ,-100), 90);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_SHIELD_L_2, 1, 0.1f, new Point(300 + 130 * i,
                            Constants.CAMERA_HEIGHT + 100), 270);
                }
                unitWave.addDelay(1f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 3, 0.3f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_2, 2, 0.3f);

                unitWave.addDelay(1f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_SHIELD_L_2, 10, 0.3f);
                for (int i = 0;i<6;i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, 0.1f,new Point(300+130*i ,-100), 90);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1, 0.1f,new Point(300+130*i ,Constants.CAMERA_HEIGHT + 100), 270);
                }
                break;
            case LEVEL_20:

                for (int i = 0;i<5;i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 2, 0.6f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_L_1, 2, 0.4f);
                }
                unitWave.addDelay(1f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 8, 0.04f);
                unitWave.addDelay(0.5f);

                for (int i = 0;i<5;i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_SHIELD_L_1, 2, 0.6f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_SHIELD_L_1, 2, 0.4f);
                }

                unitWave.addDelay(0.5f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 6, 0.04f);
                unitWave.addDelay(0.5f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 10, 0.04f);
                unitWave.waitLastKilled();
                unitWave.addDelay(0.5f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 6, 0.04f);
                unitWave.addDelay(0.5f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 10, 0.04f);

                break;
            case LEVEL_21:

                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_SHIELD_L_3, 2, 0.6f);
                for (int i = 0;i<3;i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 20, 0.1f);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_SHIELD_L_2, 2, 0.5f);
                }
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_MINIGUN_SHIELD_L_1, 2, 0.5f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_METEOR_L_1, 3, 0.4f);
                for (int i = 0; i < 5; i++) {
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1 + i / 4, 0.3f, new Point(
                            Constants.CAMERA_WIDTH + 50, Constants.CAMERA_HEIGHT_HALF), 180);
                    unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 1 + i / 4, 0.3f, new Point(Constants.CAMERA_WIDTH_HALF,
                            Constants.CAMERA_HEIGHT + 100), 270);
                }

                break;*/
            default:
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 10, 0.2f);
        }

        waveController.addWave(unitWave);
        return waveController;
        //return null;
    }

}
