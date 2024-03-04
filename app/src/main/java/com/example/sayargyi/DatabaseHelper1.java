package com.example.sayargyi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper1 extends SQLiteOpenHelper {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRICE = "price";
    private static final String DATABASE_NAME = "sayar_gyi_updated";
    public static final String COLUMN_QUANTITY = "quantity";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "food_items";
    public static final String TABLE_CART = "cart";


    public DatabaseHelper1(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_PRICE + " REAL"
                + ")";
        String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_CART + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_PRICE + " REAL,"
                + COLUMN_QUANTITY + " INTEGER,"
                + "total_value REAL"  // Add this column
                + ")";
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_CART_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }


    public void addFoodItem(FoodItem foodItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, foodItem.getName());
        values.put(COLUMN_DESCRIPTION, foodItem.getDescription());
        values.put(COLUMN_PRICE, foodItem.getPrice());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    // Method to delete a food item by its ID
    // Method to delete a food item by its ID
    public boolean deleteFoodItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        int deletedRows = db.delete(TABLE_NAME, selection, selectionArgs);
        db.close();
        return deletedRows > 0;
    }

    // Method to update food item information
    public boolean updateFoodItem(int id, FoodItem updatedFoodItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, updatedFoodItem.getName());
        values.put(COLUMN_DESCRIPTION, updatedFoodItem.getDescription());
        values.put(COLUMN_PRICE, updatedFoodItem.getPrice());

        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        int updatedRows = db.update(TABLE_NAME, values, selection, selectionArgs);
        db.close();

        return updatedRows > 0;
    }

    public void deleteAllFoodItems() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }





    public void addItemToCart(FoodItem foodItem, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, foodItem.getName());
        values.put(COLUMN_PRICE, foodItem.getPrice());
        values.put(COLUMN_QUANTITY, quantity);

        double totalValue = foodItem.getPrice() * quantity; // Calculate total value
        values.put("total_value", totalValue);

        db.insert(TABLE_CART, null, values);
        db.close();
    }

    public List<FoodItem> getAllItemsInCart() {
        List<FoodItem> itemList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CART;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                FoodItem item = new FoodItem();
                item.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                item.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                item.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE)));
                item.setQuantity(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)));
                item.setTotalValue(cursor.getDouble(cursor.getColumnIndexOrThrow("total_value")));
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return itemList;
    }

    public FoodItem getFoodItemById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_PRICE}, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        FoodItem item = null;
        if (cursor.moveToFirst()) {
            item = new FoodItem(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE))
            );
        }

        cursor.close();
        db.close();

        return item;
    }

    public boolean updateItemQuantityInCart(int id, int newQuantity, Context context) {
        SQLiteDatabase db = this.getWritableDatabase(); // Use getWritableDatabase() without any parameters
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUANTITY, newQuantity);

        // Recalculate total value based on new quantity
        double itemPrice = getFoodItemById(id).getPrice();
        double totalValue = itemPrice * newQuantity;
        values.put("total_value", totalValue);

        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        int updatedRows = db.update(TABLE_CART, values, selection, selectionArgs);
        return updatedRows > 0;
    }





    public boolean deleteItemFromCart(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        int deletedRows = db.delete(TABLE_CART, selection, selectionArgs);
        db.close();
        return deletedRows > 0;
    }




}
