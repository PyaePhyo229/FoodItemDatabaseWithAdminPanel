package com.example.sayargyi;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.ViewHolder> {

    private List<FoodItem> foodItemList;
    private OnFoodItemClickListener listener; // Listener for item clicks

    // Interface for item click events
    public interface OnFoodItemClickListener {
        void onFoodItemClick(int itemId);
    }

    // Constructor
    public FoodItemAdapter(List<FoodItem> foodItemList) {
        this.foodItemList = foodItemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodItem foodItem = foodItemList.get(position);

        holder.textViewName.setText(foodItem.getName());
        holder.textViewDescription.setText(foodItem.getDescription());
        holder.textViewPrice.setText(String.format("$%.2f", foodItem.getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the clicked food item
                FoodItem clickedFoodItem = foodItemList.get(holder.getAdapterPosition());
                // Start the details activity and pass necessary data
                Intent intent = new Intent(v.getContext(), FoodDetailsActivity.class);
                intent.putExtra("foodName", clickedFoodItem.getName());
                intent.putExtra("description", clickedFoodItem.getDescription());
                intent.putExtra("price", clickedFoodItem.getPrice());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewDescription, textViewPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
        }
    }
}
