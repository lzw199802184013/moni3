package soexample.umeng.com.moni3.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import soexample.umeng.com.moni3.R;

public class NumView extends RelativeLayout {
    private ImageView add_car, jian_car;
    private EditText edit_shop_car;

    public NumView(Context context) {
        super(context);
        init(context);
    }

    public NumView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NumView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private Context context;

    private void init(final Context context) {
        this.context = context;
        View view = View.inflate(context, R.layout.layout_numview, null);
        add_car = (ImageView) view.findViewById(R.id.add_car);
        edit_shop_car = (EditText) view.findViewById(R.id.edit_shop_car);
        jian_car = (ImageView) view.findViewById(R.id.jian_car);

        add_car.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                num++;
                listener.setNums(num);
            }
        });

        edit_shop_car.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editable.toString();
                if (listener != null) {
                    if (!TextUtils.isEmpty(s)) {
                        int i = Integer.parseInt(s);
                        listener.setNums(i);
                    }
                }
            }
        });
        jian_car.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num > 1) {
                    num--;
                    listener.setNums(num);
                } else {
                    Toast.makeText(context, "最少一件啊!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        addView(view);
    }

    private int num;

    public void setList(int num) {
        this.num = num;
        edit_shop_car.setText(num + "");
    }


    //接口回调
    private OnNumClickListener listener;

    public void setNumClick(OnNumClickListener listener) {
        this.listener = listener;
    }

    public interface OnNumClickListener {
        void setNums(int num);
    }
}
