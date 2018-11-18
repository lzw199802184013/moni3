package soexample.umeng.com.moni3.presenter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.moni3.R;
import soexample.umeng.com.moni3.fragment.LeftFragment;
import soexample.umeng.com.moni3.fragment.RightFragment;
import soexample.umeng.com.moni3.mvp.view.AppDeleGate;
import soexample.umeng.com.moni3.view.MainActivity;

public class MainActivityPresenter extends AppDeleGate {
    private Context context;
    private ViewPager vp_view;
    private TabLayout tablayout;
    private String[] mTitle = {"首页", "购物车"};
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        super.initData();

        fragmentList.add(new LeftFragment());
        fragmentList.add(new RightFragment());
        vp_view.setAdapter(new FragmentPagerAdapter(((MainActivity) context).getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle[position];
            }
        });
        tablayout.setupWithViewPager(vp_view);

    }


    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    public void initView(ViewPager vp_view, TabLayout tablayout) {
        this.vp_view = vp_view;
        this.tablayout = tablayout;

    }
}
