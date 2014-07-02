package donnu.zolotarev.SpaceShip.Enemy;

import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class UnitShape extends Sprite {
    public UnitShape(float pX, float pY, ITextureRegion pTextureRegion,
            VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        if(this.mX < 0) {
            mX +=10;
        //    return;
        } else if(this.mX + this.getWidth() > SpaceShipActivity.getCameraWidth()) {
            mX -=10;
       //     return;
        }

        if(this.mY < 0) {
            mY +=10;
      //      return;
        } else if(this.mY + this.getHeight() > SpaceShipActivity.getCameraHeight()) {
            mY -=10;
      //      return;
        }
        super.onManagedUpdate(pSecondsElapsed);
    }
}
