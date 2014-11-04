package donnu.zolotarev.SpaceShip.AI.BulletAI;

import donnu.zolotarev.SpaceShip.AI.SimpleAI;
import donnu.zolotarev.SpaceShip.Activity.GameActivity;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public abstract class BulletBaseAI extends SimpleAI {
    public BulletBaseAI(ITiledTextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
    }

    protected void destroyOutScreen(){
        if(this.mX < - this.getWidthScaled ()) {
            if(flagFirstX){
                destroyed();
            }
        } else if(this.mX - this.getWidthScaled() > GameActivity.getCameraWidth()) {
            if(flagFirstX){
                destroyed();
            }
        }else {
            flagFirstX = true;
        }

        if(this.mY < - this.getHeightScaled()) {
            if(flagFirstY){
                destroyed();
            }
        } else if(this.mY - this.getHeightScaled() > GameActivity.getCameraHeight()) {
            if(flagFirstY){
                destroyed();
            }
        }else {
             flagFirstY = true;
        }
    }

    protected abstract void destroyed();
}
