package soexample.umeng.com.moni3.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import soexample.umeng.com.moni3.R;

public class LiuShi extends RelativeLayout {
    private LinearLayout linear_layout;


    public LiuShi(Context context) {
        super(context);
        init(context);
    }

    public LiuShi(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LiuShi(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private Context context;

    private void init(Context context) {
        this.context = context;
        //垂直布局
        View view = View.inflate(context, R.layout.layout_ver, null);
        //垂直布局的id
        linear_layout = (LinearLayout) view.findViewById(R.id.linear_layout);
        addView(view);
    }

    private LinearLayout view1;
    private int position = 0;//索引

    public void setData(final List<String> stringList) {
        linear_layout.removeAllViews();//清除垂直布局
        //水平布局
        view1 = (LinearLayout) View.inflate(context, R.layout.layout_hor, null);
        linear_layout.addView(view1);
        int len = 0;//长度
        int size = 0;//大小
        //清楚水平布局
        view1.removeAllViews();
        for (int a = 0; a < stringList.size(); a++) {
            String s = stringList.get(a);
            len += s.length();
            size++;
            if (len > 18 || size > 5) {
                view1 = (LinearLayout) View.inflate(context, R.layout.layout_hor, null);
                linear_layout.addView(view1);
                len = 0;
                size = 0;
            }
            //textview布局
            final View viewText = View.inflate(context, R.layout.layout_content, null);
            //textviewid
            TextView tv_txt = viewText.findViewById(R.id.tv_text);
            //设置输入的值
            tv_txt.setText(s);
            //添加到水平布局中
            view1.addView(viewText);
            //设置textview的宽高
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewText.getLayoutParams();
            params.weight = 1;
            params.leftMargin = 10;
            params.rightMargin = 10;
            params.topMargin = 10;
            //添加到textview布局里
            viewText.setLayoutParams(params);
            //长按删除
            tv_txt.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    //删除textview布局
                    view1.removeView(viewText);
                    //根据索引删除集合
                    stringList.remove(position);
                    setData(stringList);
                    return true;
                }
            });
        }


    }


}
