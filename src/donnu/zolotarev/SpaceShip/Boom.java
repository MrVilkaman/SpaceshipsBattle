package donnu.zolotarev.SpaceShip;

import android.graphics.PointF;
import donnu.zolotarev.SpaceShip.Activity.GameActivity;
import donnu.zolotarev.SpaceShip.Scenes.BaseGameScene;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import org.andengine.entity.sprite.AnimatedSprite;

public class Boom extends AnimatedSprite{
    private Boom() {
        super(0, 0, TextureLoader.getmBoomTextureRegion(), GameActivity.engine().getVertexBufferObjectManager());
    }

    public void animate() {
        super.animate(40, new IAnimationListener() {
            @Override
            public void onAnimationStarted(AnimatedSprite pAnimatedSprite, int pInitialLoopCount) {

            }

            @Override
            public void onAnimationFrameChanged(AnimatedSprite pAnimatedSprite, int pOldFrameIndex,
                    int pNewFrameIndex) {

            }

            @Override
            public void onAnimationLoopFinished(AnimatedSprite pAnimatedSprite, int pRemainingLoopCount,
                    int pInitialLoopCount) {
                stopAnimation();
                GameActivity.getInstance().runOnUiThread( new Runnable() {
                    public void run() {
                        BaseGameScene.getActiveScene().detachChild(Boom.this);
                    }
                });
            }

            @Override
            public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {

            }
        });
    }

    public static void run(BaseUnit baseUnit) {
        Boom boom = new Boom();
        PointF f = baseUnit.getPosition();
        boom.animate();
        boom.setPosition(f.x - boom.getWidth()/2 ,f.y- boom.getHeight()/2);
        BaseGameScene.getActiveScene().attachChild(boom);
    }
}
