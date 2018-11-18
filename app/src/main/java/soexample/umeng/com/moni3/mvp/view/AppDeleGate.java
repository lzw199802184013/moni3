package soexample.umeng.com.moni3.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public abstract class AppDeleGate implements DeleGate {
    private View rootView;

    @Override
    public void initData() {

    }

    @Override
    public void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        rootView = inflater.inflate(getLayoutId(), viewGroup, false);
    }

    @Override
    public View getrootView() {
        return rootView;
    }

    private Context context;

    public void toast(Context context, String msg) {

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    public abstract int getLayoutId();
}
