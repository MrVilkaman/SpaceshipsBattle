package donnu.zolotarev.SpaceShip.Scenes;

import android.view.KeyEvent;
import donnu.zolotarev.SpaceShip.Activity.GameActivity;
import donnu.zolotarev.SpaceShip.GameData.HeroFeatures;
import donnu.zolotarev.SpaceShip.GameData.ShopData;
import donnu.zolotarev.SpaceShip.GameData.UserDataProcessor;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.ISimpleClick;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Utils.Constants;
import donnu.zolotarev.SpaceShip.Utils.HALIGMENT;
import donnu.zolotarev.SpaceShip.Utils.MenuFactory;
import donnu.zolotarev.SpaceShip.Utils.WALIGMENT;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.text.Text;

public class ShopScene extends MyScene {
    public final static int CALLBACK_UPDATE_MONEY = 0;

    private final Scene sceneMain;
    private final Scene entity;
    private final IParentScene parentScene;
    private MenuScene scene;
    private ISimpleClick lAddHealth;
    private Text healthMaxBar;
    private GameActivity activity;

    private ShopData shopData;
    private UserDataProcessor processor;
    private HeroFeatures heroFeatures;
    private ISimpleClick lBuyDoubleGun;
    private ISimpleClick lAddDamage;
    private ISimpleClick lBuyRocketGun;

    public ShopScene(Scene entity,IParentScene parentScene) {
        super(parentScene);
        this.parentScene = parentScene;
        this.entity = entity;
        activity =  GameActivity.getInstance();
        sceneMain = new Scene();
        createUI();

    }

    private void updateUI() {
        MenuFactory menuFactory =  MenuFactory.createMenu(activity.getEngine(), activity.getCamera())
                .addedText("Магазин", TextureLoader.getFont(), Constants.CAMERA_WIDTH_HALF, 50, WALIGMENT.CENTER,
                        HALIGMENT.CENTER)
                .addedText("Броня: ", TextureLoader.getFont(), Constants.CAMERA_WIDTH /10, 80, WALIGMENT.LEFT,
                        HALIGMENT.TOP)
                .addedText("$"+shopData.getPriceMaxHealth(), TextureLoader.getFontBig(),lAddHealth, (Constants.CAMERA_WIDTH*3)/4, 100, WALIGMENT.LEFT,
                        HALIGMENT.CENTER)
                .addedText("Доп. урон: "+ shopData.getLevelBulletDamege()+" уровень", TextureLoader.getFont(), Constants.CAMERA_WIDTH/10, 170, WALIGMENT.LEFT,
                        HALIGMENT.TOP)
                .addedText("$"+shopData.getPriceBulletDamege(), TextureLoader.getFontBig(),lAddDamage, (Constants.CAMERA_WIDTH*3)/4, 190, WALIGMENT.LEFT,
                        HALIGMENT.CENTER)
                .enableAnimation();
        // todo


        String text ="";
        String text2 = "";
        if (!shopData.isHaveDoubleGun()){
            text = "$"+shopData.getPriceDoubleGun();
            text2 = "Спаренные пушки";
        }else {
            text = "$"+shopData.getPriceDoubleAmmo();
            text2 = "Патроны для спаренной пушки: (всего " +shopData.getDoubleGunCount()+")";
        }

        menuFactory.addedText(text2, TextureLoader.getFont(), Constants.CAMERA_WIDTH/10, 250, WALIGMENT.LEFT,
                HALIGMENT.TOP);

        menuFactory.addedText(text, TextureLoader.getFontBig(),lBuyDoubleGun, (Constants.CAMERA_WIDTH*3)/4, 270, WALIGMENT.LEFT,
                HALIGMENT.CENTER);


        ///
        text2 = "";
        if (!shopData.isHaveRocketGun()){
            text = "$"+shopData.getPriceRocketGun();
            text2 = "Ракетная установка";
        }else {
            text = "$"+shopData.getPriceRocketAmmo();
            text2 = "Ракеты: "+ "(всего " +shopData.getRocketCount()+ ")";
        }

        menuFactory.addedText(text2, TextureLoader.getFont(), Constants.CAMERA_WIDTH/10, 340, WALIGMENT.LEFT,
                HALIGMENT.TOP);

        menuFactory.addedText(text, TextureLoader.getFontBig(),lBuyRocketGun, (Constants.CAMERA_WIDTH*3)/4, 360, WALIGMENT.LEFT,
                HALIGMENT.CENTER);

        scene =  menuFactory.build();
        parentScene.callback(CALLBACK_UPDATE_MONEY);
        show();
    }

    private void createUI() {
        heroFeatures =  HeroFeatures.get();
        shopData = ShopData.get();
        processor = UserDataProcessor.get();
        lAddHealth = new ISimpleClick() {
            @Override
            public void onClick(int id) {
              /*  if (processor.buy(shopData.getPriceMaxHealth())){
                    int h = heroFeatures.getMaxHealth()+shopData.getEffectMaxHealth();
                    heroFeatures.setMaxHealth(h);
                    healthMaxBar.setText(String.valueOf(h));
                    shopData.nextLevelMaxHealth();
                    updateUI();
                }else{
                 toast("Мало денег(");
                }*/
            }
        };
        lAddDamage  = new ISimpleClick() {
            @Override
            public void onClick(int id) {
              /*  if (processor.buy(shopData.getPriceBulletDamege())){
                    shopData.nextLevelBulletDamege();
                    updateUI();
                }else{
                    toast("Мало денег(");
                }*/
            }
        };

        lBuyDoubleGun = new ISimpleClick() {
            @Override
            public void onClick(int id) {
               /* if (!shopData.isHaveDoubleGun()){
                    if (processor.buy(shopData.getPriceDoubleGun())){
                        shopData.buyDoubleGun();
                        updateUI();
                        toast("Теперь ты обладатель крутой пушки!");
                    }else{
                        toast("Мало денег(");
                    }
                } else {
                    if (processor.buy(shopData.getPriceDoubleAmmo())){
                        shopData.buyDoubleGunAmmo();
                        updateUI();
                    }else{
                        toast("Мало денег(");
                    }
                }*/
            }
        };

        lBuyRocketGun = new ISimpleClick() {
            @Override
            public void onClick(int id) {
               /* if (!shopData.isHaveRocketGun()){
                    if (processor.buy(shopData.getPriceRocketGun())){
                        shopData.buyRocketGun();
                        updateUI();
                        toast("Теперь ты обладатель крутой пушки!");
                    }else{
                        toast("Мало денег(");
                    }
                }else{
                    if (processor.buy(shopData.getPriceRocketAmmo())){
                        shopData.buyRocketAmmo();
                        updateUI();
                    }else{
                        toast("Мало денег(");
                    }
                }*/
            }
        };
        updateUI();
        sceneMain.setBackgroundEnabled(false);
        entity.setChildScene(sceneMain);
        createHealthBar();
        show();
    }

    //todo переделать
    private void createHealthBar(){
        try {
            int y = 80;
            int x = Constants.CAMERA_WIDTH /10 +100;
            healthMaxBar = new Text(x,y,TextureLoader.getFont(),"0000",activity.getEngine().getVertexBufferObjectManager());
            healthMaxBar.setPosition(x,y);
            entity.attachChild(healthMaxBar);
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
        entity.detachChild(healthMaxBar);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }
}
