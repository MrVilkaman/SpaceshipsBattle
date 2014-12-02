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
    private float maxVelocityX = 500;
    private float maxVelocityY = 500;

    public HeroAI(ITiledTextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
       /* unregisterUpdateHandler(physicsHandler);
        physicsHandler =  new PhysicsHandler(this){
            @Override
            protected void onUpdate(float pSecondsElapsed, IEntity pEntity) {
                if(isEnabled()) {

                    final float accelerationX = this.mAccelerationX;
                    final float accelerationY = this.mAccelerationY;
                    if(accelerationX != 0 || accelerationY != 0) {
                        this.mVelocityX += accelerationX * pSecondsElapsed;
                        this.mVelocityY += accelerationY * pSecondsElapsed;
                        this.mVelocityX = Utils.equals(0,mVelocityX,maxVelocityX)?
                                mVelocityX: maxVelocityX*Utils.getSign(mVelocityX);
                        this.mVelocityY = Utils.equals(0,mVelocityY,maxVelocityY)?
                                mVelocityY: maxVelocityY*Utils.getSign(mVelocityY);
                    }

                    final float angularVelocity = this.mAngularVelocity;
                    if(angularVelocity != 0) {
                        pEntity.setRotation(pEntity.getRotation() + angularVelocity * pSecondsElapsed);
                    }

                    final float velocityX = this.mVelocityX;
                    final float velocityY = this.mVelocityY;
                    if(velocityX != 0 || velocityY != 0) {
                        pEntity.setPosition(pEntity.getX() + velocityX * pSecondsElapsed, pEntity.getY() + velocityY * pSecondsElapsed);
                    }
                }

            }
        };
        registerUpdateHandler(physicsHandler);*/
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
