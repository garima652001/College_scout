package com.users.College_scout;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;
import com.users.College_scout.R;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    List<FoodModel> list;
    Context context;

    public FoodAdapter(List<FoodModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.added_itemlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        String imgurl = list.get(position).getImgUrl();
        String title = list.get(position).getName();
        String type= list.get(position).getIsveg();
        if (list.get(position).getPriceArray().size()!=0) {
            String size = list.get(position).getPriceArray().get(0).getSize();
            String price = list.get(position).getPriceArray().get(0).getPrice();
            holder.sizes.setText(size);
            holder.price.setText("Rs. "+price);
        }
        String category= list.get(position).getCategory();
        Picasso.get()
                .load(imgurl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.food_img);
        holder.food_name.setText(title);
        holder.category.setText(category);

        if(type.equalsIgnoreCase("true")){
            holder.veg_type.setImageResource(R.drawable.ic_veg);
        }
        else{
            holder.veg_type.setImageResource(R.drawable.ic_nonveg);
        }
        /*holder.rowlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext() , Coursedetail.class);
                intent.putExtra("position",holder.getAdapterPosition());
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView food_img,veg_type;
        TextView food_name,sizes,category,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
          food_img= itemView.findViewById(R.id.foodimg);
          veg_type=itemView.findViewById(R.id.card_type);
          food_name=itemView.findViewById(R.id.foodname);
          sizes=itemView.findViewById(R.id.av_size);
          category=itemView.findViewById(R.id.card_category);
          price=itemView.findViewById(R.id.cardprice);
        }
    }
}