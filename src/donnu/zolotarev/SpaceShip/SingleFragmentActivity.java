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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());

        FragmentManager fm = getFragmentManager();
        Fragment crimeFragment = fm.findFragmentById(R.id.fragmentContainer);


        if (crimeFragment == null){
            crimeFragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, crimeFragment)
                    .commit();
        }
    }


}
