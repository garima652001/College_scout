package com.users.College_scout.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;
import com.users.College_scout.AdditemActivity;
import com.users.College_scout.Order;
import com.users.College_scout.OrdersModel;
import com.users.College_scout.R;

import org.w3c.dom.Text;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    List<Order> list;
    Context context;
    Dialog dialog;

    public OrderAdapter(List<Order> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.orderstatus, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        String name1=list.get(position).getItemName();
        String price1= list.get(position).getPrice().toString();
        String imgurl="https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.eatthis.com%2Fitalian-food-not-in-italy%2F&psig=AOvVaw1wGAPhY3omNDRkfP-5y9dm&ust=1605819318218000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCKibna79jO0CFQAAAAAdAAAAABAJ";
        final String id= list.get(position).get_id();;
        holder.foodname.setText(name1);
        holder.price.setText(price1);
        Picasso.get()
                .load(imgurl)
                .placeholder(R.drawable.spicy)
                .into(holder.imageView);

        holder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          showdialog(id);
            }
        });
    }

    private void showdialog(String id) {
        dialog= new Dialog(context);
        dialog.show();
        update_stats();
    }

    private void update_stats() {
        dialog.setContentView(R.layout.order_statusdialog);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.bg2));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
          ImageView imageView,status;
          TextView foodname,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.foodimg);
            foodname=itemView.findViewById(R.id.foodname);
            price=itemView.findViewById(R.id.itemprice);
            status=itemView.findViewById(R.id.pendingbtn);
        }
    }
}