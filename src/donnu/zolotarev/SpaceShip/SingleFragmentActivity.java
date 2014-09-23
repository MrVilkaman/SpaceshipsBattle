package donnu.zolotarev.SpaceShip;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

public abstract class SingleFragmentActivity extends Activity {
    protected abstract Fragment createFragment();

    protected int getLayoutResID(){
        return R.layout.activity_fragment;
    }

    protected int getViewPlaceID(){
        return R.id.fragmentContainer;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());

        FragmentManager fm = getFragmentManager();
        Fragment crimeFragment = fm.findFragmentById(getViewPlaceID());


        if (crimeFragment == null){
            crimeFragment = createFragment();
            fm.beginTransaction()
                    .add(getViewPlaceID(), crimeFragment)
                    .commit();
        }
    }


}
