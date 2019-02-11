package com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver.mvp;

import android.util.Log;

import com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver.CoinApi;
import com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver.data.CoinListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository implements Contract.Repository {
    private CoinApi mCoinApi;
    private MainCallback mCallback;

    public Repository(CoinApi coinApi) {
        mCoinApi = coinApi;
    }

    public void setCallback(MainCallback callback) {
        mCallback = callback;
    }

    @Override
    public void load(int start, int limit) {
        Call<CoinListResponse> call = mCoinApi.getCoinListResponse(start, limit);
        call.enqueue(new Callback<CoinListResponse>() {
            @Override
            public void onResponse(Call<CoinListResponse> call, Response<CoinListResponse> response) {
                if (response.body().getStatus().getErrorCode() == 0) {
                    mCallback.onSuccess(response.body().getCoinList());
                }
            }

            @Override
            public void onFailure(Call<CoinListResponse> call, Throwable t) {
                Log.i("ERROR", t.toString());
                mCallback.onFailed();
            }
        });
    }
}
