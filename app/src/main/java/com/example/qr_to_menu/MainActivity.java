package com.example.qr_to_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    CodeScanner codeScanner;
    CodeScannerView scannerView;
    TextView resultText;
    Button registerButton;
    public static final String SENT_TEXT = "com.example.qr_to_menu.SENT_TEXT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scannerView = findViewById(R.id.scannerView);
        codeScanner = new CodeScanner(this, scannerView);
        resultText = findViewById(R.id.qr_result);
        registerButton = (Button) findViewById(R.id.register);
        registerButton.setVisibility(View.GONE);

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        resultText.setText(result.getText());
                        if(resultText.getText().toString().equals("BURGER_KING")){
                            openMenuCall();
                        }
                        else{
                            resultText.setText(result.getText() + " Is not Registered" );
                            registerButton.setVisibility(View.VISIBLE);
                            registerButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    openCreateRestaurant();
                                }
                            });
                        }

                    }
                });
            }
        });

        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeScanner.startPreview();
            }
        });



        }
        @Override
        protected void onResume(){
        super.onResume();
        requestCamera();

        }

        private void requestCamera() {
            Dexter.withActivity(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
                @Override
                public void onPermissionGranted(PermissionGrantedResponse response) {
                    codeScanner.startPreview();
                }

                @Override
                public void onPermissionDenied(PermissionDeniedResponse response) {
                    Toast.makeText(MainActivity.this,"Access to Camera is Required", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                    token.continuePermissionRequest();
                }
            }).check();
        }

        public void openMenuCall(){
            TextView resultName = findViewById(R.id.qr_result);
            String key = resultName.getText().toString();
            Intent intent = new Intent(this, MenuCall.class);
            intent.putExtra("SENT_TEXT",key);
            startActivity(intent);
        }

    public void openCreateRestaurant(){
        Intent intent = new Intent(this, CreateRestaurant.class);
        startActivity(intent);
    }
    }

