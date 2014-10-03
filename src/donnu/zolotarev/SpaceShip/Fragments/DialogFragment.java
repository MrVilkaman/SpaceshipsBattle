package donnu.zolotarev.SpaceShip.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import donnu.zolotarev.SpaceShip.R;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.ISimpleClick2;

public class DialogFragment extends android.app.DialogFragment {

    @InjectView(R.id.text)
    TextView textView;

    String text = "";
    private ISimpleClick2 onOk;
    private ISimpleClick2 onCancel;

    @InjectView(R.id.ok)
    Button buttonOK;

    @InjectView(R.id.cansel)
    Button buttonCancel;

    @InjectView(R.id.linearLayout)
    LinearLayout linearLayout;

    private int viewResId;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater  = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
        View view = null;
            view = inflateFragmentView(R.layout.fragment_dialog, inflater, null);
        if (viewResId != 0){
            View view2 =  inflater.inflate(viewResId,null, true);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
            );
            lp.setMargins(10,5,10,5);
            view2.setLayoutParams(lp);
            linearLayout.addView(view2);
        }

        if (onCancel !=null){
            buttonCancel.setVisibility(View.VISIBLE);
        }

        if (onOk !=null){
            buttonOK.setVisibility(View.VISIBLE);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        textView.setText(text);
        builder.setView(view);
        return builder.create();
    }

    public void setText(String s){
        text = s;
    }

    public void setViewResId(int viewResId) {
        this.viewResId = viewResId;
    }

    public void setOkListener(ISimpleClick2 listener){
       onOk = listener;
    //    buttonOK.setVisibility(View.VISIBLE);
    }

    public void setCancelListener(ISimpleClick2 listener){
        onCancel = listener;
    }

    @OnClick(R.id.ok)
    void clickOk(){
        onOk.onClick();
        dismiss();
    }

    @OnClick(R.id.cansel)
    void clickCancel(){
        onCancel.onClick();
        dismiss();
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
}
