package com.atp.rewayti.API;


import com.atp.rewayti.API.model.Deals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebServices {


   @GET("admin/api/")
    Call<Deals> getDeals(
           @Query("action") String action ,
           @Query("tag") String tag
   );


}
