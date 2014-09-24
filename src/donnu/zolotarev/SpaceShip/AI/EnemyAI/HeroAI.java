package donnu.zolotarev.SpaceShip.AI.EnemyAI;

import donnu.zolotarev.SpaceShip.AI.SpriteAI;
import donnu.zolotarev.SpaceShip.Activity.GameActivity;
import donnu.zolotarev.SpaceShip.Units.WaySpecifications;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class HeroAI extends SpriteAI {
    private float xOld;
    private float yOld;

    public HeroAI(ITiledTextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
    }

    @Override
    protected void doBeforeUpdate() {
        xOld = mX;
        yOld = mY;
    }

    @Override
    protected void doUpdate() {
        if (this.mX < 0 || this.mX + this.getWidth() > GameActivity.getCameraWidth()){
            mX = xOld;
        }

        if (this.mY < 0 || this.mY + this.getHeight() > GameActivity.getCameraHeight()){
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
    protected final void doInternalUpdate() {

    }

    @Override
    public void restart() {

    }

    @Override
    public void start(WaySpecifications waySpecifications) {

    }
}
