package donnu.zolotarev.SpaceShip.EnemyAI;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public abstract class SpriteAI extends Sprite {

    protected PhysicsHandler physicsHandler;
    protected float rotateAngle = 0;

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

    public abstract void restart();
}
