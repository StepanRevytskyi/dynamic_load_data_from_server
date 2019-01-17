package com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver.mvp;

import com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver.data.Coin;

import java.util.List;

public class Presenter implements Contract.Presenter{
    private Contract.View mView;
    private Repository mRepository;

    public Presenter(Contract.View view) {
        mView = view;
        mRepository = new Repository(this);
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void loadMore(int start, int limit) {
        mRepository.load(start, limit);
    }

    @Override
    public void onSuccess(List<Coin> coins) {
        mView.showCoinList(coins);
        mView.updateParameters();
    }
}
