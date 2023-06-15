package com.ai.ozmasteel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ai.ozmasteel.Adapters.CustomerAdapterProductsByCategory;
import com.ai.ozmasteel.POJOs.AllProductsPOJO;

import java.util.ArrayList;

public class ProductByCategory extends AppCompatActivity {

    GridView gv_product;
    ArrayList<AllProductsPOJO> arrayList;
    CustomerAdapterProductsByCategory customerAdapterProductsByCategory;
    Database db;
    int catId=0;

    AllProductsPOJO allProductsPOJO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_by_category);

        gv_product = (GridView) findViewById(R.id.gv_product);
        db=new Database(getApplicationContext());
        db.open();
        db.close();

        Bundle bundle=getIntent().getExtras();
        catId=bundle.getInt("CatId");

        try{
            getProductByCategorie(catId);
        }catch (Exception e){
            //////Toast.makeText(this, "Error=>"+e.toString(), //////Toast.LENGTH_SHORT).show();
        }

        gv_product.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                allProductsPOJO=arrayList.get(position);
                Intent intent=new Intent(ProductByCategory.this,ProductDetails.class);
                intent.putExtra("CatId",allProductsPOJO.getCat_id());
                intent.putExtra("ProductId",allProductsPOJO.getProduct_id()+"");
                intent.putExtra("ProductName",allProductsPOJO.getProduct_name()+"");
                intent.putExtra("ProductPrice",allProductsPOJO.getPrice()+"");
                intent.putExtra("ProductDesc",allProductsPOJO.getDescription()+"");
                intent.putExtra("ProductImg",position+"");
                startActivity(intent);
            }
        });

    }
    public void getProductByCategorie(int id){
        int pos=0;
        arrayList=new ArrayList<AllProductsPOJO>();
        db.open();
        Cursor cur=db.getProductByCatId(id+"");
        if (cur.getCount()>0) {
            if (cur.moveToFirst()) {
                do {

                    allProductsPOJO=new AllProductsPOJO(cur.getString(0),
                            cur.getString(1),
                            cur.getString(2),
                            cur.getString(3),
                            cur.getString(4),
                            cur.getString(5),
                            cur.getString(6),
                            cur.getString(7));

                    arrayList.add(allProductsPOJO);
                    //Toast.makeText(this, "Total No of Records==>" + cur.getCount(), //////Toast.LENGTH_SHORT).show();
                } while (cur.moveToNext());
            }
        }
        int[] images= {R.drawable.product_001,
                R.drawable.product_002,
                R.drawable.product_003,
                R.drawable.product_004,
                R.drawable.product_005,
                R.drawable.product_006,
                R.drawable.product_007,
                R.drawable.product_008,
                R.drawable.product_009,
                R.drawable.product_010,
                R.drawable.product_011,
                R.drawable.product_012,
                R.drawable.product_013,
                R.drawable.product_014,
                R.drawable.product_015,
                R.drawable.product_016,
                R.drawable.product_017,
                R.drawable.product_018,
                R.drawable.product_019,
                R.drawable.product_020};

        customerAdapterProductsByCategory=new CustomerAdapterProductsByCategory(getApplicationContext(),arrayList,images);
        gv_product.setAdapter(customerAdapterProductsByCategory);

        db.close();
    }
}

