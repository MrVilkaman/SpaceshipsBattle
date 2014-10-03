package donnu.zolotarev.SpaceShip.Utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class ImageChanger {

    private final Activity context;
    private final int[] resurceImageId;
    private ImageView view;
    private final Resources res;
    private long delay = 4000;
    private int imageNextId;
    private Timer timer;

    public ImageChanger(Activity context, ImageView view, int[] resurceImageId,long delay) {
       this.context = context;
       this.view = view;
       this.resurceImageId = resurceImageId;
       res = context.getResources();
       this.delay = delay;
       changeImage(res.getDrawable(resurceImageId[0]));
       imageNextId = 1;
    }

    public void changeImageView(ImageView newView){
        if (view != null){
            Drawable drawable = view.getDrawable();
            view = newView;
            changeImage(drawable);
        }else{
            view = newView;
        }
    }

    public void start(){
        if (timer != null){
            stop();
        }
        timer =  new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageNextId++;
                        imageNextId = (imageNextId < resurceImageId.length) ? imageNextId : 0;
                        changeImage( res.getDrawable(resurceImageId[imageNextId])) ;
                    }
                });
            }
        }, 0, delay);
    }

    private void changeImage(Drawable drawable){
        if (view != null){
            Drawable oldDrawable = view.getDrawable();

            if (oldDrawable != null){
                Drawable[] layers = new Drawable[2];
                layers[0] = oldDrawable;
                layers[1] = drawable;

                final TransitionDrawable tr = new TransitionDrawable(layers);
                tr.startTransition(2000);
                view.setImageDrawable(tr);
            }else{
                view.setImageDrawable(drawable);
            }
        }
    }

    public void stop(){
        timer.cancel();
        timer = null;
    }

}
