package donnu.zolotarev.SpaceShip.Effects;

import android.opengl.GLES20;
import donnu.zolotarev.SpaceShip.Activity.GameActivity;
import org.andengine.entity.particle.SpriteParticleSystem;
import org.andengine.entity.particle.emitter.IParticleEmitter;
import org.andengine.entity.particle.initializer.*;
import org.andengine.entity.particle.modifier.AlphaParticleModifier;
import org.andengine.entity.particle.modifier.ColorParticleModifier;
import org.andengine.entity.particle.modifier.ExpireParticleInitializer;
import org.andengine.entity.particle.modifier.ScaleParticleModifier;
import org.andengine.entity.sprite.Sprite;

public class Fire extends SpriteParticleSystem {

    private static final float RATE_MIN    = 10;
    private static final float RATE_MAX	   = 20;
    private static final int PARTICLES_MAX = 100;


    public Fire(IParticleEmitter pParticleEmitter) {
        super(pParticleEmitter, RATE_MIN, RATE_MAX, PARTICLES_MAX, null/*TextureLoader.getmParticleTextureRegion()*/,
                GameActivity.engine().getVertexBufferObjectManager());
        qwer();
    }

    private void qwer() {
        addParticleInitializer(new BlendFunctionParticleInitializer<Sprite>(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE));
        addParticleInitializer(new VelocityParticleInitializer<Sprite>(15, 22, -60, -90));
        addParticleInitializer(new AccelerationParticleInitializer<Sprite>(5, 15));
        addParticleInitializer(new RotationParticleInitializer<Sprite>(0.0f, 360.0f));
        addParticleInitializer(new ColorParticleInitializer<Sprite>(1.0f, 0.0f, 0.0f));
        addParticleInitializer(new ExpireParticleInitializer<Sprite>(11.5f));

        addParticleModifier(new ScaleParticleModifier<Sprite>(0, 5, 0.5f, 2.0f));
        addParticleModifier(new AlphaParticleModifier<Sprite>(2.5f, 3.5f, 1.0f, 0.0f));
        addParticleModifier(new AlphaParticleModifier<Sprite>(3.5f, 4.5f, 0.0f, 1.0f));
        addParticleModifier(new ColorParticleModifier<Sprite>(0.0f, 11.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f));
        addParticleModifier(new AlphaParticleModifier<Sprite>(4.5f, 11.5f, 1.0f, 0.0f));
    }


}
