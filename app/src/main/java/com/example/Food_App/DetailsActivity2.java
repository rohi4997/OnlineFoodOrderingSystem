package com.example.Food_App;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Food_App.database.CartItemss;
import com.example.Food_App.database.MyDbHandler;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity2 extends AppCompatActivity {

    ImageView img,img0;
    TextView tv1,tv2,tv3;
    int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatils2);

        MyDbHandler db = new MyDbHandler(DetailsActivity2.this);



        img0=(ImageView)findViewById(R.id.xxx);
        img=(ImageView)findViewById(R.id.imageView8);
        tv1=(TextView)findViewById(R.id.textfoodname);
        tv2=(TextView)findViewById(R.id.pricetext);
        tv3=(TextView)findViewById(R.id.ratingtext);

        img.setImageResource(getIntent().getIntExtra("imgname",0));
        tv1.setText(getIntent().getStringExtra("foodname"));
        tv2.setText(getIntent().getStringExtra("price"));
        tv3.setText(getIntent().getStringExtra("rating"));

        img0.setClickable(true);
        img0.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailsActivity2.this,"Added To Cart Indian Food", Toast.LENGTH_SHORT).show();
                // Creating a contact object for the db
                CartItemss harry = new CartItemss();
                harry.setPrice(getIntent().getStringExtra("price"));
                harry.setName(getIntent().getStringExtra("foodname"));

                // Adding a contact to the db
                db.addItems(harry);


                ArrayList<String> cartitemm = new ArrayList<>();

                // Get all contacts
                List<CartItemss> allItemss = db.getAllCartItems();
                for(CartItemss itemss: allItemss){
                    Log.d("dbharry", "\nId: " + itemss.getId() + "\n" +
                            "Name: " + itemss.getName() + "\n"+
                            "Price: " + itemss.getPrice() + "\n" );
                cartitemm.add(itemss.getName() +" ("+ itemss.getPrice() +")");

                }



                Intent intent = new Intent(DetailsActivity2.this,CartView.class);
//                intent.putExtra("imgname",getIntent().getIntExtra("imgname",0));
//                intent.putExtra("foodname",getIntent().getStringExtra("foodname"));
//                intent.putExtra("price",getIntent().getStringExtra("price"));
//                intent.putExtra("rating",getIntent().getStringExtra("rating"));
//                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


            }
        });

    }

}