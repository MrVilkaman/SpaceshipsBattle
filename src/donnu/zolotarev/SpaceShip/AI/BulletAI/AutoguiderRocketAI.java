package donnu.zolotarev.SpaceShip.AI.BulletAI;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class AutoguiderRocketAI extends BulletBaseAI {
    public AutoguiderRocketAI(ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
    }

    @Override
    protected void destroyed() {

    }

    @Override
    protected void doUpdate() {
        prosecutionHero(0,800,false);
        destroyOutScreen();
    }
}
