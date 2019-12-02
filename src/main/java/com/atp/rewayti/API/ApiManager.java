package com.atp.rewayti.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {



    private static Retrofit retrofitInstance;
    private static Retrofit getInstance(){
        if(retrofitInstance==null){//create

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

           retrofitInstance = new Retrofit.Builder()
                    .baseUrl("http://jam-bee.com/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofitInstance;
    }

    public static WebServices getAPIs(){
        WebServices services = getInstance().create(WebServices.class);
        return services;
    }
}




