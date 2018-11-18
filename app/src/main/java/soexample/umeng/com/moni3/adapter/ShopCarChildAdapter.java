package soexample.umeng.com.moni3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.umeng.com.moni3.R;
import soexample.umeng.com.moni3.model.ShopBean;
import soexample.umeng.com.moni3.view.NumView;

public class ShopCarChildAdapter extends RecyclerView.Adapter<ShopCarChildAdapter.MyViewHolder> {
    private Context context;
    private List<ShopBean.DataBean.ListBean> list;

    public ShopCarChildAdapter(Context context, List<ShopBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public ShopCarChildAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.layout_right, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopCarChildAdapter.MyViewHolder myViewHolder, final int i) {
        String images = list.get(i).getImages();
        String[] split = images.split("[|]");
        myViewHolder.car_sd.setImageURI(split[0]);
        myViewHolder.car_title.setText(list.get(i).getTitle());
        myViewHolder.car_price.setText("￥:" + list.get(i).getPrice());
        //全选全不选
        if (list.get(i).isCheck()) {
            myViewHolder.car_cicrle.setChecked(true);
        } else {
            myViewHolder.car_cicrle.setChecked(false);
        }
        //改变全选状态
        myViewHolder.car_cicrle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.get(i).isCheck()) {
                    list.get(i).setCheck(false);
                } else {
                    list.get(i).setCheck(true);
                }
                //单条目刷新
                notifyItemChanged(i);
                listener.item();//回调
            }
        });
        //自定义加减
        myViewHolder.nv.setList(list.get(i).getNum());
        //接口回调
        myViewHolder.nv.setNumClick(new NumView.OnNumClickListener() {
            @Override
            public void setNums(int num) {
                //传值给num
                list.get(i).setNum(num);
                listener.item();
                notifyItemInserted(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.car_cicrle)
        CheckBox car_cicrle;
        @BindView(R.id.car_sd)
        SimpleDraweeView car_sd;
        @BindView(R.id.car_title)
        TextView car_title;
        @BindView(R.id.car_price)
        TextView car_price;
        @BindView(R.id.nv)
        NumView nv;

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
        void item();
    }
}
