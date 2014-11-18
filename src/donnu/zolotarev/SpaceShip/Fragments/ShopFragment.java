package donnu.zolotarev.SpaceShip.Fragments;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import com.google.android.gms.ads.AdView;
import donnu.zolotarev.SpaceShip.Fragments.Adapter.ShopAdapter;
import donnu.zolotarev.SpaceShip.GameData.HeroFeatures;
import donnu.zolotarev.SpaceShip.GameData.Shop;
import donnu.zolotarev.SpaceShip.GameData.UserData;
import donnu.zolotarev.SpaceShip.R;
import donnu.zolotarev.SpaceShip.Utils.Constants;
import donnu.zolotarev.SpaceShip.Utils.GlobalImageManager;

public class ShopFragment extends BaseMenuFragment {

    @InjectView(R.id.select_levels_money_val)
    TextView gold;
    private UserData userData;

    @InjectView(R.id.shop_list_view)
    ListView listView;
    private ShopAdapter shopAdapter;

    @InjectView(R.id.image_background)
    ImageView imageBack;

    @InjectView(R.id.adView)
    AdView adView;

    @InjectView(R.id.use_double_gun)
    CheckBox cbUseGun;

    @InjectView(R.id.use_double_shield)
    CheckBox cbUseShield;

    private ShopAdapter.Callback moneyUpdateCallback = new ShopAdapter.Callback() {
        @Override
        public void updateMoney() {
            gold.setText(String.valueOf(userData.getMoney()));
        }
    };
    private HeroFeatures heroFeatures;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shopAdapter = new ShopAdapter(getActivity(), Shop.get(),moneyUpdateCallback);
        userData = UserData.get();
        heroFeatures =  HeroFeatures.get();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflateFragmentView(R.layout.fragment_shop_menu,inflater,container);
        listView.setAdapter(shopAdapter);
        if (Constants.IS_ADS_ENABLED ){
            showAds(adView);
        }
        shopAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                userDataChechBox();
            }

            @Override
            public void onInvalidated() {
                userDataChechBox();
            }
        });
       userDataChechBox();

        cbUseGun.setChecked(heroFeatures.isNeedUseDoubleAmmo());
        cbUseShield.setChecked(heroFeatures.isNeedUseShield());

        return view;
    }

    private void userDataChechBox() {
        cbUseGun.setEnabled(heroFeatures.isHaveDoubleGun());
        cbUseShield.setEnabled(heroFeatures.isHaveShield());
    }

    @OnClick(R.id.select_levels_back)
    public void onBack(){
        back();
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
        heroFeatures.setNeedUseDoubleAmmo(cbUseGun.isChecked());
        heroFeatures.setNeedUseShield(cbUseShield.isChecked());
    }

        @Override
    public void onResume() {
        super.onResume();
            adView.resume();
            gold.setText(String.valueOf(userData.getMoney()));
    }

    @Override
    public void onPause() {
        super.onPause();
        saveGameState();
        adView.pause();
    }

}
