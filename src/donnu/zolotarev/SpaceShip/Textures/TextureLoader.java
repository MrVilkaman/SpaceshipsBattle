package donnu.zolotarev.SpaceShip.Textures;

import android.content.Context;
import org.andengine.engine.Engine;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;

public class TextureLoader {

    private static TextureRegion ship;
    private static TextureRegion screenControlKnobTextureRegion;
    private static TextureRegion screenControlBaseTextureRegion;
    private static TextureRegion simpleBulletTextureRegion;
    private static TextureRegion enemyShip;

    public static void loadTexture(Context context, Engine engine) {
        TextureManager tm = engine.getTextureManager();
        BitmapTextureAtlas mTexture = new BitmapTextureAtlas(tm, 1024, 768, TextureOptions.NEAREST_PREMULTIPLYALPHA);
        ship = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mTexture, context, "ship.png", 0, 0);
        enemyShip = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mTexture, context, "alienblaster.png", 0,225);
        tm.loadTexture(mTexture);
        BitmapTextureAtlas mOnScreenControlTexture = new BitmapTextureAtlas(tm, 256, 128,
                TextureOptions.BILINEAR);
        screenControlBaseTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                mOnScreenControlTexture, context, "onscreen_control_base.png", 0, 0);
        screenControlKnobTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                mOnScreenControlTexture, context, "onscreen_control_knob.png", 130, 0);
        mOnScreenControlTexture.load();

        BitmapTextureAtlas bulletTexture = new BitmapTextureAtlas(tm, 128, 128,
                TextureOptions.BILINEAR);
        simpleBulletTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                bulletTexture, context, "simpleBullet.png", 0, 0);


    }

    public static TextureRegion getShip() {
        return ship;
    }

    public static TextureRegion getScreenControlBaseTextureRegion() {
        return screenControlBaseTextureRegion;
    }

    public static TextureRegion getScreenControlKnobTextureRegion() {
        return screenControlKnobTextureRegion;
    }

    public static TextureRegion getSimpleBulletTextureRegion() {
        return simpleBulletTextureRegion;
    }

    public static TextureRegion getEnemyShip() {
        return enemyShip;
    }
}
