package donnu.zolotarev.SpaceShip.AI;

import donnu.zolotarev.SpaceShip.Units.WaySpecifications;
import donnu.zolotarev.SpaceShip.Utils.IGetShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class TurretAI extends SimpleAI implements IGetShape {


    private final int hW;
    private final int hH;

    public TurretAI(ITiledTextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
        hW = (int) getWidth()/2;
        hH = (int) getHeight()/2;
        specifications = new WaySpecifications(0,5f);
    }

    @Override
    protected void doUpdate() {
        prosecutionHero(0,1500,false);
    }

    @Override
    public void restart() {

    }

    @Override
    public void start(WaySpecifications waySpecifications) {

    }

    public int gethW() {
        return hW;
    }

    public int gethH() {
        return hH;
    }

    @Override
    public Sprite getShape() {
        return this;
    }
}
