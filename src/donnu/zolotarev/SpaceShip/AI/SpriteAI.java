package donnu.zolotarev.SpaceShip.AI;

import donnu.zolotarev.SpaceShip.Units.WaySpecifications;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public abstract class SpriteAI extends Sprite {

    protected PhysicsHandler physicsHandler;
    protected float rotateAngle = 0;
    private float oldAngle = -999;

    public SpriteAI(ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(0, 0, pTextureRegion, pVertexBufferObjectManager);
        physicsHandler = new PhysicsHandler(this);
        registerUpdateHandler(physicsHandler);
    }

    public PhysicsHandler getPhysicsHandler() {
        return physicsHandler;
    }

    public void setRotateAngle(float rotateAngle) {
        this.rotateAngle = rotateAngle;
    }

    protected void doBeforeUpdate(){

    }

    protected void doAfterUpdate(){
    }

    protected abstract void doUpdate();

    protected abstract void doInternalUpdate();

    public abstract void restart();

    public abstract void start(WaySpecifications waySpecifications);

    @Override
    protected final void onManagedUpdate(float pSecondsElapsed) {
        doBeforeUpdate();
        super.onManagedUpdate(pSecondsElapsed);
        doUpdate();
        doInternalUpdate();
        doAfterUpdate();
    }

    protected void updateSpriteRotarion(int speed){
        if (oldAngle != mRotation){
            oldAngle =  mRotation;
            physicsHandler.setVelocityX((float)(speed * Math.cos(Utils.degreeToRad(oldAngle))));
            physicsHandler.setVelocityY((float) (speed * Math.sin(Utils.degreeToRad(oldAngle))));
        }
    }
}
