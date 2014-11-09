package donnu.zolotarev.SpaceShip.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import com.google.android.gms.ads.AdView;
import donnu.zolotarev.SpaceShip.Activity.GameActivity;
import donnu.zolotarev.SpaceShip.Fragments.Adapter.LevelsAdapter;
import donnu.zolotarev.SpaceShip.GameData.Settings;
import donnu.zolotarev.SpaceShip.GameData.UserData;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.Levels.WaveContainer;
import donnu.zolotarev.SpaceShip.R;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.ISimpleClick;
import donnu.zolotarev.SpaceShip.UI.HorizontalListView;
import donnu.zolotarev.SpaceShip.Utils.Constants;
import donnu.zolotarev.SpaceShip.Utils.GlobalImageManager;

public class SelectLevelFragment extends BaseMenuFragment {

    private static final int GAME_STOP = 0;
    private ShopFragment shopFragment;

    @InjectView(R.id.select_levels_money_val)
    TextView gold;

    @InjectView(R.id.select_levels_list_view)
    HorizontalListView listView;

    @InjectView(R.id.image_background)
    ImageView imageBack;

    @InjectView(R.id.adView)
    AdView adView;

    private LevelsAdapter levelsAdapter;
    private Intent restartIntent;
    private UserData userData;
    private Settings settings;

    private ISimpleClick startLevelListenet = new ISimpleClick() {
        @Override
        public void onClick(int id) {
            restartIntent = GameActivity.createIntent(getActivity(), id);
            startActivityForResult(restartIntent, GAME_STOP);
            GlobalImageManager.stop();
            //dirty hack
            settings.setLastPlayedLevel(id - 2);
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        levelsAdapter = new LevelsAdapter(getActivity());
        levelsAdapter.setClickListener(startLevelListenet);

        loadGame();
        userData = UserData.get();
        settings = Settings.get();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateFragmentView(R.layout.fragment_select_levels,inflater,container);
        listView.setAdapter(levelsAdapter);
        if (Constants.IS_ADS_ENABLED ){
            showAds(adView);
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        GlobalImageManager.changeImageView(getActivity(), imageBack);
    }

    @Override
    public void onStop() {
        super.onStop();
        GlobalImageManager.clearImageView(imageBack);
    }

    @Override
    public void onResume() {
       super.onResume();
       gold.setText(String.valueOf(userData.getMoney()));
        saveGameState();
        listView.setSelection(settings.getLastPlayedLevel());
        adView.resume();
    }

    @Override
    public void onPause() {
        adView.pause();
        super.onPause();
    }

    @OnClick(R.id.select_levels_test_level)
    void onTest(){
        restartIntent = GameActivity.createIntent(getActivity(), WaveContainer.LEVEL_TEST);
        startActivityForResult(restartIntent, GAME_STOP);
    }

    @OnClick(R.id.select_levels_back)
    public void onBack(){
       back();
    }

    @OnClick(R.id.select_levels_shop)
    public void onShop(){
        if (shopFragment == null){
            shopFragment = new ShopFragment();
        }
        showFragment(shopFragment,true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case GAME_STOP:
                if (resultCode == Activity.RESULT_OK){
                   int code =  data.getIntExtra(IParentScene.STATUS_CODE,-99);
                   switch (code){
                       case IParentScene.EXIT_RESTART:
                           if (restartIntent != null){
                               startActivityForResult(restartIntent,GAME_STOP);
                           }
                       case -99:
                         //  GlobalImageManager.changeImageView(imageBack);
                           if (Constants.IS_ADS_ENABLED){
                               loadBigBanner();
                           }
                           break;
                   }
                    levelsAdapter.notifyDataSetInvalidated();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
