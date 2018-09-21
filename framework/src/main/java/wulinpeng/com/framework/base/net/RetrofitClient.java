package wulinpeng.com.framework.base.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author wulinpeng
 * @datetime: 18/9/22 上午12:26
 * @description:
 */
public class RetrofitClient {

    private static Retrofit mClient;

    public static Retrofit getClient() {
        if (mClient == null) {
            synchronized (RetrofitClient.class) {
                if (mClient == null) {
                    mClient = new Retrofit.Builder()
                            .baseUrl(ApiConstant.API_BASE_URL)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return mClient;
    }
}
