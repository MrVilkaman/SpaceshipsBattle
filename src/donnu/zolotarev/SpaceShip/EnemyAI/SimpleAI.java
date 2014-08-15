package donnu.zolotarev.SpaceShip.EnemyAI;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class SimpleAI extends SpriteAI {

    public SimpleAI(ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);

    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        doBeforeUpdate();
        if(mX <250){
            physicsHandler.setVelocityX(0);
            mX = 249;
        }
        super.onManagedUpdate(pSecondsElapsed);
        doAfterUpdate();
        // todo !!!
       //
    }



}
