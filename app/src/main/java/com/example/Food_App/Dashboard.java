package com.example.Food_App;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Food_App.adapter.AsiaFoodAdapter;
import com.example.Food_App.adapter.PopularFoodAdapter;
import com.example.Food_App.profile.Profile;
import com.example.Food_App.model.AsiaFood;
import com.example.Food_App.model.PopularFood;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();


    RecyclerView popularRecycler, asiaRecycler;
    PopularFoodAdapter popularFoodAdapter;
    AsiaFoodAdapter asiaFoodAdapter;
    FloatingActionButton mapButton;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dahboard);




        mapButton=findViewById(R.id.mapbutton);
        bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Toast.makeText(Dashboard.this,"Already At home",Toast.LENGTH_SHORT).show();
                        // startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        // overridePendingTransition(0,0);
                        return true;
                    case R.id.cart:
                        startActivity(new Intent(getApplicationContext(),CartView.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.help:
                        startActivity(new Intent(getApplicationContext(),Help.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });





        // now here we will add some dummy data to out model class

        List<PopularFood> popularFoodList = new ArrayList<>();


        popularFoodList.add(new PopularFood("Pizza plain", "₹80", R.drawable.pizza,"4.5"));
        popularFoodList.add(new PopularFood("Cheese burger", "₹130", R.drawable.cheeseburger,"2.5"));
        popularFoodList.add(new PopularFood("Hot dog", "₹90", R.drawable.hotdog,"3.2"));
        popularFoodList.add(new PopularFood("Chiken Drumstick", "₹400", R.drawable.popularfood2,"4.9"));
        popularFoodList.add(new PopularFood("Fish Tikka Stick", "$350", R.drawable.popularfood3,"4.8"));
        setPopularRecycler(popularFoodList);


        List<AsiaFood> asiaFoodList = new ArrayList<>();

        asiaFoodList.add(new AsiaFood("Hamburger", "₹250",R.drawable.hamburger, "4.4", "Friends Restaurant"));
        asiaFoodList.add(new AsiaFood("Lemonade", "₹140", R.drawable.lemonade, "3.5", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("Omelette", "₹70", R.drawable.omelette, "4.2", "Veg hut"));
        asiaFoodList.add(new AsiaFood("Pasta", "₹200", R.drawable.pasta, "4.5", "Fivestar Restaurant"));
        asiaFoodList.add(new AsiaFood("Biscuit", "₹150", R.drawable.biscuit, "5.0", "Baba ka Dhaba"));
        asiaFoodList.add(new AsiaFood("Casse role", "₹250", R.drawable.casserole, "4.1", "Amar punjabi"));
        asiaFoodList.add(new AsiaFood("Cheese burger", "₹130", R.drawable.cheeseburger, "2.5", "Punjabi Restaurant"));
        asiaFoodList.add(new AsiaFood("Hot dog", "₹90", R.drawable.hotdog, "3.2", "Shekhawati Restaurant"));
        asiaFoodList.add(new AsiaFood("Pizza plain", "₹80", R.drawable.pizza, "4.5", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("Salad", "₹65", R.drawable.salad, "5.0", "Friends Restaurant"));
        asiaFoodList.add(new AsiaFood("Sandwich", "₹70", R.drawable.sandwich, "5.0", "Friends Restaurant"));
        asiaFoodList.add(new AsiaFood("Shortcake", "₹230", R.drawable.shortcake, "4.3", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("Soup", "₹100", R.drawable.soup, "4.2", "Baba ka Dhaba"));
        asiaFoodList.add(new AsiaFood("Sour cream", "₹400", R.drawable.sourcream, "4.7", "Veg hut"));
        asiaFoodList.add(new AsiaFood("Sponge pudding", "₹300", R.drawable.spongepudding, "4.8", "Friends Restaurant"));
        asiaFoodList.add(new AsiaFood("Chicago Pizza", "₹200", R.drawable.asiafood1, "4.9", "Baba ka Dhaba"));
        asiaFoodList.add(new AsiaFood("Straberry Cake", "₹250", R.drawable.asiafood2, "4.1", "Friends Restaurant"));
        setAsiaRecycler(asiaFoodList);


    }



    private void setPopularRecycler(List<PopularFood> popularFoodList)
    {

        popularRecycler = findViewById(R.id.popular_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        popularRecycler.setLayoutManager(layoutManager);
        popularFoodAdapter = new PopularFoodAdapter(this.getApplicationContext(), popularFoodList);
        popularRecycler.setAdapter(popularFoodAdapter);

    }

    private void setAsiaRecycler(List<AsiaFood> asiaFoodList)
    {

        asiaRecycler = findViewById(R.id.cart_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        asiaRecycler.setLayoutManager(layoutManager);
        asiaFoodAdapter = new AsiaFoodAdapter(this.getApplicationContext(), asiaFoodList);
        asiaRecycler.setAdapter(asiaFoodAdapter);

    }

    public void profileShow(View view)
    {
        Intent intent = new Intent(this,Profile.class);
        startActivity(intent);
    }

    public void openMaps(View view)
    {
        Intent intent= new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:"));
        Intent chooser=Intent.createChooser(intent,"Launch Maps");
        startActivity(chooser);


    }

    public void notification(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notifications");
        builder.setMessage("No new notification");
        builder.setPositiveButton((Html.fromHtml("<font color='#E91E63'>"+"OK"+"</font>")), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(MainActivity.this, "OK was clicked" + which, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton((Html.fromHtml("<font color='#E91E63'>"+"Cancel"+"</font>")), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Dashboard.this,"Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }


    public void searchItems(View view)
    {
        Toast.makeText(Dashboard.this,"Currently not available",Toast.LENGTH_SHORT).show();
    }
































}