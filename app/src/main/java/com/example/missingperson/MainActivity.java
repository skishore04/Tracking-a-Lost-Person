package com.example.missingperson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
          Button Lost = findViewById(R.id.buttonlost);
            Lost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,ReportLostPerson.class);
                    startActivity(intent);

                }
            });
            Button found = findViewById(R.id.buttonfound);
            found.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,ReportFoundPerson.class);
                    startActivity(intent);

                }
            });
            Button lostper = findViewById(R.id.checklostfound);
            lostper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,CheckLostPerson.class);
                    startActivity(intent);

                }
            });
            Button foundper = findViewById(R.id.checkfoundperson);
            foundper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,CheckFoundPerson.class);
                    startActivity(intent);

                }
            });
            TextView contact = findViewById(R.id.contact);
            contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,ContactUs.class);
                    startActivity(intent);

                }
            });


        }
    }
}