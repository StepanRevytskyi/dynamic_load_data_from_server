package com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver.data.Coin;

import java.util.List;

public class CoinListAdapter extends RecyclerView.Adapter<CoinListAdapter.CoinViewHolder> {

    private List<Coin> mCoins;

    public CoinListAdapter(List<Coin> coins) {
        mCoins = coins;
    }

    public void addNewCoins(List<Coin> coins) {
        mCoins.addAll(coins);
    }

    @NonNull
    @Override
    public CoinViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.coin_item, viewGroup, false);
        CoinViewHolder holder = new CoinViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CoinViewHolder coinViewHolder, int i) {
        coinViewHolder.bind(mCoins.get(i));
    }

    @Override
    public int getItemCount() {
        return mCoins.size();
    }

    public class CoinViewHolder extends RecyclerView.ViewHolder {

        private TextView mIdTextView;
        private TextView mNameTextView;

        CoinViewHolder(@NonNull View itemView) {
            super(itemView);
            mIdTextView = itemView.findViewById(R.id.id);
            mNameTextView = itemView.findViewById(R.id.name);
        }

        public void bind(Coin coin) {
            mIdTextView.setText(String.valueOf(coin.getId()));
            mNameTextView.setText(coin.getName());
        }
    }
}
