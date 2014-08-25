package donnu.zolotarev.SpaceShip.Levels;

import donnu.zolotarev.SpaceShip.Waves.IAddedEnemy;
import donnu.zolotarev.SpaceShip.Waves.IWaveController;
import donnu.zolotarev.SpaceShip.Waves.SimpleWave;
import donnu.zolotarev.SpaceShip.Waves.UnitWave;

public  class WaveContainer {
    public static final int LEVEL_INFINITY = 99;
    public static final int LEVEL_TEST = 100;

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
                unitWave.addEnemy(0, 1, 0.2f);
                waveController.addWave(unitWave);
                return waveController;
            case LEVEL_1:
                waveController = new SimpleWave();
                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addEnemy(0, 5, 2.0f);
                unitWave.addEnemy(0, 4, 1.4f);
                unitWave.addEnemy(0, 3, 0.2f);
                waveController.addWave(unitWave);
                return waveController;
            case LEVEL_2:
                waveController = new SimpleWave();

                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addEnemy(0, 5, 1.5f);
                unitWave.addEnemy(0, 5, 1.4f);
                unitWave.addEnemy(0, 3, 0.3f);
                waveController.addWave(unitWave);
                return waveController;
           // case LEVEL_3:
            default:
                waveController = new SimpleWave();

                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addEnemy(0, 1 + id, 1f+(id%2/5f));
                unitWave.addEnemy(0, 5, 1.2f);
                unitWave.addEnemy(0,5 + id,0.9f);
                waveController.addWave(unitWave);
                int waveCount = (id+1)/3;
                for (int i = 0; i<waveCount; i++) {
                    unitWave = new UnitWave(iAddedEnemy);
                    unitWave.addEnemy(0, 2 + i, 1.5f - i/10f);
                    unitWave.addEnemy(0, 5*i, 1.5f - i/10f);
                    unitWave.addEnemy(0, 2 + 2*i, 1.5f - i/10f);
                    unitWave.addEnemy(0, 0, 5f + i);
                    waveController.addWave(unitWave);
                }

                return waveController;
            /*default:
                new Exception("Undefine id of wave controller");*/
        }
        //return null;
    }

}
