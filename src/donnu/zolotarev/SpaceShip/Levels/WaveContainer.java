package donnu.zolotarev.SpaceShip.Levels;

import donnu.zolotarev.SpaceShip.Units.BaseUnit;
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
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_ROCKET_L_1, 3, 0.2f);
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
                waveController.addWave(unitWave);
                return waveController;
            case LEVEL_1:
                waveController = new SimpleWave();
                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5, 3.0f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 3, 2.0f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 4, 0.2f);
                waveController.addWave(unitWave);
                return waveController;
            case LEVEL_2:
                waveController = new SimpleWave();

                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 5, 1.7f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_2, 6, 1.9f);
                unitWave.addEnemy(BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1, 3, 0.5f);
                waveController.addWave(unitWave);
                return waveController;
           // case LEVEL_3:
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
