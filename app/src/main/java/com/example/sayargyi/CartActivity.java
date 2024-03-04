package com.example.sayargyi;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartItemAdapter cartItemAdapter;
    private DatabaseHelper1 databaseHelper1;
    private TextView button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_layout);

        recyclerView = findViewById(R.id.recyclerViewCartItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseHelper1 = new DatabaseHelper1(this);

        button = findViewById(R.id.textView11);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartActivity.this, "Order Placed", Toast.LENGTH_SHORT).show();
            }
        });



        // Retrieve cart items from the database
        List<FoodItem> cartItems = databaseHelper1.getAllItemsInCart();
        double totalPrice = 0.0;
        for (FoodItem item : cartItems) {
            totalPrice += item.getTotalValue();
        }

        // Set up the RecyclerView adapter
        cartItemAdapter = new CartItemAdapter(this, cartItems);
        recyclerView.setAdapter(cartItemAdapter);
        TextView totalPriceTextView = findViewById(R.id.cartText6);
        totalPriceTextView.setText("$" + String.format("%.2f", totalPrice));
    }


}