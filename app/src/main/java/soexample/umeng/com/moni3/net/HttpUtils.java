package soexample.umeng.com.moni3.net;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class HttpUtils {

    private BaseService baseService;

    public HttpUtils() {

        //初始化retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.zhaoapi.cn/")//域名
                //适配器工厂
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        baseService = retrofit.create(BaseService.class);
    }

    //get
    public HttpUtils get(String url, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        baseService.get(url, map)
                //设置调度器
                .subscribeOn(Schedulers.io())
                //主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return this;
    }

    //post
    public HttpUtils post(String url, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        baseService.post(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return this;
    }

    //共用一个观察者
    private Observer observer = new Observer<ResponseBody>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(ResponseBody responseBody) {
            try {
                String data = responseBody.string();
                listener.success(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Throwable e) {
            String message = e.getMessage();
            listener.error(message);
        }

        @Override
        public void onComplete() {

        }
    };

    private HttpListener listener;

    public void result(HttpListener listener) {
        this.listener = listener;
    }

    public interface HttpListener {
        void success(String data);

        void error(String error);

    }

    ;
}
