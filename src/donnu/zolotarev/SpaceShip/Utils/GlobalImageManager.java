package donnu.zolotarev.SpaceShip.Utils;

import android.app.Activity;
import android.widget.ImageView;

public  class GlobalImageManager {

    private static ImageChanger changer;

    private GlobalImageManager(){}

    public static void configuration(Activity activities, int[] images,int del ){
        if (changer == null){
            changer =  new ImageChanger(activities,null, images,del*1000);
        }
    }

    public static void changeImageView(ImageView imageView){
        changer.changeImageView(imageView);
        changer.start();
    }

    public static void stop(){
        changer.stop();
    }
}
