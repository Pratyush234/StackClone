package com.example.praty.stackclone.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//class to instantaniate Retrofit
public class ApiClient {

    private static final String BASE_URL="https://api.stackexchange.com/";
    private static Retrofit retrofit=null;

    public static Retrofit getclient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
