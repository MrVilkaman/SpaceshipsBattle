package donnu.zolotarev.SpaceShip.EnemyAI;

import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class SimpleAI extends SpriteAI {

    boolean flagFirstX = false;
    boolean flagFirstY = false;
    public SimpleAI(ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
     //   physicsHandler.setAccelerationY(10);

    }

    @Override
    public void restart() {
        rotateAngle = 15;
        flagFirstX = false;
        flagFirstY = false;
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        doBeforeUpdate();
        super.onManagedUpdate(pSecondsElapsed);
        if (this.mX < -this.getWidth() ){
            if (flagFirstX){
                mX = SpaceShipActivity.getCameraWidth();
            }
        }else if (this.mX > SpaceShipActivity.getCameraWidth()){
            if (flagFirstX){
                mX = -this.getWidth();
            }
        }else{
            flagFirstX = true;
        }

        if (this.mY < -this.getHeight()  ){
            if (flagFirstY){
                mY = SpaceShipActivity.getCameraHeight();
            }
        } else if (this.mY > SpaceShipActivity.getCameraHeight()){
            if (flagFirstY){
                mY = -this.getHeight();
            }
        } else{
            flagFirstY = true;
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
