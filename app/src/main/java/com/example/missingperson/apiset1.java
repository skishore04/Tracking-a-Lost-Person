package com.example.missingperson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface apiset1 {

    @GET("found_fetch.php")
    Call<List<responsemodel1>> getdata();
}
