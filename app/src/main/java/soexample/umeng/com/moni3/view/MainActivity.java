package soexample.umeng.com.moni3.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.umeng.com.moni3.R;
import soexample.umeng.com.moni3.mvp.presenter.BaseActivityPresenter;
import soexample.umeng.com.moni3.presenter.MainActivityPresenter;

public class MainActivity extends BaseActivityPresenter<MainActivityPresenter> {
    @BindView(R.id.vp_view)
    ViewPager vp_view;
    @BindView(R.id.tablayout)
    TabLayout tablayout;

    @Override
    public Class<MainActivityPresenter> getClassDeleGate() {
        return MainActivityPresenter.class;
    }

    @Override
    public void initWeight() {
        super.initWeight();
        ButterKnife.bind(this);
            deleGate.initView(vp_view,tablayout);
    }
}
