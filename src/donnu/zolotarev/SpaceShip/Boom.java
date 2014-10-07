package donnu.zolotarev.SpaceShip;

import android.graphics.PointF;
import donnu.zolotarev.SpaceShip.Activity.GameActivity;
import donnu.zolotarev.SpaceShip.Scenes.BaseGameScene;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.util.adt.pool.GenericPool;

public class Boom extends AnimatedSprite {

    protected static GenericPool<Boom> bulletsPool;

    private Boom() {
        super(0, 0, TextureLoader.getmBoomTextureRegion(), GameActivity.engine().getVertexBufferObjectManager());
        BaseGameScene.getActiveScene().attachChild(this);
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
                destroy();
            }

            @Override
            public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {

            }
        });
    }

    private void destroy() {
        bulletsPool.recyclePoolItem(this);
        setVisible(false);
        setIgnoreUpdate(true);
    }

    public static void run(BaseUnit baseUnit) {

        if (bulletsPool == null){
            bulletsPool = new GenericPool<Boom>() {
                @Override
                protected Boom onAllocatePoolItem() {
                    return new Boom();
                }
            };
        }

        Boom boom = bulletsPool.obtainPoolItem();
        PointF f = baseUnit.getPosition();
        boom.animate();
        boom.setPosition(f.x - boom.getWidth()/2 ,f.y- boom.getHeight()/2);
        boom.setVisible(true);
        boom.setIgnoreUpdate(false);

    }
}
