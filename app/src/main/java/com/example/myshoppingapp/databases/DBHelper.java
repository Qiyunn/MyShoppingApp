package com.example.myshoppingapp.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myshoppingapp.models.Cart;
import com.example.myshoppingapp.models.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shopdb";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "cart";

    public static final String COlUMN_ID = "id";
    public static final String COlUMN_PRODUCT_NAME = "name";
    public static final String COlUMN_PRODUCT_PRICE = "price";
    public static final String COlUMN_PRODUCT_IMAGE = "image";
    public static final String COlUMN_QUANTITY = "quantity";

    Context context;
    SQLiteDatabase database;


    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUERY = "create table if not exists " + TABLE_NAME + " (" +
                COlUMN_ID + " integer primary key autoincrement," +
                COlUMN_PRODUCT_NAME + " varchar, " +
                COlUMN_PRODUCT_PRICE + " varchar, " +
                COlUMN_PRODUCT_IMAGE + " varchar," +
                COlUMN_QUANTITY + " integer )";
        try {
            db.execSQL(CREATE_QUERY);
        } catch (android.database.SQLException e) {
            Log.i("hi", e.getMessage());
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_QUERY = "drop table " + TABLE_NAME;
        db.execSQL(DROP_QUERY);
        onCreate(db);
    }


    public boolean isProductAlreadyInCart(Product product) {
        String query="select * from "+TABLE_NAME+" where "+COlUMN_PRODUCT_NAME+"=?";
        Cursor cursor = database.rawQuery(query,new String[]{product.getName()});
        int count=cursor.getCount();
        if(count==0){
            return false;
        }else{
            return true;
        }

//        ArrayList<Cart> arrayListCart = selectFromCart();
//        for (int i = 0; i < arrayListCart.size(); i++ ) {
//            if (arrayListCart.get(i).getProductName().equals(product.getName())) {
//                return true;
//            }
//        }
//        return false;
    }

    public void addToCart(Product product) {
        if(isProductAlreadyInCart(product)){
            updateQuantity(product);
        }else{
        ContentValues values = new ContentValues();
        values.put(COlUMN_PRODUCT_NAME, product.getName());
        values.put(COlUMN_PRODUCT_PRICE, product.getPrice());
        values.put(COlUMN_QUANTITY, 1);
        values.put(COlUMN_PRODUCT_IMAGE, product.getImage());

        database.insert(TABLE_NAME, null, values);

//        Toast.makeText(context, "added to cart", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<Cart> selectFromCart() {

        ArrayList<Cart> arrayList = new ArrayList<>();
        String[] columns = {COlUMN_ID,COlUMN_PRODUCT_IMAGE, COlUMN_PRODUCT_NAME, COlUMN_QUANTITY, COlUMN_PRODUCT_PRICE};
        Cursor cursor = database.query(TABLE_NAME, columns, null, null, null, null, null);
        if(cursor!=null && cursor.moveToFirst()){
            do{

                Cart cart=new Cart();
                cart.setCartItemId(cursor.getInt(cursor.getColumnIndex(COlUMN_ID)));
                cart.setProductImage(cursor.getString(cursor.getColumnIndex(COlUMN_PRODUCT_IMAGE)));
                cart.setProductName(cursor.getString(cursor.getColumnIndex(COlUMN_PRODUCT_NAME)));
                cart.setQuantity(cursor.getInt(cursor.getColumnIndex(COlUMN_QUANTITY)));
                cart.setProductPrice(cursor.getInt(cursor.getColumnIndex(COlUMN_PRODUCT_PRICE)));
                arrayList.add(cart);
            }while(cursor.moveToNext());
            cursor.close();
        }
       return arrayList;
    }


    public void updateQuantity(Product product){
        ArrayList<Cart> arrayListCart=new ArrayList<>();
        arrayListCart=selectFromCart();
        Cart cart=new Cart();

        for(int i=0;i<arrayListCart.size();i++){
            if(arrayListCart.get(i).getProductName().equals(product.getName()));
            cart= arrayListCart.get(i);
        }

        ContentValues values=new ContentValues();
        values.put(COlUMN_QUANTITY,cart.getQuantity()+1);
//        values.put(COlUMN_PRODUCT_PRICE,cart.getProductPrice()+cart.getProductPrice()/cart.getQuantity());

        database.update(TABLE_NAME,values,COlUMN_PRODUCT_NAME+"=?",new String[]{product.getName()});
    }

   public void updateAddQuantity(Cart cart){
        ContentValues values=new ContentValues();
        values.put(COlUMN_QUANTITY,cart.getQuantity()+1);
//        values.put(COlUMN_PRODUCT_PRICE,cart.getProductPrice()+cart.getProductPrice()/cart.getQuantity());
        database.update(TABLE_NAME,values,COlUMN_ID+"=?",new String[]{String.valueOf(cart.getCartItemId())});
   }

    public void updateMinusQuantity(Cart cart){
        ContentValues values=new ContentValues();
        values.put(COlUMN_QUANTITY,cart.getQuantity()-1);
//        values.put(COlUMN_PRODUCT_PRICE,cart.getProductPrice()-cart.getProductPrice()/cart.getQuantity());
        database.update(TABLE_NAME,values,COlUMN_ID+"=?",new String[]{String.valueOf(cart.getCartItemId())});
    }

    public void deleteProduct(Cart cart){
        database.delete(TABLE_NAME,COlUMN_ID+"=?",new String[]{String.valueOf(cart.getCartItemId())});
    }

    public int getAmount(){
        int amount=0;
        ArrayList<Cart> list=selectFromCart();
        for(int i=0;i<list.size();i++){
            amount=amount +list.get(i).getQuantity();
        }
        return amount;
    }

    public int getPrice(){
        int price=0;
        ArrayList<Cart> list=selectFromCart();
        for(int i=0;i<list.size();i++){
            price=price+list.get(i).getQuantity()*list.get(i).getProductPrice();
        }
        return price;
    }



}
