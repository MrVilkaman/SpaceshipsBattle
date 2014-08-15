package donnu.zolotarev.SpaceShip.EnemyAI;

import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class SimpleAI extends SpriteAI {

    public SimpleAI(ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
     //   physicsHandler.setAccelerationY(10);
        rotateAngle = 15;
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        doBeforeUpdate();
        super.onManagedUpdate(pSecondsElapsed);
        if (this.mX < -this.getWidth() ){
            mX = SpaceShipActivity.getCameraWidth();
        }else if (this.mX > SpaceShipActivity.getCameraWidth()){
            mX = -this.getWidth();
        }

        if (this.mY < -this.getHeight()  ){
            mY = SpaceShipActivity.getCameraHeight();
        } else if (this.mY > SpaceShipActivity.getCameraHeight()){
            mY = -this.getHeight();
        }

       /* if (! Utils.equals(mRotation, 180 + rotateAngle, 0.1f)){
            mRotation -= Utils.dAngleDegree(mRotation,180+ rotateAngle)/10f;
        }else{
            mRotation = 180+ rotateAngle;
            rotateAngle*=-1;
        }*/


        doAfterUpdate();
    }



}
