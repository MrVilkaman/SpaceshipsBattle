package donnu.zolotarev.SpaceShip.Utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class ImageChangeManager {

    private final Activity context;
    private final int[] resurceImageId;
    private final ImageView view;
    private final Resources res;
    private long delay = 4000;
    private int image1;
    private int image2;

    public ImageChangeManager(Activity context,ImageView view, int[] resurceImageId) {
       this.context = context;
       this.view = view;
       this.resurceImageId = resurceImageId;
       res = context.getResources();

       view.setImageDrawable(res.getDrawable(resurceImageId[0]));
       image1 = 0;
       image2 = 1;
    }

    public void start(){
      new Timer().schedule(new TimerTask() {
          @Override
          public void run() {
              context.runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      image1 = image2;
                      image2++;
                      image2 = (image2 < resurceImageId.length)? image2: 0;
                      Drawable[] layers = new Drawable[2];
                      layers[0] = res.getDrawable(resurceImageId[image1]);
                      layers[1] = res.getDrawable(resurceImageId[image2]);

                      final TransitionDrawable tr = new TransitionDrawable(layers);
                      tr.startTransition(1000);
                      view.setImageDrawable(tr);
                  }
              });
          }
      },0,delay);
    }

}
