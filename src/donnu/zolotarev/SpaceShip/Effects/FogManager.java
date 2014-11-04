package donnu.zolotarev.SpaceShip.Effects;

import donnu.zolotarev.SpaceShip.Textures.TextureLoader;

public class FogManager {

    public static int speed = -50;

    public static boolean isActive = false;

    private static float fps = 140;
    private static float maxCounter;
    private static int counter = 0;
    private static boolean flag = true;

    public static void fogOn(){
        isActive = true;
        int size = (int) TextureLoader.getFogTextureRegion().getWidth(0);
        maxCounter =  ((-1*size*fps)/speed);
    }

    public static void fogOff(){
        isActive = false;
    }

    public static void fogUpdate(){
        if (isActive){
            if (checkThatReady()){
                create();
            }
        }
    }

    private static boolean checkThatReady() {
        counter--;
        if (counter<0){
            counter = (int)maxCounter;
            return true;
        }
        return false;
    }

    private static void create(){
        if (flag){
            for (int i = 0; i < 3; i++) {
                Fog.run(100 + i*250,speed);
            }
        } else {
            for (int i = 0; i < 4; i++) {
                Fog.run(0 + i*250,speed);
            }
        }
        flag = !flag;
    }
}
