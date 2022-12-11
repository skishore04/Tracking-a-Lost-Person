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

public class CheckFoundPerson extends AppCompatActivity {

    RecyclerView recview1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_found_person);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recview1 =findViewById(R.id.recview1);
        recview1.setLayoutManager(new LinearLayoutManager(this));


//      LinearLayoutManager llm = new LinearLayoutManager(this);
//      llm.setOrientation(LinearLayoutManager.VERTICAL);
//       recview.setLayoutManager(llm);

        processdata();
    }

    public void processdata() {

        Call<List<responsemodel1>> call = apicontroller1
                .getInstance()
                .getapi()
                .getdata();

        call.enqueue(new Callback<List<responsemodel1>>() {
            @Override
            public void onResponse(Call<List<responsemodel1>> call, Response<List<responsemodel1>> response) {
                List<responsemodel1> data = response.body();
                myadpater1 adpater = new myadpater1(data);
                recview1.setAdapter(adpater);

            }

            @Override
            public void onFailure(Call<List<responsemodel1>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}