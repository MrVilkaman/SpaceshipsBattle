package donnu.zolotarev.SpaceShip.AI.EnemyAI;

import donnu.zolotarev.SpaceShip.AI.SimpleAI;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Boss1AI extends SimpleAI {


    public Boss1AI(ITiledTextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
        startTimeScan = 4;
    }

    @Override
    protected void doUpdate() {
        turnonX();
        turnonY();
    }



}
