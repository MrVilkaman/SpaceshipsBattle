package donnu.zolotarev.SpaceShip.AI.EnemyAI;

import donnu.zolotarev.SpaceShip.AI.SimpleAI;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class RocketAI extends SimpleAI {


    public RocketAI(ITiledTextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
    }

    @Override
    protected void doUpdate() {
        turnonX();
        turnonY();
        prosecutionHero(150,800);
    }



}
