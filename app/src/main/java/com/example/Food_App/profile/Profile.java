package com.example.Food_App.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.Food_App.R;
import com.example.Food_App.databinding.ActivityProfileBinding;
import com.example.Food_App.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;


public class Profile extends AppCompatActivity {

    ActivityProfileBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    Uri selectedImage;
    DatabaseReference mDatabase;
    TextView tvName, editProfile, tvPhone, cstId;
    ImageView profile;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tvName = (TextView) findViewById(R.id.tv_name);
        tvPhone = (TextView) findViewById(R.id.phone);
        profile = (ImageView) findViewById(R.id.profile);
        cstId = (TextView) findViewById(R.id.cstId);
        progressBar = (ProgressBar) findViewById(R.id.pB);
        progressBar.setVisibility(View.VISIBLE);

        //tvName.setText((CharSequence) getSharedPreferences("Username",0 ));

        editProfile = (TextView) findViewById(R.id.editProfile);
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        mDatabase = database.getInstance().getReference().child("users");


        //String uid = auth.getUid();

        DatabaseReference userr = mDatabase.child(auth.getUid());

        userr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                User value = snapshot.getValue(User.class);
                tvName.setText(value.getName());
                tvPhone.setText(value.getPhoneNumber());
                cstId.setText(value.getUid());
                Glide.with(Profile.this).load(value.getProfileImage()).into(profile);
                //tvName.setText(snapshot.child("name").getValue().toString());

            }



            @Override
            public void onCancelled(DatabaseError error) {

                Toast.makeText(Profile.this, "Failed to read value:: " + error.toException(), Toast.LENGTH_SHORT).show();
            }

        });

        progressBar.setVisibility(View.VISIBLE);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, SetupProfileActivity.class);
                startActivity(intent);
            }
        });


    }
}