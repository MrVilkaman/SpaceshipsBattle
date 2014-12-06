package donnu.zolotarev.SpaceShip.Activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.purplebrain.adbuddiz.sdk.AdBuddiz;
import com.purplebrain.adbuddiz.sdk.AdBuddizDelegate;
import com.purplebrain.adbuddiz.sdk.AdBuddizError;
import donnu.zolotarev.SpaceShip.Fragments.DialogFragment;
import donnu.zolotarev.SpaceShip.Fragments.MainMenuFragment;
import donnu.zolotarev.SpaceShip.GameData.Settings;
import donnu.zolotarev.SpaceShip.R;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.ISimpleClick2;
import donnu.zolotarev.SpaceShip.Utils.Constants;
import donnu.zolotarev.SpaceShip.Utils.GlobalImageManager;
import donnu.zolotarev.SpaceShip.Utils.SoundHelper;
import donnu.zolotarev.SpaceShip.Utils.VibroHelper;

public class MenuActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new MainMenuFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleAnalytics.getInstance(this).enableAutoActivityReports(getApplication());

        try {
            GoogleAnalytics.getInstance(this).dispatchLocalHits();
        } catch (Exception e) {
        }

        AdBuddiz.setPublisherKey(Constants.ADBUDDIZ_PUBLISHER_KEY);
        AdBuddiz.cacheAds(this);
        final Activity activity = this;
        AdBuddiz.setDelegate(new AdBuddizDelegate() {
            @Override
            public void didCacheAd() {

            }

            @Override
            public void didShowAd() {

            }

            @Override
            public void didFailToShowAd(AdBuddizError adBuddizError) {
                if (adBuddizError == AdBuddizError.NO_MORE_AVAILABLE_ADS || adBuddizError == AdBuddizError.NO_MORE_AVAILABLE_CACHED_ADS){

                    final InterstitialAd interstitial = new InterstitialAd(activity);
                    interstitial.setAdUnitId(Constants.BANNER2_ID);
                    AdRequest adRequest = new AdRequest.Builder().build();

                    interstitial.loadAd(adRequest);
                    interstitial.setAdListener(new AdListener() {
                        @Override
                        public void onAdLoaded() {
                            super.onAdLoaded();
                            if (interstitial.isLoaded()  ){
                                try {
                                    interstitial.show();
                                } catch (Exception e) {
                                }
                            }
                        }
                    });
                }
            }

            @Override
            public void didClick() {

            }

            @Override
            public void didHideAd() {

            }
        });

        VibroHelper.launch(this);
    }

    public void loadRootFragment(Fragment fragment, boolean addToBackStack){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (addToBackStack){
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        fragmentTransaction.replace(getViewPlaceID(), fragment).commit();
    }

    @Override
    public void onBackPressed() {
        int backStackEntries = getFragmentManager().getBackStackEntryCount();
        if(backStackEntries == 0){
            showExitDialog();
        } else {
            MenuActivity.super.onBackPressed();
        }
    }

    public void popBackStack(){
        getFragmentManager().popBackStack();
    }

    public void showExitDialog() {
        // Диалог выхода
        final Activity self = this;
        DialogFragment fragment = new DialogFragment();
        fragment.show(getFragmentManager(), "1");
        fragment.setTitle(getString(R.string.dialog_text_exit_message));
        fragment.setOkListener(new ISimpleClick2() {
            @Override
            public void onClick() {
                GlobalImageManager.stop();
                popBackStack();
                MenuActivity.super.onBackPressed();
                //android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        fragment.setCancelListener(new ISimpleClick2() {
            @Override
            public void onClick() {

            }
        });

    }

   @Override
    protected void onStop() {
        super.onStop();
        SoundHelper.pause();
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Settings settings = Settings.get();
        if (settings != null && settings.isMusic()){
            SoundHelper.play();
        }
        if (Constants.NEED_GOOGLE_ANALISTIC_TRACING){
            try {
                Tracker tracker = GoogleAnalytics.getInstance(this).newTracker(Constants.ANALISTYC_TRACER_ID);
                tracker.setScreenName("MenuActivity");
                tracker.send(new HitBuilders.AppViewBuilder().build());
            } catch (Exception e) {
            }
            GoogleAnalytics.getInstance(this).reportActivityStart(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GlobalImageManager.clear();
        VibroHelper.clear();
    }
}
