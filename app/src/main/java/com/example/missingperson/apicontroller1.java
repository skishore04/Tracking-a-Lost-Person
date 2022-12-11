package com.example.missingperson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apicontroller1 {


    private static final String url= "http://192.168.10.214/lostperson/";
    private static apicontroller1 clientobject;
    private static Retrofit retrofit;

    apicontroller1()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static  synchronized apicontroller1 getInstance()
    {
        if(clientobject==null)
            clientobject= new apicontroller1();

        return clientobject;
    }

    apiset1 getapi()
    {
        return retrofit.create(apiset1.class);
    }
}
