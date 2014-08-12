package donnu.zolotarev.SpaceShip.Scenes;

import android.view.KeyEvent;
import donnu.zolotarev.SpaceShip.GameData.HeroFeatures;
import donnu.zolotarev.SpaceShip.GameData.ShopData;
import donnu.zolotarev.SpaceShip.GameData.UserDataProcessor;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.ISimpleClick;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Utils.Constants;
import donnu.zolotarev.SpaceShip.Utils.HALIGMENT;
import donnu.zolotarev.SpaceShip.Utils.MenuFactory;
import donnu.zolotarev.SpaceShip.Utils.WALIGMENT;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.text.Text;

public class ShopScene extends MyScene {
    private final Scene sceneMain;
    private final Scene entity;
    private final IParentScene parentScene;
    private MenuScene scene;
    private ISimpleClick lAddHealth;
    private Text healthMaxBar;
    private SpaceShipActivity activity;

    public ShopScene(Scene entity,IParentScene parentScene) {
        super(parentScene);
        this.parentScene = parentScene;
        this.entity = entity;
        sceneMain = new Scene();
        createUI();
    }

    private void createUI() {
        lAddHealth = new ISimpleClick() {
            @Override
            public void onClick(int id) {
                HeroFeatures heroFeatures =  HeroFeatures.get();
                ShopData shopData = ShopData.get();
                // TODO отнимать деньги
                UserDataProcessor processor = UserDataProcessor.get();
                if (processor.buy(shopData.getPriceMaxHealth())){
                    int h = heroFeatures.getMaxHealth()+shopData.getEffectMaxHealth();
                    heroFeatures.setMaxHealth(h);
                    healthMaxBar.setText(String.valueOf(h));
                    shopData.nextLevelMaxHealth();
                }else{
                 toast("Мало денег(");
                }
            }
        };

        activity =  SpaceShipActivity.getInstance();
        scene = MenuFactory.createMenu(activity.getEngine(), activity.getCamera())
                .addedText("Магазин", TextureLoader.getFont(), Constants.CAMERA_WIDTH_HALF, 50, WALIGMENT.CENTER,
                        HALIGMENT.CENTER)
                .addedText("Броня: ", TextureLoader.getFont(), Constants.CAMERA_WIDTH/4, 100, WALIGMENT.LEFT,
                        HALIGMENT.TOP)
                .addedText("+", TextureLoader.getFontBig(),lAddHealth, (Constants.CAMERA_WIDTH*3)/4, 100, WALIGMENT.LEFT,
                        HALIGMENT.CENTER)
                .enableAnimation()
                .build();


        sceneMain.setBackgroundEnabled(false);
        createGoldBar();
        entity.setChildScene(sceneMain);
        show();
    }

    //todo переделать
    private void createGoldBar(){
        try {
            int y = 100;
            int x = Constants.CAMERA_WIDTH/4+100;
            healthMaxBar = new Text(x,y,TextureLoader.getFont(),"0000",activity.getEngine().getVertexBufferObjectManager());
            healthMaxBar.setPosition(x,y);
            scene.attachChild(healthMaxBar);
            healthMaxBar.setText(String.valueOf(HeroFeatures.get().getMaxHealth()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void show(){
        sceneMain.setChildScene(scene,false,true,true);
    }

    public void hide(){
        sceneMain.back();

    }

    @Override
    public void onKeyPressed(int keyCode, KeyEvent event) {
       // hide();
        parentScene.returnToParentScene(IParentScene.EXIT_SHOP);
    }
}
