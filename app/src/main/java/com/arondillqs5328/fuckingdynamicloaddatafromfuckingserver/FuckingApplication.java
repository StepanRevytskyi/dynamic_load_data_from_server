package com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver;

import android.app.Application;

import retrofit2.Retrofit;

public class FuckingApplication extends Application {

    public static Retrofit sRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        sRetrofit = RetrofitClient.getRetrofitInstance();
    }
}
