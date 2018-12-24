package com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver;

import com.arondillqs5328.fuckingdynamicloaddatafromfuckingserver.data.CoinListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoinApi {

    @GET("v1/cryptocurrency/map")
    Call<CoinListResponse> getCoinListResponse(@Query("CMC_PRO_API_KEY") String api_key,
                                               @Query("start") Integer start,
                                               @Query("limit") Integer limit);

}
