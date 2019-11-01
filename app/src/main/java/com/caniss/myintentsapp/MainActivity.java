package com.caniss.myintentsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button mBtnMpesa,mBtnCall,mBtnSms,mBtnCamera,mBtnDial,mBtnEmail,mBtnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnMpesa = findViewById(R.id.btnMpesa);
        mBtnCall = findViewById(R.id.btnCall);
        mBtnSms = findViewById(R.id.btnSms);
        mBtnCamera = findViewById(R.id.btnCamera);
        mBtnDial = findViewById(R.id.btnDial);
        mBtnEmail = findViewById(R.id.btnEmail);
        mBtnShare = findViewById(R.id.btnShare);

        mBtnMpesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent simToolKitLaunchIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.android.stk");
                if(simToolKitLaunchIntent != null) {
                    startActivity(simToolKitLaunchIntent);
                }
            }
        });
        mBtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+254710899187"));
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},1);
                }
                else
                {
                    startActivity(intent);
                }
            }
        });
        mBtnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("smsto:YOUR_SMS_NUMBER");
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", "Hello there, Bogie is back");
                startActivity(intent);
            }
        });
        mBtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, 1);
            }
        });
        mBtnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "07";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });
        mBtnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","abc@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "JOB APPLICATION");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "This is,,,");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
        mBtnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hey, download this app!");
                startActivity(shareIntent);
            }
        });
    }
}
