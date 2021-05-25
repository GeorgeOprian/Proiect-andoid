package com.example.cinehub.API;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerAPIBuilder {

    private static ServerApiService apiBuilder;
    private final static String BASE_URL = "http://192.168.0.116:8081";
    public final static String API_KEY = "9dee1adf";

    public static ServerApiService getInstance(){
        if(apiBuilder == null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiBuilder = retrofit.create(ServerApiService.class);
        }
        return apiBuilder;
    }


}
