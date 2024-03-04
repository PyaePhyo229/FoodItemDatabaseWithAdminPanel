package com.example.sayargyi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder> {

    private List<FoodItem> cartItemList;
    private Context context;
    private DatabaseHelper1  databaseHelper;

    public CartItemAdapter(Context context, List<FoodItem> cartItemList) {
        this.context = context;
        this.cartItemList = cartItemList;
        this.databaseHelper = new DatabaseHelper1(context);
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cart_item, parent, false);
        return new CartItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        FoodItem item = cartItemList.get(position);
        holder.textViewCartItemName.setText(item.getName());
        holder.textViewCartItemPrice.setText(String.format("Price: $%.2f", item.getPrice()));
        holder.textViewQuantity.setText(String.valueOf(item.getQuantity()));

        double totalValue = item.getPrice() * item.getQuantity();
        holder.totalValue.setText(String.format("Total: $%.2f", totalValue));

        // Set onClick listeners for plus and minus buttons
        holder.plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(holder.textViewQuantity.getText().toString());
                quantity++;
                holder.textViewQuantity.setText(String.valueOf(quantity));

                // Update database
                //databaseHelper.updateItemQuantityInCart(databaseHelper.getWritableDatabase(), item.getId(), quantity);
            }
        });

        holder.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(holder.textViewQuantity.getText().toString());
                if (quantity > 0) {
                    quantity--;
                    holder.textViewQuantity.setText(String.valueOf(quantity));

                    // Update database
                    // databaseHelper.updateItemQuantityInCart(databaseHelper.getWritableDatabase(), item.getId(), quantity);
                }
            }
        });

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && !cartItemList.isEmpty()) {
                    // Remove the item from the list
                    FoodItem deletedItem = cartItemList.get(position); // Move this line here
                    cartItemList.remove(position);
                    // Notify adapter about the item removal
                    notifyItemRemoved(position);

                    // Update database
                    databaseHelper.deleteItemFromCart(deletedItem.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public static class CartItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCartItemName, textViewCartItemPrice, textViewQuantity, totalValue;
        Button plusBtn, minusBtn;
        ImageView deleteBtn;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCartItemName = itemView.findViewById(R.id.textViewCartItemName);
            textViewCartItemPrice = itemView.findViewById(R.id.textViewCartItemPrice);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
            totalValue = itemView.findViewById(R.id.totalPrice);
            plusBtn = itemView.findViewById(R.id.buttonPlus);
            minusBtn = itemView.findViewById(R.id.buttonMinus);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);



            // Plus button click listener

        }
    }
}