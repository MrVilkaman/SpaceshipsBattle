package donnu.zolotarev.SpaceShip.EnemyAI;

import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Simple2AI extends SpriteAI {

    public Simple2AI(ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
     //   physicsHandler.setAccelerationY(10);
        rotateAngle = 15;
    }

    boolean flagFirstX = false;
    boolean flagFirstY = false;

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        doBeforeUpdate();
        super.onManagedUpdate(pSecondsElapsed);
        if (this.mX < -this.getWidth() ){
            if (flagFirstX){
                mRotation = Utils.dAngleDegree(mRotation, 180)/2;
            }
        }else if (this.mX > SpaceShipActivity.getCameraWidth()){
            if (flagFirstX){
                mRotation = 180 + Utils.dAngleDegree(mRotation, 0)/2;
            }
        }else{
            flagFirstX = true;
        }

        if (this.mY < -this.getHeight()  ){
            if (flagFirstY){
                mRotation =  Utils.dAngleDegree(mRotation, 270)/2;
            }
        } else if (this.mY > SpaceShipActivity.getCameraHeight()){
            if (flagFirstY){
                mRotation = 270+ Utils.dAngleDegree(mRotation, 90)/2;
            }
        }else{
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
