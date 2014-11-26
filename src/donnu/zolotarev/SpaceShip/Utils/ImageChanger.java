package donnu.zolotarev.SpaceShip.Utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.util.Random;

public class ImageChanger {

    private final Random random;
    private final int[] resurceImageId;
    private int imageNextId;
    private boolean needRandom = false;
//    private Timer timer;

  /*  private ImageView view;
    private long delay = 4000;
    private Drawable[] layers;
    private Runnable timerRunneble;*/

    public ImageChanger(final int[] resurceImageId) {
        //  this.view = view;
        //    this.delay = delay;
        imageNextId = 1;
        random = new Random();
        //       layers = new Drawable[2];
        this.resurceImageId = resurceImageId;
        /*timerRunneble = new Runnable() {
            @Override
            public void run() {
            //    changeImage( getNextImage() ) ;
            }
        };*/
    }

    private Drawable getNextImage(Resources res){
        imageNextId++;
        if (needRandom){
            int newId = imageNextId;
            while (newId == imageNextId) {
                newId = random.nextInt(resurceImageId.length);
            }
            imageNextId = newId;
        } else {
            imageNextId = (imageNextId < resurceImageId.length) ? imageNextId : 0;
        }
        return res.getDrawable(resurceImageId[imageNextId]);
    }

    public void changeImageView(Resources res, ImageView newView){
        /*if (view != null){
            Drawable drawable = view.getDrawable();
            view = newView;
            changeImage(drawable);
        }else{*/
        //      view = newView;
        //  changeImage();
        newView.setImageBitmap(Bitmap.createBitmap(((BitmapDrawable) getNextImage(res)).getBitmap()));
        //  }
    }

 /*   public void start(){
        stop();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
              //  context.runOnUiThread(timerRunneble);
            }
        }, 0, delay);

    }*/

   /* private void changeImage(Drawable drawable){
        if (view != null){
            Drawable oldDrawable = view.getDrawable();

            if (oldDrawable != null){

                layers[0] = oldDrawable;
                layers[1] = drawable;
                final TransitionDrawable tr = new TransitionDrawable(layers);
                tr.startTransition(2000);
             //   clearImageView(view);
                view.setImageDrawable(tr);
            }else{
                view.setImageDrawable(drawable);
            }
        }
    }*/

 /*   public void stop(){
        if (timer != null){
            timer.cancel();
            timer = null;
        }
    }*/

    public void needRandom(boolean needRandom) {
        this.needRandom = needRandom;
    }

}
