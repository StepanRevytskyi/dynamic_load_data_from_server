package com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private Retrofit mRetrofit;

    public Retrofit getRetrofitInstance() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("X-CMC_PRO_API_KEY", "63405db2-b682-4c22-8af7-255b21e3f09d")
                                .build();

                        return chain.proceed(request);
                    }
                }).build();

        mRetrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://pro-api.coinmarketcap.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return mRetrofit;
    }

}
