package soexample.umeng.com.moni3.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import soexample.umeng.com.moni3.R;
import soexample.umeng.com.moni3.adapter.ShopCarAdapter;
import soexample.umeng.com.moni3.model.ShopBean;
import soexample.umeng.com.moni3.mvp.view.AppDeleGate;
import soexample.umeng.com.moni3.net.HttpUtils;

public class RightFragmentPresenter extends AppDeleGate {
    private Context context;
    private TextView bianji;
    private RecyclerView recyclerView1;
    private CheckBox iv_circle;
    private TextView tv_heji;
    private TextView go;
    private List<ShopBean.DataBean> data1 = new ArrayList<>();
    private ShopCarAdapter shopCarAdapter;
    private RelativeLayout rl_delete;
    int numStatus = 0;//标识

    @Override
    public int getLayoutId() {
        return R.layout.right_fragment;
    }

    @Override
    public void initData() {
        super.initData();
        doGet();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(linearLayoutManager);
        shopCarAdapter = new ShopCarAdapter(context);
        recyclerView1.setAdapter(shopCarAdapter);
        //接口回调
        shopCarAdapter.setClickListener(new ShopCarAdapter.OnItemClickListener() {
            @Override
            public void item(List<ShopBean.DataBean> data) {
                numStatus = 0;
                data1 = data;
                double allPrice = 0;//价格
                int num = 0;//选中的个数
                int num1 = 0;//类型个数
                int numAll = 0;//所有的个数
                int num2 = 0;//商品选中的个数
                for (int a = 0; a < data1.size(); a++) {
                    List<ShopBean.DataBean.ListBean> list = data1.get(a).getList();
                    num2 = 0;
                    for (int b = 0; b < list.size(); b++) {
                        numAll++;//全部的个数
                        if (list.get(b).isCheck()) {
                            numStatus = 1;
                            num1++;//类型个数加加
                            num2++;//商品选中的个数
                            allPrice += list.get(b).getPrice() * list.get(b).getNum();//价格
                            num += list.get(b).getNum();//选中个数加加

                        }
                    }
                    //如果商品的个数等于商家就全部选中
                    if (list.size() == num2) {
                        data1.get(a).setChecks(true);
                    } else {
                        data1.get(a).setChecks(false);
                    }

                }
                //如果选中类型个数小于全部个数就改变状态
                if (num1 < numAll) {
                    iv_circle.setChecked(false);
                    isClick = true;

                } else {
                    iv_circle.setChecked(true);
                    isClick = false;
                }
                tv_heji.setText("合计:" + String.format("%.2f", allPrice));
                go.setText("去结算(" + num + ")");
                shopCarAdapter.setList(data1);
            }
        });
    }

    //获取商家
    private void doGet() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", "71");
        new HttpUtils().get("/product/getCarts", map).result(new HttpUtils.HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                ShopBean shopBean = gson.fromJson(data, ShopBean.class);
                data1 = shopBean.getData();
                data1.remove(0);
                shopCarAdapter.setList(data1);
            }

            @Override
            public void error(String error) {

            }
        });
    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    //view
    public void initView(TextView bianji, RecyclerView recyclerView1, CheckBox iv_circle, TextView tv_heji, TextView go, RelativeLayout rl_delete) {
        this.bianji = bianji;
        this.recyclerView1 = recyclerView1;
        this.iv_circle = iv_circle;
        this.tv_heji = tv_heji;
        this.go = go;
        this.rl_delete = rl_delete;
    }

    //点击事件
    private boolean isClick = true;//开关
    private boolean isDelete = true;//删除开关

    public void initClick(View view) {
        switch (view.getId()) {
            case R.id.iv_circle: //全选/全不选
                if (isClick) {
                    allGoods(true);
                    isClick = false;
                } else {
                    allGoods(false);
                    isClick = true;

                }

                break;
            case R.id.bianji:
                //编辑
                if (isDelete) {
                    bianji.setText("完成");
                    tv_heji.setVisibility(View.GONE);
                    rl_delete.setVisibility(View.VISIBLE);
                    isDelete = false;
                } else {
                    bianji.setText("编辑");
                    tv_heji.setVisibility(View.VISIBLE);//合计
                    tv_heji.setText("合计:0.00");
                    go.setText("去结算(0)");
                    rl_delete.setVisibility(View.GONE);
                    isDelete = true;
                }
                break;
            case R.id.rl_delete:
                for (int i = 0; i < data1.size(); i++) {
                    List<ShopBean.DataBean.ListBean> list = data1.get(i).getList();
                    removes(i, list);
                    if (list.size() == 0) {
                        data1.remove(i);//删除商家和商品的所有信息
                    }
                }

                if (numStatus == 0) {
                    toast(context, "还没有选择商品");
                }

                //刷新适配器
                shopCarAdapter.setList(data1);
                break;

        }
    }

    //删除商品

    private void removes(int i, List<ShopBean.DataBean.ListBean> list) {
        int num = 0;
        for (int j = 0; j < list.size(); j++) {
            if (list.get(j).isCheck()) {
                num++;
                list.remove(j);//删除选中的商品
            }
        }
        if (num != 0) {
            removes(i, list);
        } else {
            numStatus = 0;
        }

    }


    //全选/全不选
    private void allGoods(boolean bool) {
        double allPrice = 0;//总价格
        int num = 0;//数量
        for (int a = 0; a < data1.size(); a++) {
            //商家的复选框选中
            data1.get(a).setChecks(bool);
            List<ShopBean.DataBean.ListBean> list = data1.get(a).getList();
            for (int b = 0; b < list.size(); b++) {
                list.get(b).setCheck(bool);//商品选中
                allPrice += list.get(b).getPrice() * list.get(b).getNum();//价格
                num += list.get(b).getNum();//数量
            }

        }
        if (bool) {
            tv_heji.setText("合计:" + String.format("%.2f", allPrice));
            go.setText("去结算(" + num + ")");
        } else {
            tv_heji.setText("合计:0.00");
            go.setText("去结算(0)");
        }
        //刷新适配器
        shopCarAdapter.notifyDataSetChanged();
    }
}
