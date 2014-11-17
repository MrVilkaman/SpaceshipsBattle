package donnu.zolotarev.SpaceShip.Utils;

import android.content.Context;
import android.opengl.GLES20;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.ISimpleClick;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.color.Color;

import java.util.HashMap;

public  class MenuFactory {
    private final Engine engine;
    private final MenuScene menuScene;
    private final Context context;
    private HashMap<Integer,ISimpleClick> clicks;
    private Integer clickCounter;
    private int lostIndex = -1;

    public static MenuFactory createMenu(Engine engine, Camera camera, Context context){

        return new MenuFactory(engine, camera, context);
    }

    private MenuFactory(Engine engine, Camera camera, Context context) {
        clickCounter = 0;
        this.menuScene = new MenuScene(camera);
        this.engine = engine;
        this.context = context;
        clicks = new HashMap<Integer, ISimpleClick>();
    }

    // Картинка с нажатием
    public MenuFactory addedItem(ITextureRegion texture, int textResurceId, IFont font, ISimpleClick simpleClick){
        reqFromClick(simpleClick);
        IMenuItem background = createSpriteItem(texture);
        IMenuItem text = createText(context.getString(textResurceId), font);
        text = alihment(text,texture.getWidth()/2,texture.getHeight()/2,WALIGMENT.CENTER,HALIGMENT.CENTER);

        background.attachChild(text);
        return addMenuItem(background);
    }

       // Текст без нажатиея
    public MenuFactory addedText(String text, IFont iFont){
        return addMenuItem(createText(text, iFont));
    }


    public MenuFactory addedText(String text, IFont iFont, float x, float y,WALIGMENT waligment, HALIGMENT haligment){
        return attachChild(createText(text, iFont,x,y,waligment,haligment));
    }

    private IMenuItem createSpriteItem(ITextureRegion texture){
        SpriteMenuItem resetMenuItem = new SpriteMenuItem(lostIndex, texture,
                engine.getVertexBufferObjectManager());
        resetMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        return resetMenuItem;
    }

    private IMenuItem createText(String text, IFont iFont){
        TextMenuItem textMenuItem = new TextMenuItem(lostIndex,iFont, text,engine.getVertexBufferObjectManager());
        textMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        textMenuItem.setColor(Color.WHITE);
        return textMenuItem;
    }

    private IMenuItem createText(String text, IFont iFont,float x, float y,WALIGMENT waligment, HALIGMENT haligment){
        IMenuItem textMenuItem = createText(text, iFont);
        return alihment(textMenuItem,x,y,waligment,haligment);
    }

    private MenuFactory addMenuItem(IMenuItem entity){
        menuScene.addMenuItem(entity);
        return this;
    }

    private MenuFactory attachChild(IMenuItem entity){
        menuScene.registerTouchArea(entity);
        menuScene.attachChild(entity);
        return this;
    }

    public MenuFactory enableAnimation(){
        this.menuScene.buildAnimations();
        this.menuScene.setBackgroundEnabled(false);
        return this;
    }

    private void reqFromClick(ISimpleClick simpleClick){
        clicks.put(clickCounter,simpleClick);
        lostIndex = clickCounter++;
    }

    public MenuScene build(){
        menuScene.setOnMenuItemClickListener(new MenuScene.IOnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX,
                    float pMenuItemLocalY) {
                ISimpleClick d = clicks.get(pMenuItem.getID());
                if (d != null){
                    d.onClick(pMenuItem.getID());
                    return true;
                }
                return false;
            }
        });
        return menuScene;
    }

    private IMenuItem alihment(IMenuItem iMenuItem,float x, float y,WALIGMENT waligment, HALIGMENT haligment){
        int dx = 0;
        int dy = 0;
        switch (waligment){
            case LEFT:
                dx = 0;
                break;
            case CENTER:
                dx = (int)iMenuItem.getWidth()/2;
                break;
            case RIGHT:
                dx = (int)iMenuItem.getWidth();
                break;
        }

        switch (haligment){
            case TOP:
                dy = 0;
                break;
            case CENTER:
                dy = (int)iMenuItem.getHeight()/2;
                break;
            case BOTTOM:
                dy = (int)iMenuItem.getHeight();
                break;
        }
        iMenuItem.setPosition(x - dx,y - dy);
        return iMenuItem;
    }

}
