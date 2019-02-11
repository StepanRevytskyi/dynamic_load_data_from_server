package com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver.mvp;

import com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver.data.Coin;

import java.util.List;

public interface MainCallback {
    void onFailed();
    void onSuccess(List<Coin> coins);
}
