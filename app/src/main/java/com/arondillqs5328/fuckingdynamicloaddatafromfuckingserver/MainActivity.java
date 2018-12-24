package com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver.data.Coin;
import com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver.data.CoinListResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CoinApi mCoinApi;

    private RecyclerView mRecyclerView;
    private CoinListAdapter mAdapter;

    private boolean isLoading = true;
    private Integer mStart = 1;
    private Integer mLimit = 25;
    private List<Coin> mCoins = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCoinApi = FuckingApplication.sRetrofit.create(CoinApi.class);

        mAdapter = new CoinListAdapter(mCoins);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLoading && ((LinearLayoutManager)mRecyclerView.getLayoutManager()).findLastVisibleItemPosition() == mRecyclerView.getLayoutManager().getItemCount() - 1) {
                    //TODO:додати індикатор загрузки для recyclerview.footer
                    loadCoinListFromServer(Common.API_KEY, mStart, mLimit);
                    isLoading = false;
                }
            }
        });

        loadCoinListFromServer(Common.API_KEY, mStart, mLimit);
    }

    private void loadCoinListFromServer(String apiKey, Integer start, Integer limit) {
        Call<CoinListResponse> call = mCoinApi.getCoinListResponse(apiKey, start, limit);
        call.enqueue(new Callback<CoinListResponse>() {
            @Override
            public void onResponse(Call<CoinListResponse> call, Response<CoinListResponse> response) {
                mCoins.addAll(response.body().getCoinList());
                mAdapter.notifyDataSetChanged();
                mStart = mStart + mLimit;
                isLoading = true;
                //TODO:після загрузки сховати індикатор загрузки для recyclerview.footer
            }

            @Override
            public void onFailure(Call<CoinListResponse> call, Throwable t) {
                Log.i("ERROR", t.toString());
            }
        });
    }
}
