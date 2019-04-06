package wulinpeng.com.framework.base.net

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 * @author wulinpeng
 * @datetime: 2019/4/6 8:42 PM
 * @description:
 */
object RetrofitClient {
    var client: Retrofit = Retrofit.Builder()
            .baseUrl(ApiConstant.API_BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
}