package soexample.umeng.com.moni3.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.SearchView;

import soexample.umeng.com.moni3.R;
import soexample.umeng.com.moni3.mvp.view.AppDeleGate;
import soexample.umeng.com.moni3.view.ShowActivity;

public class LeftFragmentPresenter extends AppDeleGate {
    private Context context;

    @Override
    public int getLayoutId() {
        return R.layout.left_fragment;
    }

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }


    public void initClick() {
        Intent intent = new Intent(context, ShowActivity.class);
        context.startActivity(intent);

    }
}
