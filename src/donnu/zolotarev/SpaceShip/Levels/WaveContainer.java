package donnu.zolotarev.SpaceShip.Levels;

import donnu.zolotarev.SpaceShip.Waves.IAddedEnemy;
import donnu.zolotarev.SpaceShip.Waves.IWaveController;
import donnu.zolotarev.SpaceShip.Waves.SimpleWave;
import donnu.zolotarev.SpaceShip.Waves.UnitWave;

public  class WaveContainer {

    public static IWaveController getWaveControllerById(int id,IAddedEnemy iAddedEnemy){
        return get(id,iAddedEnemy);
    }

    private static IWaveController get(int id,IAddedEnemy iAddedEnemy) {
        SimpleWave waveController;
        switch (id){
            case 1:
                waveController = new SimpleWave();

                UnitWave unitWave = new UnitWave(iAddedEnemy);
                unitWave.addEnemy(0, 7, 0.2f);

                waveController.addWave(unitWave);
                return waveController;
            default:
                new Exception("Undefine id of wave controller");
        }
        return null;
    }

}
