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
import com.example.Food_App.model.PopularFood;

import java.util.List;


public class PopularFoodAdapter extends RecyclerView.Adapter<PopularFoodAdapter.PopularFoodViewHolder> {

    Context context;
    List<PopularFood> popularFoodList;



    public PopularFoodAdapter(Context context, List<PopularFood> popularFoodList) {
        this.context = context;
        this.popularFoodList = popularFoodList;
    }

    @NonNull
    @Override
    public PopularFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.popular_food_row_item, parent, false);
        return new PopularFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder( PopularFoodViewHolder holder, int position) {

        holder.foodImage.setImageResource(popularFoodList.get(position).getImageUrl());
        holder.name.setText(popularFoodList.get(position).getName());
        holder.price.setText(popularFoodList.get(position).getPrice());

        final PopularFood temp=popularFoodList.get(position);

        holder.foodImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("imgname",temp.getImageUrl());
                intent.putExtra("foodname",temp.getName());
                intent.putExtra("price",temp.getPrice());
                intent.putExtra("rating",temp.getRating());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });










        /*switch(position)
        {
            case 0:
            holder.foodImage.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"item 1",Toast.LENGTH_SHORT).show();
                }


            });
            break;
            case 1:
                holder.foodImage.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                       Intent intent=new Intent(context,DetailsActivity.class);
                       context.startActivity(intent);

                        // Toast.makeText(context,"item 2",Toast.LENGTH_SHORT).show();
                    }


                });
                break;
            case 2:
                holder.foodImage.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context,"item 3",Toast.LENGTH_SHORT).show();
                    }


                });
                break;

            case 3:
                holder.foodImage.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context,"item 4",Toast.LENGTH_SHORT).show();
                    }


                });
                break;

            case 4:
                holder.foodImage.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context,"item 5",Toast.LENGTH_SHORT).show();
                    }


                });
                break;

            case 5:
                holder.foodImage.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context,"item 6",Toast.LENGTH_SHORT).show();
                    }


                });
            break;
        }*/
    }

    @Override
    public int getItemCount() {
        return popularFoodList.size();
    }


    public static final class PopularFoodViewHolder extends RecyclerView.ViewHolder
    {


        ImageView foodImage;
        TextView price, name;

        public PopularFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.food_image);
            price = itemView.findViewById(R.id.price);
            name = itemView.findViewById(R.id.name);



        }
    }

}
