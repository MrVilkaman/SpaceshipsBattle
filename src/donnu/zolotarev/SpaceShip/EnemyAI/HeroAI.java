package donnu.zolotarev.SpaceShip.EnemyAI;

import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class HeroAI extends SpriteAI {
    private float xOld;
    private float yOld;

    public HeroAI(ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
    }

    @Override
    protected void doBeforeUpdate() {
        xOld = mX;
        yOld = mY;
    }

    @Override
    protected void doUpdate() {
        if (this.mX < 0 || this.mX + this.getWidth() > SpaceShipActivity.getCameraWidth()){
            mX = xOld;
        }

        if (this.mY < 0 || this.mY + this.getHeight() > SpaceShipActivity.getCameraHeight()){
            mY = yOld;
        }
        if (! Utils.equals(mRotation, rotateAngle, 0.1f)){
            //todo влияет на скорость поворота
            mRotation -= Utils.dAngleDegree(mRotation,rotateAngle)/10;
        }else{
            mRotation = rotateAngle;
        }
    }

    @Override
    public void restart() {

    }


}
