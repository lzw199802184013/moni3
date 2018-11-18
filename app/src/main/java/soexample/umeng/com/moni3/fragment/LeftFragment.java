package soexample.umeng.com.moni3.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import soexample.umeng.com.moni3.R;
import soexample.umeng.com.moni3.mvp.presenter.BaseFragmentPresenter;
import soexample.umeng.com.moni3.presenter.LeftFragmentPresenter;

public class LeftFragment extends BaseFragmentPresenter<LeftFragmentPresenter> {
    @BindView(R.id.sv)
    SearchView sv;
    @BindView(R.id.linearlayout)
    LinearLayout linearlayout;
    private Unbinder bind;

    @Override
    public Class<LeftFragmentPresenter> getClassDeleGate() {
        return LeftFragmentPresenter.class;
    }

    @Override
    public void initWeight() {
        super.initWeight();
        bind = ButterKnife.bind(this, getView());

    }

    @OnClick(R.id.linearlayout)
    public void click() {
        deleGate.initClick();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }
}
