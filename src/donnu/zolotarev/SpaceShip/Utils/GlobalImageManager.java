package donnu.zolotarev.SpaceShip.Utils;

import android.content.Context;
import android.widget.ImageView;

public  class GlobalImageManager {

    private static ImageChanger changer;

    private GlobalImageManager(){}

    public static void configuration(int[] images, int del){
        if (changer == null){
            changer =  new ImageChanger(null, images,del*1000);
            changer.needRandom(true);
        }
    }

    public static void changeImageView(Context context, ImageView imageView){
        if (changer != null){
            changer.changeImageView(context.getResources(), imageView);
        //    changer.start();
            System.gc();
        }
    }

    public static void stop(){
        if (changer != null){
            changer.stop();
        }
    }

    public static void clearImageView(ImageView imageBack) {
        AppUtils.clearImageView(imageBack);
        System.gc();

    }
}
