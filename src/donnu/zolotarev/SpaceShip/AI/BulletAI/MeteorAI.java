package donnu.zolotarev.SpaceShip.AI.BulletAI;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class MeteorAI extends BulletBaseAI {
    public MeteorAI(ITiledTextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
    }

    @Override
    protected void doUpdate() {
       destroyOutScreen();
    }

    @Override
    protected void destroyed() {

    }

}
