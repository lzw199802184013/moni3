package soexample.umeng.com.moni3.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soexample.umeng.com.moni3.mvp.view.AppDeleGate;

public abstract class BaseFragmentPresenter<T extends AppDeleGate> extends Fragment {

    public T deleGate;

    public abstract Class<T> getClassDeleGate();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            deleGate = getClassDeleGate().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        deleGate.create(inflater, container, savedInstanceState);
        return deleGate.getrootView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        deleGate.getContext(getActivity());
        initWeight();
        deleGate.initData();
    }

    public void initWeight() {

    }
}
