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
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.color.Color;

public class TextureLoader {

    private static TextureRegion ship;
    private static TextureRegion screenControlKnobTextureRegion;
    private static TextureRegion screenControlBaseTextureRegion;
    private static TextureRegion enemyShipLightBlue;
    private static TextureRegion enemyShipOrange;
    private static TextureRegion enemyShipGreen;

    private static Font font;
    private static Font fontBig;

    private static TextureRegion menuResumeTextureRegion;
    private static TextureRegion menuExitTextureRegion;
    private static TextureRegion menuNewGameTextureRegion;
    private static TextureRegion menuBackToMainMenuTextureRegion;
    private static TextureRegion menuRestartTextureRegion;
    private static TextureRegion menuAboutTextureRegion;

    private static TextureRegion changeLevelLableTextureRegion1;
    private static TextureRegion changeLevelIconShop;

    private static TextureRegion rocketAmmoTextureRegion;
    private static TextureRegion simpleBulletTextureRegion;
    private static TiledTextureRegion mMeteorite1TextureRegion;

    public static void loadTexture(Context context, Engine engine) {
        TextureManager tm = engine.getTextureManager();
        FontManager fm = engine.getFontManager();
        BitmapTextureAtlas mTexture = new BitmapTextureAtlas(tm, 1024, 768, TextureOptions.NEAREST_PREMULTIPLYALPHA);
        ship = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mTexture, context, "ship.png", 0, 0);
        enemyShipLightBlue = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mTexture, context, "alienblaster.png", 0,225);
        enemyShipOrange = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mTexture, context, "alienblaster_2.png", 0,300);
        enemyShipGreen = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mTexture, context, "alienblaster_3.png", 0,375);
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
        simpleBulletTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bulletTexture, context,
                "simpleBullet.png", 0, 0);
        rocketAmmoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bulletTexture, context,
                "roket_ammo.png", 6, 0);
        bulletTexture.load();
       // Шрифты
        font = FontFactory.create(fm, tm, 256, 256, Typeface.create(
                Typeface.DEFAULT, Typeface.BOLD), 32);
        font.load();
        fontBig = FontFactory.create(fm, tm, 512, 256, Typeface.create(
                Typeface.DEFAULT, Typeface.BOLD), 72, Color.WHITE_ABGR_PACKED_INT);
        fontBig.load();
        // Меню

        BitmapTextureAtlas menuTexture = new BitmapTextureAtlas(tm, 512, 512, TextureOptions.BILINEAR);
        menuResumeTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTexture, context
                , "menu_resume.png", 0, 0);
        menuExitTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTexture, context
                , "menu_exit.png", 0, 51);
        menuNewGameTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTexture, context
                , "menu_new_game.png", 0, 102);
        menuBackToMainMenuTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTexture, context
                , "menu_to_mainmenu.png", 0, 153);
        menuRestartTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTexture, context
                , "menu_restart.png", 0, 204);
        menuAboutTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTexture, context
                , "menu_about.png", 0, 255);
        menuTexture.load();

        // Меню выбора уровня
        BitmapTextureAtlas loadSceneTexture = new BitmapTextureAtlas(tm, 512, 256, TextureOptions.BILINEAR);
        changeLevelLableTextureRegion1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(loadSceneTexture, context
                , "text/change_level_Lable_1.png", 0, 0);
        changeLevelIconShop = BitmapTextureAtlasTextureRegionFactory.createFromAsset(loadSceneTexture, context
                , "Level_Shop.png", 401, 0);
        loadSceneTexture.load();


        BuildableBitmapTextureAtlas mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(tm, 512, 256,
                TextureOptions.NEAREST);

        mMeteorite1TextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mBitmapTextureAtlas, context, "Meteorite1.png", 5, 1);
        mBitmapTextureAtlas.load();

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

    public static TextureRegion getEnemyShipLightBlue() {
        return enemyShipLightBlue;
    }

    public static TextureRegion getEnemyShipOrange() {
        return enemyShipOrange;
    }

    public static TextureRegion getEnemyShipGreen() {
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

    public static TextureRegion getMenuNewGameTextureRegion() {
        return menuNewGameTextureRegion;
    }

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

    public static TextureRegion getRocketAmmoTextureRegion() {
        return rocketAmmoTextureRegion;
    }

    public static TiledTextureRegion getmMeteorite1TextureRegion() {
        return mMeteorite1TextureRegion;
    }
}
