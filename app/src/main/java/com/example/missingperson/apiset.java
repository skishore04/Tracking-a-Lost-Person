package com.example.missingperson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface apiset {
    @GET("lost_fetch.php")
    Call<List<ResponseModel>> getdata();
}
