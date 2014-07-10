package donnu.zolotarev.SpaceShip.Scenes;

import android.opengl.GLES20;
import android.view.KeyEvent;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;

public class MainMenu extends Scene {
    private static final int MENU_RESET = 0;
    private static final int MENU_QUIT = MENU_RESET + 1;

    private final SpaceShipActivity activity;
    private final Engine engine;
    private MenuScene menuScene;

    public MainMenu() {
        activity = SpaceShipActivity.getInstance();
        engine = activity.getEngine();
        setBackground(new Background(0.9f, 0.9f, 0.9f));;
        createMenuScene();
        setChildScene(menuScene, false, true, true);
    }

    protected void createMenuScene() {
        this.menuScene = new MenuScene(activity.getCamera());

        final SpriteMenuItem resetMenuItem = new SpriteMenuItem(MENU_RESET, TextureLoader.getMenuResetTextureRegion(),
                engine.getVertexBufferObjectManager());
        resetMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        this.menuScene.addMenuItem(resetMenuItem);

        final SpriteMenuItem quitMenuItem = new SpriteMenuItem(MENU_QUIT, TextureLoader.getMenuQuitTextureRegion(),
                engine.getVertexBufferObjectManager());
        quitMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        this.menuScene.addMenuItem(quitMenuItem);

        this.menuScene.buildAnimations();

        this.menuScene.setBackgroundEnabled(false);

        this.menuScene.setOnMenuItemClickListener(new MenuScene.IOnMenuItemClickListener() {


            @Override
            public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX,
                    float pMenuItemLocalY) {
                switch (pMenuItem.getID()){
                    case MENU_RESET:
                    setChildScene(new MainScene());
                        break;
                    case MENU_QUIT:
                        //
                        break;
                }
                return false;
            }
        });
    }

    public void onKeyPressed(int keyCode, KeyEvent event) {
        /*if(hasChildScene()) {
				*//* Remove the menu and reset it. *//*
            this.menuScene.back();
        } else {
				*//* Attach the menu. *//*
            setChildScene(this.menuScene, false, true, true);
        }*/
    }
}
