package soexample.umeng.com.moni3.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import soexample.umeng.com.moni3.mvp.view.AppDeleGate;

public abstract class BaseActivityPresenter<T extends AppDeleGate> extends AppCompatActivity {

    public T deleGate;

    public abstract Class<T> getClassDeleGate();

    public BaseActivityPresenter() {
        try {
            deleGate = getClassDeleGate().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deleGate.create(getLayoutInflater(), null, savedInstanceState);
        setContentView(deleGate.getrootView());
        deleGate.getContext(this);
        initWeight();
        deleGate.initData();
    }

    public void initWeight() {


    }
}

