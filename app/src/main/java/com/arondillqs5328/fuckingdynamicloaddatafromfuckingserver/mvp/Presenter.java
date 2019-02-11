package com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver.mvp;

import com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver.data.Coin;

import java.util.List;

public class Presenter implements Contract.Presenter, MainCallback{
    private Contract.View mView;
    private Repository mRepository;

    public Presenter(Contract.View view, Repository repository) {
        mView = view;
        mRepository = repository;
        mRepository.setCallback(this);
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void loadMore(int start, int limit) {
        if (isNetworkConnection()) {
            mRepository.load(start, limit);
        } else {
            mView.showNoInternetConnection();
        }
    }

    private boolean isNetworkConnection() {
        return true;
    }

    @Override
    public void onFailed() {

    }

    @Override
    public void onSuccess(List<Coin> coins) {
        mView.showCoinList(coins);
        mView.updateParameters();
    }
}
