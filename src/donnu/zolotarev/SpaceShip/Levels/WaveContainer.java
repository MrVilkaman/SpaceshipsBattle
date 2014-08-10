package donnu.zolotarev.SpaceShip.Levels;

import donnu.zolotarev.SpaceShip.Waves.IAddedEnemy;
import donnu.zolotarev.SpaceShip.Waves.IWaveController;
import donnu.zolotarev.SpaceShip.Waves.SimpleWave;
import donnu.zolotarev.SpaceShip.Waves.UnitWave;

public  class WaveContainer {
    public static final int LEVEL_INFINITY = 99;
    public static final int LEVEL_1 = 1;
    public static final int LEVEL_2 = LEVEL_1+1;
    public static final int LEVEL_3 = LEVEL_2+1;
    public static final int LEVEL_4 = LEVEL_3+1;
    public static final int LEVEL_5 = LEVEL_4+1;
    public static final int LEVEL_6 = LEVEL_5+1;
    public static final int LEVEL_TEST = LEVEL_6+1;


    public static IWaveController getWaveControllerById(int id,IAddedEnemy iAddedEnemy){
        return get(id,iAddedEnemy);
    }

    private static IWaveController get(int id,IAddedEnemy iAddedEnemy) {
        SimpleWave waveController;
        UnitWave unitWave;
        switch (id){
            case LEVEL_1:
                waveController = new SimpleWave();
                 //todo убрать коментарии
                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addEnemy(0, 1, 0.4f);
               /* unitWave.addEnemy(0, 7, 1.2f);
                unitWave.addEnemy(0, 4, 0.2f);
                unitWave.addEnemy(0,7,0.8f);*/
                waveController.addWave(unitWave);
                return waveController;
            case LEVEL_2:
                waveController = new SimpleWave();

                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addEnemy(0, 1, 0.4f);
               /* unitWave.addEnemy(0, 8, 1.1f);
                unitWave.addEnemy(0, 7, 0.3f);
                unitWave.addEnemy(0,10,0.9f);*/
                waveController.addWave(unitWave);
                return waveController;
           // case LEVEL_3:
            default:
                waveController = new SimpleWave();

                unitWave = new UnitWave(iAddedEnemy);
                unitWave.addEnemy(0, 1, 0.4f);
               /* unitWave.addEnemy(0, 10, 1.2f);
                unitWave.addEnemy(0, 10, 0.4f);
                unitWave.addEnemy(0,5,0.9f);*/
                waveController.addWave(unitWave);

                /*unitWave = new UnitWave(iAddedEnemy);
                unitWave.addEnemy(0, 15, 1.5f);
                waveController.addWave(unitWave);*/

                return waveController;
            /*default:
                new Exception("Undefine id of wave controller");*/
        }
        //return null;
    }

}
