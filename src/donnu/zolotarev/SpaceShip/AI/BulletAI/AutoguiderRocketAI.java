package donnu.zolotarev.SpaceShip.AI.BulletAI;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class AutoguiderRocketAI extends BulletBaseAI {
    private boolean autoguider = false;

    public AutoguiderRocketAI(ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
    }

    @Override
    protected void destroyed() {

    }

    @Override
    protected void doUpdate() {
        if (autoguider){
            prosecutionHero(0,800,false);
        }
        destroyOutScreen();
    }

    public void setAutoguider(){
        autoguider = true;
    }

    public void setNoAutoguider(){
        autoguider = false;
    }
}
