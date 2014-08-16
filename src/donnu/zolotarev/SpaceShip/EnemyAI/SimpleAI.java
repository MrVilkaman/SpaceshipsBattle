package donnu.zolotarev.SpaceShip.EnemyAI;

import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public abstract class SimpleAI extends SpriteAI {

    boolean flagFirstX = false;
    boolean flagFirstY = false;
    public SimpleAI(ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
     //   physicsHandler.setAccelerationY(10);

    }

    @Override
    public void restart() {
        flagFirstX = false;
        flagFirstY = false;
    }
    /* if (! Utils.equals(mRotation, 180 + rotateAngle, 0.1f)){
            mRotation -= Utils.dAngleDegree(mRotation,180+ rotateAngle)/10f;
        }else{
            mRotation = 180+ rotateAngle;
            rotateAngle*=-1;
        }*/
    protected final void flyThroughX(){
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
    }

    protected final void flyThroughY(){
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
    }

    protected final void reflectionFromX(){
        if (this.mX < -this.getWidth() ){
            if (flagFirstX){
                mRotation = -Utils.dAngleDegree(mRotation, 180);
            }
        }else if (this.mX > SpaceShipActivity.getCameraWidth()){
            if (flagFirstX){
                mRotation = 180 - Utils.dAngleDegree(mRotation, 0);
            }
        }else{
            flagFirstX = true;
        }
    }

    protected final void reflectionFromY(){
        if (this.mY < -this.getHeight()  ){
            if (flagFirstY){
                mRotation = 90 - Utils.dAngleDegree(mRotation, 270);
            }
        } else if (this.mY > SpaceShipActivity.getCameraHeight()){
            if (flagFirstY){
                mRotation = 270 - Utils.dAngleDegree(mRotation, 90);
            }
        }else{
            flagFirstY = true;
        }
    }

}
