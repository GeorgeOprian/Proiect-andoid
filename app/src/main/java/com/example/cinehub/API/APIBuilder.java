package com.example.cinehub.API;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class APIBuilder {

    private static APIService apiBuilder;
    private final static String BASE_URL = "http://www.omdbapi.com";
    public final static String API_KEY = "9dee1adf";

    public static APIService getInstance(){
        if(apiBuilder == null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build();
            apiBuilder = retrofit.create(APIService.class);
        }
        return apiBuilder;
    }
}
