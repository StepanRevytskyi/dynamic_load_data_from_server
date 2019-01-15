package com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver.mvp;

import com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver.data.Coin;

import java.util.List;

public interface Contract {

    interface View {
        void showCoinList(List<Coin> coins);
        void updateParameters();
    }

    interface Presenter {
        void detachView();
        void loadMore(int start, int limit, String API_KEY);
        void onSuccess(List<Coin> coins);
    }

    interface Repository {
        void load(int start, int limit, String API_KEY);
    }
}
