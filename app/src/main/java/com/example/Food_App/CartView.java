package com.example.Food_App;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.Food_App.adapter.CartAdapter;

import com.example.Food_App.database.CartItemss;
import com.example.Food_App.database.MyDbHandler;
import com.example.Food_App.model.CartModel;
import com.google.android.material.snackbar.Snackbar;
import com.example.Food_App.DetailsActivity2;

import java.util.ArrayList;
import java.util.List;


public class CartView extends AppCompatActivity {
    RecyclerView CartRecycler;
    CartAdapter asiaFoodAdapter;
    List<CartItemss> asiaFoodList = new ArrayList<>();
    String name,price,rating;
    Button button;
    MyDbHandler db = new MyDbHandler(CartView.this);


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_view);
        button= (Button)findViewById(R.id.placeorder);




        asiaFoodList=db.getAllCartItems();
        if(asiaFoodList.isEmpty())
        {
            button.setVisibility(View.INVISIBLE);
            Toast.makeText(this,"Your Cart is Empty",Toast.LENGTH_SHORT).show();
        }
//        name=getIntent().getStringExtra("foodname");
//        price=getIntent().getStringExtra("price");
//        rating=getIntent().getStringExtra("rating");
//
//
//
//
//        asiaFoodList.add(new CartModel(name, price, R.drawable.iteem, rating));



        setCartRecycler(asiaFoodList);


    }

    private void setCartRecycler(List<CartItemss> asiaFoodList)
    {

        CartRecycler = findViewById(R.id.cart_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        CartRecycler.setLayoutManager(layoutManager);
        asiaFoodAdapter = new CartAdapter(this.getApplicationContext(), asiaFoodList);
        CartRecycler.setAdapter(asiaFoodAdapter);

    }


    public void placeOrder(View view) {


        db.deleteItem();
        Snackbar.make(view, "Order Placed", Snackbar.LENGTH_LONG).show();
        button.setVisibility(View.INVISIBLE);

        CartRecycler.setVisibility(View.INVISIBLE);
        //asiaFoodList.clear()
    }


    @Override
    protected void onResume() {
        super.onResume();
        //asiaFoodList.add(new CartModel(name, price, R.drawable.iteem, rating));
        //asiaFoodList.add(new CartModel(getIntent().getStringExtra("foodname"), getIntent().getStringExtra("price"), R.drawable.iteem, getIntent().getStringExtra("rating")));

    }
}