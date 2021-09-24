package com.example.Food_App;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SharedPrefLogin extends AppCompatActivity {
    EditText Username_input, Password_input;
    String Username, Password;

    TextView forgotPass;

    //if false then no, if true then yes
    boolean Registered,Loggedin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref_login);


        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Registered = sharedPref.getBoolean("Registered", false);
        Loggedin = sharedPref.getBoolean("Loggedin", false);

        Button RegisterButton = (Button) findViewById(R.id.Register_btn);
        Button Loginbutton = (Button) findViewById(R.id.Login_btn);
        Button ForgotPass = (Button) findViewById(R.id.forget_btn);


        // If the user is registered.
        if (Registered == false) {

            Loginbutton.setEnabled(false);
            RegisterButton.setEnabled(true);
            ForgotPass.setVisibility(View.GONE);


            // If the user is registered already.
        } else {

            Loginbutton.setEnabled(true);
            RegisterButton.setEnabled(false);
            ForgotPass.setVisibility(View.VISIBLE);
        }

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username_input = (EditText) findViewById(R.id.Username_input);
                Password_input = (EditText) findViewById(R.id.Password_input);
                Username = Username_input.getText().toString();
                Password = Password_input.getText().toString();


                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("Registered", true);
                editor.putString("Username", Username);
                editor.putString("Password", Password);
                editor.apply();

                finish();
                startActivity(getIntent());

            }
        });

        Loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username_input = (EditText) findViewById(R.id.Username_input);
                Password_input = (EditText) findViewById(R.id.Password_input);
                Username = Username_input.getText().toString();
                Password = Password_input.getText().toString();
                String Username1 = sharedPref.getString("Username", null);
                String Password1 = sharedPref.getString("Password", null);

                if(Username1.equals(Username) && Password1.equals(Password))
                {
                    Intent intent=new Intent(SharedPrefLogin.this , Dashboard.class);
                    startActivity(intent);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean("Loggedin", true);
                    finish();
                }
                else
                    Toast.makeText(SharedPrefLogin.this,"Not exist",Toast.LENGTH_SHORT).show();
            }
        });

        ForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SharedPrefLogin.this);
                builder.setTitle("Enter Your Username");
                EditText editText = new EditText(SharedPrefLogin.this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
                editText.setLayoutParams(layoutParams);
                builder.setView(editText);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //String Username2 = sharedPref.getString("Username", null);
                        //String Username3 = editText.getText().toString();
                        if(sharedPref.getString("Username", null).equals(editText.getText().toString()))
                        {
                            Toast.makeText(SharedPrefLogin.this, "Password: "+sharedPref.getString("Password", null), Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(SharedPrefLogin.this, "Username does not exist", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                builder.show();

            }
        });

    }
}