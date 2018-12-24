package com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CoinListResponse {

    @SerializedName("data")
    @Expose
    private List<Coin> mCoinList;

    @SerializedName("status")
    @Expose
    private Status mStatus;

    public CoinListResponse() {

    }

    public CoinListResponse(List<Coin> coinList, Status status) {
        mCoinList = coinList;
        mStatus = status;
    }

    public List<Coin> getCoinList() {
        return mCoinList;
    }

    public void setCoinList(List<Coin> coinList) {
        mCoinList = coinList;
    }

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        mStatus = status;
    }
}
