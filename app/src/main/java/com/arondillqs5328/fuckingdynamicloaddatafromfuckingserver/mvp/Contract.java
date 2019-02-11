package com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver.mvp;

import com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver.data.Coin;

import java.util.List;

public interface Contract {

    interface View {
        void showCoinList(List<Coin> coins);
        void updateParameters();
        void showNoInternetConnection();
    }

    interface Presenter {
        void detachView();
        void loadMore(int start, int limit);
    }

    interface Repository {
        void load(int start, int limit);
    }
}
