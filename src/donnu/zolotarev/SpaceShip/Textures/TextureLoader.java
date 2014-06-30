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

    public static void loadTexture(Context context,Engine engine){
        TextureManager tm = engine.getTextureManager();
        BitmapTextureAtlas mTexture = new BitmapTextureAtlas(tm, 1024, 768,
                                                             TextureOptions.NEAREST_PREMULTIPLYALPHA);
        ship = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mTexture, context, "ship.png", 0, 0);
        tm.loadTexture(mTexture);
    }

    public static TextureRegion getShip() {
        return ship;
    }
}
