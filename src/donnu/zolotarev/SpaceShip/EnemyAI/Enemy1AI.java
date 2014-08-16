package donnu.zolotarev.SpaceShip.EnemyAI;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Enemy1AI extends SimpleAI {

    public Enemy1AI(ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
    }

    @Override
    protected void doUpdate() {
        turnonX();
        turnonY();
        prosecutionHero(200,500);
    }

}
