package donnu.zolotarev.SpaceShip.Effects;

import donnu.zolotarev.SpaceShip.AI.BulletAI.MeteorAI;
import donnu.zolotarev.SpaceShip.Activity.GameActivity;
import donnu.zolotarev.SpaceShip.Scenes.BaseGameScene;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Units.WaySpecifications;
import donnu.zolotarev.SpaceShip.Utils.Constants;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import org.andengine.util.adt.pool.GenericPool;

public class Fog extends MeteorAI {

    protected static GenericPool<Fog> fogPool;
    private static WaySpecifications waySpecifications;

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

    public static void run(int y,int speed) {
        // todo удалять при выходе из игры!
        if (fogPool == null){
            fogPool = new GenericPool<Fog>() {
                @Override
                protected Fog onAllocatePoolItem() {
                    return new Fog();
                }
            };
        }
        if (waySpecifications == null){
            waySpecifications = new WaySpecifications((int) Utils.random(speed*0.9f,speed*1.1f), 180);
        }

        Fog fog = fogPool.obtainPoolItem();
        fog.start(waySpecifications);
        fog.setPosition(Constants.CAMERA_WIDTH+170,y);
        fog.setVisible(true);
        fog.setIgnoreUpdate(false);
        fog.setRotation(Utils.random(-30,30));
    }

    @Override
    protected void destroyed() {
        destroy();
    }
}
