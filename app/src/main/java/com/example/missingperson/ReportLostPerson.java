package com.example.missingperson;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.icu.text.UnicodeSetSpanner;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;
import java.util.UUID;

public class ReportLostPerson extends AppCompatActivity {

    ImageView imageView;
    EditText Name,Gender,Age,Date,Time,Address,Number,Marks;
    private static final int STORAGE_PERMISSION_CODE = 4655;
    private int PICK_IMAGE_REQUEST = 1;
    private Uri filepath;
    private Bitmap bitmap;
    TextView tv;
    public static final String UPLOAD_URL = "http://192.168.10.214/lostperson/lostreport.php?add=333";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_lost_person);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        requestStoragePermission();

        imageView = (ImageView) findViewById(R.id.imageView);
        Name = (EditText) findViewById(R.id.editTextTextPersonName);
        Gender = (EditText) findViewById(R.id.editTextTextGender);
        Age = (EditText) findViewById(R.id.editTextTextAge);
        Date = (EditText) findViewById(R.id.editTextTextDate);
        Time = (EditText) findViewById(R.id.editTextTextTime);
        Address =(EditText) findViewById(R.id.editTextTextAdress);
        Number = (EditText) findViewById(R.id.editTextNumber) ;
        Marks = (EditText) findViewById(R.id.editTextmarks) ;


        tv=(TextView)findViewById(R.id.tv4);

    }


    private void requestStoragePermission() {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void ShowFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && data != null && data.getData() != null) {

            filepath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                imageView.setImageBitmap(bitmap);
                tv.setText(filepath.toString());
                // Toast.makeText(getApplicationContext(),getPath(filepath),Toast.LENGTH_LONG).show();
            } catch (Exception ex) {

            }
        }
    }

    public void selectImage(View view)
    {

        ShowFileChooser();
    }


    private String getPath(Uri uri) {

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media._ID + "=?", new String[]{document_id}, null
        );
        cursor.moveToFirst();
        @SuppressLint("Range") String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }

    private void uploadImage() {
        String name = Name.getText().toString().trim();
        String gender = Gender.getText().toString().trim();
        String age = Age.getText().toString().trim();
        String date = Date.getText().toString().trim();
        String time = Time.getText().toString().trim();
        String address = Address.getText().toString().trim();
        String number = Number.getText().toString().trim();
        String marks = Marks.getText().toString().trim();


        String path = getPath(filepath);
        try {
            String uploadId = UUID.randomUUID().toString();
            new MultipartUploadRequest(this, uploadId, UPLOAD_URL).addFileToUpload(path, "upload").addParameter("t1", name).addParameter("t2", gender).addParameter("t3",age).addParameter("t4",date).addParameter("t5",time).addParameter("t6",address).addParameter("t7",number).addParameter("t8",marks)
                    .setMaxRetries(3)
                    .startUpload();
            Toast.makeText(ReportLostPerson.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();



        } catch (Exception ex) {


        }

    }


    public void saveData(View view)
    {
        uploadImage();
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ReportLostPerson.this,MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });

    }
}