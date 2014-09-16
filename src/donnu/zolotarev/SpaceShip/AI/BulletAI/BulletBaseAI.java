package donnu.zolotarev.SpaceShip.AI.BulletAI;

import donnu.zolotarev.SpaceShip.AI.SimpleAI;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public abstract class BulletBaseAI extends SimpleAI {
    public BulletBaseAI(ITiledTextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
    }

    protected void destroyOutScreen(){
        if(this.mX < 0) {
            destroyed();
        } else if(this.mX + this.getWidth() > SpaceShipActivity.getCameraWidth()) {
            destroyed();
        }

        if(this.mY < 0) {
            destroyed();
        } else if(this.mY + this.getHeight() > SpaceShipActivity.getCameraHeight()) {
            destroyed();
        }
    }

    protected abstract void destroyed();
}
