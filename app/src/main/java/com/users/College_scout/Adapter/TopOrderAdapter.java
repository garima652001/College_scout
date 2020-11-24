package com.users.College_scout.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;
import com.users.College_scout.R;
import com.users.College_scout.TodaysModel;

import java.util.List;

public class TopOrderAdapter extends RecyclerView.Adapter<TopOrderAdapter.ViewHolder> {
    List<TodaysModel> list;
    Context context;

    public TopOrderAdapter(List<TodaysModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.statslayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
       String name1=list.get(position).getName().toString();
       String price1= list.get(position).getTotal().toString();
       String qty1= list.get(position).getItemSold().toString();
       String imgurl="https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.eatthis.com%2Fitalian-food-not-in-italy%2F&psig=AOvVaw1wGAPhY3omNDRkfP-5y9dm&ust=1605819318218000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCKibna79jO0CFQAAAAAdAAAAABAJ";
       holder.name.setText(name1);
       holder.price.setText(price1);
       holder.qty.setText(qty1);
        Picasso.get()
                .load(imgurl)
                .placeholder(R.drawable.spicy)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView price,qty,name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.foodimg);
            name=itemView.findViewById(R.id.foodname);
            price=itemView.findViewById(R.id.tv_price);
            qty=itemView.findViewById(R.id.tv_qty);
        }
    }
}