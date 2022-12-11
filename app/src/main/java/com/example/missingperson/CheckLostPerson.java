package com.example.missingperson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckLostPerson extends AppCompatActivity {

    RecyclerView recview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_lost_person);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recview =findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));


//      LinearLayoutManager llm = new LinearLayoutManager(this);
//      llm.setOrientation(LinearLayoutManager.VERTICAL);
//       recview.setLayoutManager(llm);

        processdata();

    }

    public void processdata() {
        Call<List<ResponseModel>> call = apicontroller
                .getInstance()
                .getapi()
                .getdata();

        call.enqueue(new Callback<List<ResponseModel>>() {
            @Override
            public void onResponse(Call<List<ResponseModel>> call, Response<List<ResponseModel>> response) {
                List<ResponseModel> data = response.body();
                myadpater adpater = new myadpater(data);
                recview.setAdapter(adpater);

            }

            @Override
            public void onFailure(Call<List<ResponseModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }


}
