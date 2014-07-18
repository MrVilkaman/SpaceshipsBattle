package donnu.zolotarev.SpaceShip.Waves;

public interface IWaveController {
    public void addWave(UnitWave unitWave);
    public void restart();
    public void restart(int number);
    public void updateWave(float pSecondsElapsed);
}
