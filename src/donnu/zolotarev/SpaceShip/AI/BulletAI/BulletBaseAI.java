package donnu.zolotarev.SpaceShip.AI.BulletAI;

import donnu.zolotarev.SpaceShip.AI.SimpleAI;
import donnu.zolotarev.SpaceShip.GameActivity;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public abstract class BulletBaseAI extends SimpleAI {
    public BulletBaseAI(ITiledTextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
    }

    protected void destroyOutScreen(){
        if(this.mX < 0) {
            if(flagFirstX){
                destroyed();
            }
        } else if(this.mX + this.getWidth() > GameActivity.getCameraWidth()) {
            if(flagFirstX){
                destroyed();
            }
        }else {
            flagFirstX = true;
        }

        if(this.mY < 0) {
            if(flagFirstY){
                destroyed();
            }
        } else if(this.mY + this.getHeight() > GameActivity.getCameraHeight()) {
            if(flagFirstY){
                destroyed();
            }
        }else {
             flagFirstY = true;
        }
    }

    protected abstract void destroyed();
}
