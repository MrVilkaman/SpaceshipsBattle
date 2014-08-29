package donnu.zolotarev.SpaceShip.AI.BulletAI;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class SimpleBulletAI extends BulletBaseAI {
    public SimpleBulletAI(ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
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
