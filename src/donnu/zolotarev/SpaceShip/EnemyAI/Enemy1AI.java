package donnu.zolotarev.SpaceShip.EnemyAI;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Enemy1AI extends SimpleAI {

    public Enemy1AI(ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
    }

    @Override
    protected void doUpdate() {
      /* reflectionFromX();
       reflectionFromY();*/
        turnonX();
        turnonY();
//       flyThroughY();
        prosecutionHero(400,700);
    }

}
