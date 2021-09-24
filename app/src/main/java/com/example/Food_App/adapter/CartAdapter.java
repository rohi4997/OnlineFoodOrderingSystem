package com.example.Food_App.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Food_App.DetailsActivity;
import com.example.Food_App.R;
import com.example.Food_App.database.CartItemss;
import com.example.Food_App.model.CartModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    List<CartItemss> cartList;

    public CartAdapter(Context context, List<CartItemss> cartList){
        this.context=context;
        this.cartList=cartList;
    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_row_item, parent, false);
        return new CartAdapter.CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        //holder.foodImage.setImageResource(cartList.get(position).getImageUrl());
        holder.name.setText(cartList.get(position).getName());
        holder.price.setText(cartList.get(position).getPrice());
        //holder.rating.setText(cartList.get(position).getRating());


        final CartItemss temp=cartList.get(position);

        /*holder.foodImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("imgname",temp.getImageUrl());
                intent.putExtra("foodname",temp.getName());
                intent.putExtra("price",temp.getPrice());
                intent.putExtra("rating",temp.getRating());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("imgname",temp.getImageUrl());
                intent.putExtra("foodname",temp.getName());
                intent.putExtra("price",temp.getPrice());
                intent.putExtra("rating",temp.getRating());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
*/
    }



    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static final class CartViewHolder extends RecyclerView.ViewHolder{


        ImageView foodImage;
        TextView price, name, rating, restorantName;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.food_image);
            price = itemView.findViewById(R.id.price);
            name = itemView.findViewById(R.id.name);
            rating = itemView.findViewById(R.id.rating);
            restorantName = itemView.findViewById(R.id.restorant_name);

        }
    }


}
