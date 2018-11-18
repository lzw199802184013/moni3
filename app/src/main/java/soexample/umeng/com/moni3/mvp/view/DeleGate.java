package soexample.umeng.com.moni3.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface DeleGate {
    //初始化方法
    void initData();

    //初始化布局
    void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle);

    //创建view
    View getrootView();

    //上下文
    void getContext(Context context);

}
