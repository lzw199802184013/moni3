package soexample.umeng.com.moni3.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.moni3.R;
import soexample.umeng.com.moni3.mvp.presenter.BaseFragmentPresenter;
import soexample.umeng.com.moni3.presenter.LeftFragmentPresenter;
import soexample.umeng.com.moni3.presenter.RightFragmentPresenter;

public class RightFragment extends BaseFragmentPresenter<RightFragmentPresenter> {
    @BindView(R.id.bianji)
    TextView bianji;
    @BindView(R.id.recyclerView1)
    RecyclerView recyclerView1;
    @BindView(R.id.iv_circle)
    CheckBox iv_circle;
    @BindView(R.id.tv_heji)
    TextView tv_heji;
    @BindView(R.id.go)
    TextView go;
    @BindView(R.id.rl_delete)
    RelativeLayout rl_delete;

    @Override
    public Class<RightFragmentPresenter> getClassDeleGate() {
        return RightFragmentPresenter.class;
    }

    @Override
    public void initWeight() {
        super.initWeight();
        ButterKnife.bind(this, getView());
        deleGate.initView(bianji, recyclerView1, iv_circle, tv_heji, go, rl_delete);
    }

    @OnClick({R.id.iv_circle, R.id.bianji, R.id.rl_delete})
    public void click(View view) {
        deleGate.initClick(view);
    }
}
