package donnu.zolotarev.SpaceShip.Textures;

import android.content.Context;
import android.graphics.Typeface;
import org.andengine.engine.Engine;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;
import org.andengine.util.texturepack.TexturePack;
import org.andengine.util.texturepack.TexturePackLoader;
import org.andengine.util.texturepack.TexturePackTextureRegionLibrary;
import org.andengine.util.texturepack.exception.TexturePackParseException;

public class TextureLoader {

    private static TexturePack texturePack1;
    private static TexturePackTextureRegionLibrary texturePackLibrary;

    private static ITiledTextureRegion ship;
    private static TextureRegion screenControlKnobTextureRegion;
    private static TextureRegion screenControlBaseTextureRegion;
    private static ITiledTextureRegion enemyShipLightBlue;
    private static ITiledTextureRegion enemyShipOrange;
    private static ITiledTextureRegion enemyShipGreen;

    private static Font font;
    private static Font fontBig;

    private static TextureRegion menuResumeTextureRegion;
    private static TextureRegion menuExitTextureRegion;
    private static TextureRegion menuNewGameTex1tureRegion;
    private static TextureRegion menuBackToMainMenuTextureRegion;
    private static TextureRegion menuRestartTextureRegion;
    private static TextureRegion menuAboutTextureRegion;

    private static TextureRegion changeLevelLableTextureRegion1;
    private static TextureRegion changeLevelIconShop;

    private static ITiledTextureRegion rocketAmmoTextureRegion;
    private static ITiledTextureRegion simpleBulletTextureRegion;
    private static TiledTextureRegion mMeteorite1TextureRegion;
    private static TextureRegion gameBK;
    private static ITiledTextureRegion btnFire1;
    private static ITiledTextureRegion btnFire2;
    private static TiledTextureRegion mBoomTextureRegion;
    private static TextureRegion mParticleTextureRegion;

    public static void loadTexture(Context context, Engine engine) {

        TextureManager tm = engine.getTextureManager();
        FontManager fm = engine.getFontManager();

        try
        {
            texturePack1 = new TexturePackLoader(context.getAssets(),tm).loadFromAsset("gfx/TexturesPack1.xml", "gfx/");
            texturePack1.loadTexture();
            texturePackLibrary = texturePack1.getTexturePackTextureRegionLibrary();

            ship = texturePackLibrary.getTiled(TexturesPack1ID.SHIP_ID);
            screenControlKnobTextureRegion = texturePackLibrary.get(TexturesPack1ID.ONSCREEN_CONTROL_KNOB_ID);
            screenControlBaseTextureRegion = texturePackLibrary.get(TexturesPack1ID.ONSCREEN_CONTROL_BASE_ID);
            enemyShipLightBlue = texturePackLibrary.getTiled(TexturesPack1ID.ALIENBLASTER_ID);
            enemyShipOrange = texturePackLibrary.getTiled(TexturesPack1ID.ALIENBLASTER_2_ID);
            enemyShipGreen = texturePackLibrary.getTiled(TexturesPack1ID.ALIENBLASTER_3_ID);

            rocketAmmoTextureRegion = texturePackLibrary.getTiled(TexturesPack1ID.ROKET_AMMO_ID);
            simpleBulletTextureRegion  = texturePackLibrary.getTiled(TexturesPack1ID.SIMPLEBULLET_ID);
            btnFire1 = texturePackLibrary.getTiled(TexturesPack1ID.BTN_FIRE_1_ID);
            btnFire2 = texturePackLibrary.getTiled(TexturesPack1ID.BTN_FIRE_2_ID);

            menuResumeTextureRegion  = texturePackLibrary.get(TexturesPack1ID.MENU_RESUME_ID);
            menuExitTextureRegion = texturePackLibrary.get(TexturesPack1ID.MENU_EXIT_ID);
            menuBackToMainMenuTextureRegion = texturePackLibrary.get(TexturesPack1ID.MENU_TO_MAINMENU_ID);
            menuRestartTextureRegion = texturePackLibrary.get(TexturesPack1ID.MENU_RESTART_ID);




        }
        catch (final TexturePackParseException e)
        {
            Debug.e(e);
        }

        BitmapTextureAtlas gameBKTexture = new BitmapTextureAtlas(tm, 2048, 1024, TextureOptions.BILINEAR);
        gameBK = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBKTexture, context, "bk_game.jpg", 0, 0);

        gameBKTexture.load();

        font = FontFactory.create(fm, tm, 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32,
                Color.WHITE_ABGR_PACKED_INT);
        font.load();

    }

    public static ITiledTextureRegion getShip() {
        return ship;
    }

    public static TextureRegion getScreenControlBaseTextureRegion() {
        return screenControlBaseTextureRegion;
    }

    public static TextureRegion getScreenControlKnobTextureRegion() {
        return screenControlKnobTextureRegion;
    }

    public static ITiledTextureRegion getSimpleBulletTextureRegion() {
        return simpleBulletTextureRegion;
    }

    public static ITiledTextureRegion getEnemyShipLightBlue() {
        return enemyShipLightBlue;
    }

    public static ITiledTextureRegion getEnemyShipOrange() {
        return enemyShipOrange;
    }

    public static ITiledTextureRegion getEnemyShipGreen() {
        return enemyShipGreen;
    }

    public static Font getFont() {
        return font;
    }

    public static Font getFontBig() {
        return fontBig;
    }

    public static TextureRegion getMenuResumeTextureRegion() {
        return menuResumeTextureRegion;
    }

    public static TextureRegion getMenuExitTextureRegion() {
        return menuExitTextureRegion;
    }

    /*public static TextureRegion getMenuNewGameTextureRegion() {
        return menuNewGameTextureRegion;
    }*/

    public static TextureRegion getMenuBackToMainMenuTextureRegion() {
        return menuBackToMainMenuTextureRegion;
    }

    public static TextureRegion getMenuRestartTextureRegion() {
        return menuRestartTextureRegion;
    }

    public static TextureRegion getChangeLevelLableTextureRegion1() {
        return changeLevelLableTextureRegion1;
    }

    public static TextureRegion getChangeLevelIconShop() {
        return changeLevelIconShop;
    }

    public static TextureRegion getMenuAboutTextureRegion() {
        return menuAboutTextureRegion;
    }

    public static ITiledTextureRegion getRocketAmmoTextureRegion() {
        return rocketAmmoTextureRegion;
    }

    public static TiledTextureRegion getmMeteorite1TextureRegion() {
        return mMeteorite1TextureRegion;
    }

    public static TextureRegion getGameBK() {
        return gameBK;
    }

    public static ITiledTextureRegion getBtnFire1() {
        return btnFire1;
    }
    public static ITiledTextureRegion getBtnFire2() {
        return btnFire2;
    }

    public static TiledTextureRegion getmBoomTextureRegion() {
        return mBoomTextureRegion;
    }

    public static TextureRegion getmParticleTextureRegion() {
        return mParticleTextureRegion;
    }
}
