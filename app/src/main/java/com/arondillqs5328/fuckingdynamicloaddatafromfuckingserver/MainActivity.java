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
import com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver.mvp.Contract;
import com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver.mvp.Presenter;
import com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver.mvp.Repository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Contract.View {
    private Presenter mPresenter;

    private RecyclerView mRecyclerView;
    private CoinListAdapter mAdapter;

    private boolean isLoading = true;
    private Integer mStart = 1;
    private Integer mLimit = 50;
    private List<Coin> mCoins = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new Presenter(this);

        mAdapter = new CoinListAdapter(mCoins);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLoading && ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findLastVisibleItemPosition() == mRecyclerView.getLayoutManager().getItemCount() - 1) {
                    mPresenter.loadMore(mStart, mLimit, Common.API_KEY);
                    isLoading = false;
                }
            }
        });

        mPresenter.loadMore(mStart, mLimit, Common.API_KEY);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showCoinList(List<Coin> coins) {
        mCoins.addAll(coins);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateParameters() {
        mStart = mStart + mLimit;
        isLoading = true;
    }
}
