package soexample.umeng.com.moni3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.umeng.com.moni3.R;
import soexample.umeng.com.moni3.model.ShopBean;

public class ShopCarAdapter extends RecyclerView.Adapter<ShopCarAdapter.MyViewHolder> {
    private Context context;
    private List<ShopBean.DataBean> data1 = new ArrayList<>();

    public ShopCarAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ShopCarAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.layout_left, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopCarAdapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.tv_title1.setText(data1.get(i).getSellerName());
        myViewHolder.cb.setChecked(data1.get(i).isChecks());
        //chebox点击
        myViewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //商家
                if (data1.get(i).isChecks()) {
                    data1.get(i).setChecks(false);
                } else {
                    data1.get(i).setChecks(true);
                }
                //商品
                List<ShopBean.DataBean.ListBean> list = data1.get(i).getList();
                for (int c = 0; c < list.size(); c++) {
                    if (list.get(c).isCheck()) {
                        list.get(c).setCheck(false);
                    } else {
                        list.get(c).setCheck(true);
                    }
                }
                listener.item(data1);
                notifyItemChanged(i);
            }
        });
        //嵌套适配器
        createRecyclerView2(myViewHolder.recyclerView2, data1.get(i).getList());
    }

    private void createRecyclerView2(RecyclerView recyclerView2, List<ShopBean.DataBean.ListBean> list) {
        ShopCarChildAdapter shopCarChildAdapter = new ShopCarChildAdapter(context, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView2.setLayoutManager(linearLayoutManager);
        recyclerView2.setAdapter(shopCarChildAdapter);
        //接口回调
        shopCarChildAdapter.setClickListener(new ShopCarChildAdapter.OnItemClickListener() {
            @Override
            public void item() {
                listener.item(data1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data1.size();
    }

    public void setList(List<ShopBean.DataBean> list) {
        this.data1 = list;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cb)
        CheckBox cb;
        @BindView(R.id.tv_title1)
        TextView tv_title1;
        @BindView(R.id.recyclerView2)
        RecyclerView recyclerView2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //接口回调
    private OnItemClickListener listener;

    public void setClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }

    public interface OnItemClickListener {
        void item(List<ShopBean.DataBean> data1);
    }
}
