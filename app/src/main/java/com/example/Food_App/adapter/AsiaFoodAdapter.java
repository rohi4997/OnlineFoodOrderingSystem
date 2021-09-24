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
import com.example.Food_App.DetailsActivity2;
import com.example.Food_App.R;
import com.example.Food_App.model.AsiaFood;


import java.util.List;


public class AsiaFoodAdapter extends RecyclerView.Adapter<AsiaFoodAdapter.AsiaFoodViewHolder> {

    Context context;
    List<AsiaFood> asiaFoodList;



    public AsiaFoodAdapter(Context context, List<AsiaFood> asiaFoodList) {
        this.context = context;
        this.asiaFoodList = asiaFoodList;
    }

    @NonNull
    @Override
    public AsiaFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.asia_food_row_item, parent, false);
        return new AsiaFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder( AsiaFoodViewHolder holder, int position) {

        holder.foodImage.setImageResource(asiaFoodList.get(position).getImageUrl());
        holder.name.setText(asiaFoodList.get(position).getName());
        holder.price.setText(asiaFoodList.get(position).getPrice());
        holder.rating.setText(asiaFoodList.get(position).getRating());
        holder.restorantName.setText(asiaFoodList.get(position).getRestorantname());

        final AsiaFood temp=asiaFoodList.get(position);

        holder.foodImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, DetailsActivity2.class);
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

    }

    @Override
    public int getItemCount() {
        return asiaFoodList.size();
    }


    public static final class AsiaFoodViewHolder extends RecyclerView.ViewHolder{


        ImageView foodImage;
        TextView price, name, rating, restorantName;

        public AsiaFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.food_image);
            price = itemView.findViewById(R.id.price);
            name = itemView.findViewById(R.id.name);
            rating = itemView.findViewById(R.id.rating);
            restorantName = itemView.findViewById(R.id.restorant_name);



        }
    }

}
