package com.example.Food_App;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginPage extends AppCompatActivity {

    Button getOTP;
    EditText editText;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        getOTP= (Button) findViewById(R.id.getOTP);
        editText = (EditText) findViewById(R.id.editTextPhone);
        progressbar = findViewById(R.id.progressbar);

        getOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this,Otp_Page.class);
                //startActivity(new Intent(MainActivity.this,Otp_Page.class));
                if(!editText.getText().toString().trim().isEmpty()){
                    if((editText.getText().toString().trim()).length() == 10){
                        progressbar.setVisibility(View.VISIBLE);
                        getOTP.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + editText.getText().toString(), 60, TimeUnit.SECONDS, LoginPage.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        progressbar.setVisibility(View.GONE);
                                        getOTP.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressbar.setVisibility(View.GONE);
                                        getOTP.setVisibility(View.VISIBLE);
                                        Toast.makeText(LoginPage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(String backendotp, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        progressbar.setVisibility(View.GONE);
                                        getOTP.setVisibility(View.VISIBLE);
                                        Intent intent = new Intent(LoginPage.this,Otp_Page.class);
                                        intent.putExtra("mobile",editText.getText().toString());
                                        intent.putExtra("backendotp",backendotp);
                                        startActivity(intent);
                                    }
                                }
                        );


                        /*Intent intent = new Intent(MainActivity.this,Otp_Page.class);
                        intent.putExtra("mobile",editText.getText().toString());
                        startActivity(intent);*/
                    }else {
                        Toast.makeText(LoginPage.this, "Please enter correct number", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginPage.this, "Enter mobile number", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Exit the App?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        LoginPage.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}