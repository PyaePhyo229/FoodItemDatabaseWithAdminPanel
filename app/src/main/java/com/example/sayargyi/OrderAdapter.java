package com.example.sayargyi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>
{
    private List<FoodItem> cartItemList;
    private Context context;
    private DatabaseHelper1 databaseHelper;

    public OrderAdapter(Context context, List<FoodItem> cartItemList) {
        this.context = context;
        this.cartItemList = cartItemList;
        this.databaseHelper = new DatabaseHelper1(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_order_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodItem item = cartItemList.get(position);
        holder.textViewCartItemName.setText(item.getName());
        holder.textViewQuantity.setText(String.valueOf(item.getQuantity()));
        //holder.textViewCartItemPrice.setText(String.format("Price: $%.2f", item.getPrice()));

    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Define your views here

        TextView textViewCartItemName, textViewCartItemPrice, textViewQuantity;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewCartItemName = itemView.findViewById(R.id.textViewCartItemName);
            textViewCartItemPrice = itemView.findViewById(R.id.textViewCartItemPrice);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);

        }
    }
}
