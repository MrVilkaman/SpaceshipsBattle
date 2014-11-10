package donnu.zolotarev.SpaceShip.AI;

import donnu.zolotarev.SpaceShip.Units.WaySpecifications;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class TurretAI extends SpriteAI {


    private final int hW;
    private final int hH;

    public TurretAI(ITiledTextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
        hW = (int) getWidth()/2;
        hH = (int) getHeight()/2;
    }

    @Override
    protected void doUpdate() {

    }

    @Override
    protected void doInternalUpdate() {

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
}
