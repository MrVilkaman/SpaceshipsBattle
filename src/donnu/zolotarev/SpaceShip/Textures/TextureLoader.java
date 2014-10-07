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
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.color.Color;

public class TextureLoader {

    private static TiledTextureRegion ship;
    private static TextureRegion screenControlKnobTextureRegion;
    private static TextureRegion screenControlBaseTextureRegion;
    private static TiledTextureRegion enemyShipLightBlue;
    private static TiledTextureRegion enemyShipOrange;
    private static TiledTextureRegion enemyShipGreen;

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

    private static TiledTextureRegion rocketAmmoTextureRegion;
    private static TiledTextureRegion simpleBulletTextureRegion;
    private static TiledTextureRegion mMeteorite1TextureRegion;
    private static TextureRegion gameBK;
    private static TiledTextureRegion btnFire1;
    private static TiledTextureRegion btnFire2;
    private static TiledTextureRegion mBoomTextureRegion;
    private static TextureRegion mParticleTextureRegion;

    public static void loadTexture(Context context, Engine engine) {
        TextureManager tm = engine.getTextureManager();
        FontManager fm = engine.getFontManager();
        BuildableBitmapTextureAtlas mTexture = new BuildableBitmapTextureAtlas(tm, 1024, 768, TextureOptions.NEAREST);
        ship = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mTexture, context, "ship.png", 1, 1);
        enemyShipLightBlue = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mTexture, context,
                "alienblaster.png", 1, 1);
        enemyShipOrange = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mTexture, context,
                "alienblaster_2.png", 1, 1);
        enemyShipGreen = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mTexture, context,
                "alienblaster_3.png", 1, 1);
        try {
            mTexture.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            e.printStackTrace();
        }
        tm.loadTexture(mTexture);

        BitmapTextureAtlas mOnScreenControlTexture = new BitmapTextureAtlas(tm, 256, 128,
                TextureOptions.BILINEAR);
        screenControlBaseTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                mOnScreenControlTexture, context, "onscreen_control_base.png", 0, 0);
        screenControlKnobTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                mOnScreenControlTexture, context, "onscreen_control_knob.png", 130, 0);
        mOnScreenControlTexture.load();


        BuildableBitmapTextureAtlas bulletTexture = new BuildableBitmapTextureAtlas(tm, 128, 128,
                TextureOptions.NEAREST);
        simpleBulletTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bulletTexture, context,
                "simpleBullet.png", 1, 1);
        rocketAmmoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bulletTexture, context,
                "roket_ammo.png", 1, 1);
        try {
            bulletTexture.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            e.printStackTrace();
        }
        bulletTexture.load();
       // Шрифты
        font = FontFactory.create(fm, tm, 256, 256, Typeface.create(
                Typeface.DEFAULT, Typeface.BOLD), 32, Color.WHITE_ABGR_PACKED_INT);
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



        //Кнопки
        BuildableBitmapTextureAtlas buttonTexture = new BuildableBitmapTextureAtlas(tm, 256, 128, TextureOptions.NEAREST);
        btnFire1 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(buttonTexture, context, "btn_fire_1.png", 1, 1);
        btnFire2 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(buttonTexture, context, "btn_fire_2.png", 1, 1);

        try {
            buttonTexture.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            e.printStackTrace();
        }
        buttonTexture.load();


        // Меню выбора уровня
        BitmapTextureAtlas loadSceneTexture = new BitmapTextureAtlas(tm, 512, 256, TextureOptions.BILINEAR);
        changeLevelLableTextureRegion1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(loadSceneTexture, context
                , "text/change_level_Lable_1.png", 0, 0);
        changeLevelIconShop = BitmapTextureAtlasTextureRegionFactory.createFromAsset(loadSceneTexture, context
                , "Level_Shop.png", 401, 0);

        loadSceneTexture.load();

        BitmapTextureAtlas gameBKTexture = new BitmapTextureAtlas(tm, 2048, 1024, TextureOptions.BILINEAR);
        gameBK = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBKTexture, context
                , "bk_game.jpg", 0, 0);

        gameBKTexture.load();


        BuildableBitmapTextureAtlas mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(tm, 1024, 512,
                TextureOptions.NEAREST);
        mMeteorite1TextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mBitmapTextureAtlas, context, "Meteorite1.png", 5, 1);
        mBoomTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mBitmapTextureAtlas, context, "boom.png", 5, 2);
        try {
            mBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            e.printStackTrace();
        }
        mBitmapTextureAtlas.load();

        //
        BitmapTextureAtlas mAtlas = new BitmapTextureAtlas(tm,64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mParticleTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mAtlas, context, "f4.png", 0, 0);
        mAtlas.load();

    }

    public static TiledTextureRegion getShip() {
        return ship;
    }

    public static TextureRegion getScreenControlBaseTextureRegion() {
        return screenControlBaseTextureRegion;
    }

    public static TextureRegion getScreenControlKnobTextureRegion() {
        return screenControlKnobTextureRegion;
    }

    public static TiledTextureRegion getSimpleBulletTextureRegion() {
        return simpleBulletTextureRegion;
    }

    public static TiledTextureRegion getEnemyShipLightBlue() {
        return enemyShipLightBlue;
    }

    public static TiledTextureRegion getEnemyShipOrange() {
        return enemyShipOrange;
    }

    public static TiledTextureRegion getEnemyShipGreen() {
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

    public static TiledTextureRegion getRocketAmmoTextureRegion() {
        return rocketAmmoTextureRegion;
    }

    public static TiledTextureRegion getmMeteorite1TextureRegion() {
        return mMeteorite1TextureRegion;
    }

    public static TextureRegion getGameBK() {
        return gameBK;
    }

    public static TiledTextureRegion getBtnFire1() {
        return btnFire1;
    }
    public static TiledTextureRegion getBtnFire2() {
        return btnFire2;
    }

    public static TiledTextureRegion getmBoomTextureRegion() {
        return mBoomTextureRegion;
    }

    public static TextureRegion getmParticleTextureRegion() {
        return mParticleTextureRegion;
    }
}
