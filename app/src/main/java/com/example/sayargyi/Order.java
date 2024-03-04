package com.example.sayargyi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class Order extends AppCompatActivity {
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapterAdapter;
    private DatabaseHelper1 databaseHelper1;
    private Button btnDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order2);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseHelper1 = new DatabaseHelper1(this);
        btnDashboard = findViewById(R.id.btnDash);

        btnDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Order.this, AdminDashboardActivity.class);
                startActivity(intent);

            }
        });



        // Retrieve cart items from the database
        List<FoodItem> cartItems = databaseHelper1.getAllItemsInCart();
        orderAdapterAdapter = new OrderAdapter(this, cartItems);
        recyclerView.setAdapter(orderAdapterAdapter);
    }
}