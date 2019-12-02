package com.atp.rewayti.API;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebServices {


   @GET("api/Category")
    Call<ResponseBody> getCategories(
           @Query("id") int id
   );
}
