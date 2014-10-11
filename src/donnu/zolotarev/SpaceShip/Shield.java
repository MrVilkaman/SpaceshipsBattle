package donnu.zolotarev.SpaceShip;

import android.graphics.PointF;
import donnu.zolotarev.SpaceShip.Activity.GameActivity;
import donnu.zolotarev.SpaceShip.Scenes.BaseGameScene;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.util.adt.pool.GenericPool;

public class Shield extends AnimatedSprite {

    protected static GenericPool<Shield> bulletsPool;

    private Shield() {
        super(0, 0, TextureLoader.getShieldTextureRegion(), GameActivity.engine().getVertexBufferObjectManager());
        BaseGameScene.getActiveScene().attachChild(this);
    }

    private void destroy() {
        bulletsPool.recyclePoolItem(this);
        setVisible(false);
        setIgnoreUpdate(true);

    }

    public static void run(/*BaseUnit baseUnit*/) {

        if (bulletsPool == null){
            bulletsPool = new GenericPool<Shield>() {
                @Override
                protected Shield onAllocatePoolItem() {
                    return new Shield();
                }
            };
        }

        Shield boom = bulletsPool.obtainPoolItem();
        PointF f = new PointF(200,200);//baseUnit.getPosition();
        boom.setPosition(f.x - boom.getWidth()/2 ,f.y- boom.getHeight()/2);
        boom.setVisible(true);
        boom.setIgnoreUpdate(false);
    }
}
