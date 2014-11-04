package donnu.zolotarev.SpaceShip.Effects;

import android.graphics.PointF;
import donnu.zolotarev.SpaceShip.AI.BulletAI.MeteorAI;
import donnu.zolotarev.SpaceShip.Activity.GameActivity;
import donnu.zolotarev.SpaceShip.Scenes.BaseGameScene;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import org.andengine.util.adt.pool.GenericPool;

public class Fog extends MeteorAI {

    protected static GenericPool<Fog> fogPool;

    private Fog() {
        super(TextureLoader.getFogTextureRegion(), GameActivity.engine().getVertexBufferObjectManager());
         BaseGameScene.getActiveScene().attachChild(this);
        setZIndex(999);
        setScale(3);
    }


    private void destroy() {
        fogPool.recyclePoolItem(this);
        setVisible(false);
        setIgnoreUpdate(true);
    }

    public static void run( PointF f) {
        // todo удалять при выходе из игры!
        if (fogPool == null){
            fogPool = new GenericPool<Fog>() {
                @Override
                protected Fog onAllocatePoolItem() {
                    return new Fog();
                }
            };
        }

        Fog fog = fogPool.obtainPoolItem();
        fog.setPosition(f.x - fog.getWidth()/2 ,f.y- fog.getHeight()/2);
        fog.setVisible(true);
        fog.setIgnoreUpdate(false);
        fog.setRotation(Utils.random(0, 360));
    }

    @Override
    protected void destroyed() {
        destroy();
    }
}
