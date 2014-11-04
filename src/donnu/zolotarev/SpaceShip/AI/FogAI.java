package donnu.zolotarev.SpaceShip.AI;

import donnu.zolotarev.SpaceShip.AI.BulletAI.MeteorAI;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class FogAI extends MeteorAI {
    private int acc;
    private int accDeff = 40;
    private int counter = 0;

    public FogAI(ITiledTextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
        acc = (int) Utils.random(0.6f*accDeff,1.4f*accDeff);
    }

    @Override
    public void restart() {
        super.restart();
        acc = (int) Utils.random(0.6f*accDeff,1.4f*accDeff);
    }

    @Override
    protected void doAfterUpdate() {
        super.doAfterUpdate();
        counter--;
        if(counter<0){
            counter = 50;
            physicsHandler.setAccelerationY(acc);
            physicsHandler.setVelocityY(0);
            acc *= -1;
        }

    }
}
