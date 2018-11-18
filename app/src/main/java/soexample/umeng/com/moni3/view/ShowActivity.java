package soexample.umeng.com.moni3.view;


import android.support.v7.widget.SearchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.moni3.R;
import soexample.umeng.com.moni3.mvp.presenter.BaseActivityPresenter;
import soexample.umeng.com.moni3.presenter.ShowActivityPresenter;

public class ShowActivity extends BaseActivityPresenter<ShowActivityPresenter> {
    @BindView(R.id.sv)
    SearchView sv;
    @BindView(R.id.liushi)
    LiuShi liushi;

    @Override
    public Class<ShowActivityPresenter> getClassDeleGate() {
        return ShowActivityPresenter.class;
    }

    @Override
    public void initWeight() {
        super.initWeight();
        ButterKnife.bind(this);
        deleGate.initView(sv,liushi);
    }

    @OnClick(R.id.sv)
    public void click() {
        deleGate.initClick();

    }
}
