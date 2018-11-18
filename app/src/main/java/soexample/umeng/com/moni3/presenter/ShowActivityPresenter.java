package soexample.umeng.com.moni3.presenter;

import android.content.Context;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.moni3.R;
import soexample.umeng.com.moni3.mvp.view.AppDeleGate;
import soexample.umeng.com.moni3.view.LiuShi;

public class ShowActivityPresenter extends AppDeleGate {
    private Context context;
    private SearchView sv;
    private List<String> stringList = new ArrayList<>();
    private LiuShi liushi;

    @Override
    public int getLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    public void initData() {
        super.initData();
    }


    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    public void initView(SearchView sv, LiuShi liushi) {
        this.sv = sv;
        this.liushi = liushi;
    }

    //点击
    public void initClick() {
        sv.setIconifiedByDefault(false);
        sv.setSubmitButtonEnabled(true);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                stringList.add(0,s);
                liushi.setData(stringList);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

}
