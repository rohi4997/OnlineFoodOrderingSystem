package com.example.Food_App;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Otp_Page extends AppCompatActivity
{
    Button login;
    Button cancel;
    TextView resend;
    EditText editTextNumber1;
    EditText editTextNumber2;
    EditText editTextNumber3;
    EditText editTextNumber4;
    EditText editTextNumber5;
    EditText editTextNumber6;
    String getotpbackend;
    ProgressBar progressbar;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_page);

        editTextNumber1 = findViewById(R.id.editTextnumber1);
        editTextNumber2 = findViewById(R.id.editTextnumber2);
        editTextNumber3 = findViewById(R.id.editTextnumber3);
        editTextNumber4 = findViewById(R.id.editTextnumber4);
        editTextNumber5 = findViewById(R.id.editTextnumber5);
        editTextNumber6 = findViewById(R.id.editTextnumber6);
        login = (Button) findViewById(R.id.login);
        cancel =(Button) findViewById(R.id.cancel);

        getotpbackend = getIntent().getStringExtra("backendotp");
        progressbar = findViewById(R.id.progressbar);
        resend = findViewById(R.id.resend);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextNumber1.getText().toString().trim().isEmpty() && !editTextNumber2.getText().toString().trim().isEmpty() &&
                        !editTextNumber3.getText().toString().trim().isEmpty() && !editTextNumber4.getText().toString().trim().isEmpty() &&
                        !editTextNumber4.getText().toString().trim().isEmpty() && !editTextNumber6.getText().toString().trim().isEmpty()) {

                    String entercodeotp = editTextNumber1.getText().toString() + editTextNumber2.getText().toString() +
                            editTextNumber3.getText().toString() + editTextNumber4.getText().toString() +
                            editTextNumber5.getText().toString() + editTextNumber6.getText().toString();

                    if(getotpbackend != null){
                        progressbar.setVisibility(View.VISIBLE);
                        login.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(getotpbackend,entercodeotp);

                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).
                                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        /*progressbar.setVisibility(View.GONE);
                                        login.setVisibility(View.INVISIBLE);*/

                                        if(task.isSuccessful()){
                                            progressbar.setVisibility(View.GONE);
                                            login.setVisibility(View.INVISIBLE);
                                            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        }else{progressbar.setVisibility(View.GONE);
                                            login.setVisibility(View.VISIBLE);
                                            Toast.makeText(Otp_Page.this, "Enter the correct Otp", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }else{
                        Toast.makeText(Otp_Page.this, "Connection lost..", Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText(Otp_Page.this, "otp verify", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Otp_Page.this, "please enter all number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Otp_Page.this);

                builder.setMessage("Are you sure you want to Exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                Otp_Page.super.onBackPressed();
                                //finish();
                                //System.exit(0);
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
        });

        movecursor();

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(getIntent().getStringExtra("mobile"),5, TimeUnit.SECONDS, Otp_Page.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(Otp_Page.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(String newbackendotp, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                getotpbackend = newbackendotp;
                                Toast.makeText(Otp_Page.this, "Otp send succesfull", Toast.LENGTH_SHORT).show();

                            }
                        }
                );
            }
        });
    }

    //Oncreate function  ended

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Otp_Page.super.onBackPressed();
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

    private void movecursor() {
        editTextNumber1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    editTextNumber2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextNumber2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    editTextNumber3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextNumber3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    editTextNumber4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextNumber4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    editTextNumber5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextNumber5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    editTextNumber6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

}
