package com.ai.ozmasteel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Database {

    Context context;
    DatabaseHelper dbhelper;
    SQLiteDatabase db;

    private static final String DATABASE_NAME="demodb.db";
    private static final int DATABASE_VERSION=1;

    public static final String TABLE_CATEGORIES="categories";
    public static final String CAT_ID="cat_id";
    public static final String CAT_NAME="cat_name";
    public static final String CREATE_TABLE_CATEGORIE_QUERY="CREATE TABLE "+TABLE_CATEGORIES+"("+CAT_ID+" INTEGER PRIMARY KEY, "+CAT_NAME+" TEXT)";

    public static final String TABLE_PRODUCTS="products";
    public static final String PRODUCT_ID="product_id";
    public static final String PRODUCT_NAME="product_name";
    public static final String COMPANY_NAME="company_name";
    public static final String PRODUCT_DESC="description";
    public static final String PRODUCT_QTY="qty";
    public static final String PRODUCT_MRP="mar";
    public static final String CREATE_TABLE_PRODUCT_QUERY="CREATE TABLE "+TABLE_PRODUCTS+"("+PRODUCT_ID+" INTEGER PRIMARY KEY, "+PRODUCT_NAME+" TEXT, "+CAT_ID+" TEXT, "+CAT_NAME+" TEXT, "+COMPANY_NAME+" TEXT, "+PRODUCT_DESC+" TEXT, "+PRODUCT_QTY+" TEXT, "+PRODUCT_MRP+" TEXT)";


    public static final String TABLE_CART="cart";
    public static final String CART_ID="cart_id";
    public static final String CART_QTY="cart_qty";
    public static final String CREATE_TABLE_CART_QUERY="CREATE TABLE "+TABLE_CART+"("+CART_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+PRODUCT_ID+" INTEGER, "+CART_QTY+" TEXT)";

    public static final String TABLE_FAVORITE="favorite";
    public static final String FAV_ID="fav_id";
    public static final String CREATE_TABLE_FAVORITE_QUERY="CREATE TABLE "+TABLE_FAVORITE+"("+FAV_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +PRODUCT_ID+" INTEGER)";


    public Database(Context context) {
        this.context = context;
        dbhelper=new DatabaseHelper(context);
    }

    class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_CATEGORIE_QUERY);
            db.execSQL(CREATE_TABLE_PRODUCT_QUERY);
            db.execSQL(CREATE_TABLE_CART_QUERY);
            db.execSQL(CREATE_TABLE_FAVORITE_QUERY);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_CATEGORIES);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_PRODUCTS);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_CART);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_FAVORITE);
            onCreate(db);
        }
    }

    public Database open() throws SQLException
    {
        db=dbhelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        dbhelper.close();
    }

    public long insert_categorie(String cat_id,String cat_name){
        ContentValues csv=new ContentValues();
        csv.put(this.CAT_ID,cat_id);
        csv.put(this.CAT_NAME,cat_name);
        ////Toast.makeText(context, "Category Inserted", ////Toast.LENGTH_SHORT).show();
        return db.insert(TABLE_CATEGORIES,null,csv);
    }

    public void update_categories(String cat_id,String cat_name){
        ContentValues csv=new ContentValues();
        csv.put(this.CAT_NAME,cat_name);
        db.update(TABLE_CATEGORIES,csv,"cat_id=?",new String[]{cat_id});
    }

    public void delete_categories(String cat_id){
        db.delete(TABLE_CATEGORIES,"cat_id=?",new String[]{cat_id});
    }

    public Cursor getAllCategorie(){
        Cursor cur=db.rawQuery("SELECT * FROM "+TABLE_CATEGORIES,null);
        return cur;
    }


    public Cursor getCategorieById(int id){
        Cursor cur=db.rawQuery("SELECT * FROM "+TABLE_CATEGORIES+" WHERE cat_id="+id,null);
        return cur;
    }
    public long insert_product(String product_id,String product_name,String cat_id,String cat_name,String company_name,String product_desc,String product_qty,String product_mrp){
        ContentValues csv=new ContentValues();
        csv.put(this.PRODUCT_ID,product_id);
        csv.put(this.PRODUCT_NAME,product_name);
        csv.put(this.CAT_ID,cat_id);
        csv.put(this.CAT_NAME,cat_name);
        csv.put(this.COMPANY_NAME,company_name);
        csv.put(this.PRODUCT_DESC,product_desc);
        csv.put(this.PRODUCT_QTY,product_qty);
        csv.put(this.PRODUCT_MRP,product_mrp);
        ////Toast.makeText(context, "Product Inserted", ////Toast.LENGTH_SHORT).show();
        return db.insert(TABLE_PRODUCTS,null,csv);
    }

    public void update_product(String product_id,String product_name,String cat_id,String cat_name,String company_name,String product_desc,String product_qty,String product_mrp){
        ContentValues csv=new ContentValues();
        csv.put(this.PRODUCT_NAME,product_name);
        csv.put(this.CAT_ID,cat_id);
        csv.put(this.CAT_NAME,cat_name);
        csv.put(this.COMPANY_NAME,company_name);
        csv.put(this.PRODUCT_DESC,product_desc);
        csv.put(this.PRODUCT_QTY,product_qty);
        csv.put(this.PRODUCT_MRP,product_mrp);
        db.update(TABLE_PRODUCTS,csv,"product_id=?",new String[]{product_id});
    }

    public void delete_products(String product_id){
        db.delete(TABLE_PRODUCTS,"product_id=?",new String[]{product_id});
    }

    public Cursor getAllProduct(){
        Cursor cur=db.rawQuery("SELECT * FROM "+TABLE_PRODUCTS,null);
        return cur;
    }

    public Cursor getProductByCatId(String id){
        String query="SELECT * FROM "+TABLE_PRODUCTS+" WHERE cat_id="+id;
        Cursor cur=db.rawQuery(query,null);
        return cur;
    }

    public Cursor getProductById(int id){
        String query="SELECT * FROM "+TABLE_PRODUCTS+" WHERE product_id="+id;
        Cursor cur=db.rawQuery(query,null);
        return cur;
    }

    public long insert_cart(int product_id,String cart_qty){
        ContentValues csv=new ContentValues();
        csv.put(this.PRODUCT_ID,product_id);
        csv.put(this.CART_QTY,cart_qty);
        return db.insert(TABLE_CART,null,csv);
    }

    public void update_cart(String cart_id,String cart_qty){
        ContentValues csv=new ContentValues();
       // csv.put(this.PRODUCT_ID,product_id);
        csv.put(this.CART_QTY,cart_qty);
        db.update(TABLE_CART,csv,"cart_id=?",new String[]{cart_id});
    }

    public void delete_cart(String cart_id){
        db.delete(TABLE_CART,"cart_id=?",new String[]{cart_id});
    }

    public Cursor getAllCartData(){
        Cursor cur=db.rawQuery("SELECT * FROM "+TABLE_CART,null);
        return cur;
    }

    public long insert_favorite(int product_id){
        ContentValues csv=new ContentValues();
        csv.put(this.PRODUCT_ID,product_id);
        return db.insert(TABLE_FAVORITE,null,csv);
    }

    public void update_favorite(String fav_id,String product_id){
        ContentValues csv=new ContentValues();
        csv.put(this.PRODUCT_ID,product_id);
        db.update(TABLE_FAVORITE,csv,"fav_id=?",new String[]{fav_id});
    }

    public void delete_favorite(String product_id){
        db.delete(TABLE_FAVORITE,"product_id=?",new String[]{product_id});
        Log.d("remove ",product_id);
    }

    public Cursor getAllFavoriteProduct(){
        Cursor cur=db.rawQuery("SELECT * FROM "+TABLE_FAVORITE,null);
        return cur;
    }
}
