package donnu.zolotarev.SpaceShip.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import donnu.zolotarev.SpaceShip.MenuActivity;

public class BaseFragment extends Fragment {

    protected final String tag;

    public BaseFragment() {
        tag = getClass().getSimpleName();
    }

    protected View inflateFragmentView(int layoutResource, LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(layoutResource, container, false);
        try {
            ButterKnife.inject(this, view);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    protected void toast(int messageId) {
        Activity activity = getActivity();
        if (activity != null) {
            Toast.makeText(activity, messageId, Toast.LENGTH_SHORT).show();
        }
    }

    protected void toast(String message) {
        Activity activity = getActivity();
        if (activity != null) {
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
        }
    }

    protected void toastInRun(final String message){
        if (getActivity()!=null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    toast(message);
                }
            });
        }
    }

    protected void toastInRun(final int message){
        if (getActivity()!=null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    toast(message);
                }
            });
        }
    }

    public static void showAlert(Context ctx, int messageId, String title){
        showAlert(ctx, ctx.getString(messageId), title);
    }

    public static void showAlert(Context ctx, String message, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        if(title == null){
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setTitle("Alert");
        } else {
            builder.setTitle(title);
        }
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        builder.show();
    }

    protected void logDebug(String message) {
        Log.d(tag, String.valueOf(message));
    }

    protected void logError(String message) {
        Log.e(tag, String.valueOf(message));
    }

    protected void back(){
        MenuActivity activity = getMainActivity();
        if(activity != null){
            activity.popBackStack();
        }
    }

    protected MenuActivity getMainActivity(){
        Activity activity = getActivity();
        if(activity != null && activity instanceof MenuActivity){
            return (MenuActivity) activity;
        }

        return null;
    }


    protected void showFragment(BaseFragment fragment, boolean addToBackStack) {
        Activity activity = getActivity();
        if (activity != null && activity instanceof MenuActivity) {
            MenuActivity mainActivity = (MenuActivity) activity;
            mainActivity.loadRootFragment(fragment, addToBackStack);
        } else {
            logError("Cannot get MainActivity reference... Look for error");
        }
    }

}
