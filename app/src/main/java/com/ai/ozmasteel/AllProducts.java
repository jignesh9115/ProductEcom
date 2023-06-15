package com.ai.ozmasteel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.ai.ozmasteel.Adapters.CustomerAdapterAllProducts;
import com.ai.ozmasteel.POJOs.AllProductsPOJO;

import java.util.ArrayList;
import java.util.Locale;

public class AllProducts extends AppCompatActivity {

    EditText edt_search;
    GridView gridview;
    Database db;
    AllProductsPOJO allProductsPOJO;

    ArrayList<AllProductsPOJO> arrayList,searcharrayList;
    CustomerAdapterAllProducts CustomerAdapterAllProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);

        edt_search=(EditText)findViewById(R.id.edt_search);
        gridview=(GridView) findViewById(R.id.gridview);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        arrayList=new ArrayList<>();
        searcharrayList=new ArrayList<>();

        db=new Database(getApplicationContext());
        db.open();
        db.close();
        getdatafromdb();

        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String text=s.toString();
                filterproduct(text);
                CustomerAdapterAllProducts.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                allProductsPOJO=arrayList.get(position);
                Intent intent=new Intent(AllProducts.this,ProductDetails.class);
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

    private void filterproduct(String charText) {

        charText=charText.toLowerCase(Locale.getDefault());
        Log.d("filter==",charText);

        arrayList.clear();

        if (charText.length()==0)
        {
            arrayList.addAll(searcharrayList);
        }
        else
        {
            Log.d("load data","filtered");
            for (AllProductsPOJO allProductsPOJO:searcharrayList)
            {
                if (allProductsPOJO.getProduct_name().contains(charText)||
                allProductsPOJO.getPrice().contains(charText))
                {
                    arrayList.add(allProductsPOJO);
                }
                CustomerAdapterAllProducts.notifyDataSetChanged();
            }
        }

    }

    public void getdatafromdb()
    {
        db.open();
        Cursor cur=db.getAllProduct();
        if (cur.getCount()>0)
        {
            if (cur.moveToFirst())
            {
                do {
                    AllProductsPOJO allProductsPOJO=new AllProductsPOJO(cur.getString(0),
                            cur.getString(1),
                            cur.getString(2),
                            cur.getString(3),
                            cur.getString(4),
                            cur.getString(5),
                            cur.getString(6),
                            cur.getString(7));
                    arrayList.add(allProductsPOJO);
                    searcharrayList.add(allProductsPOJO);

                   // //Toast.makeText(this, "Total No of Records==>"+cur.getCount(), //Toast.LENGTH_SHORT).show();
                }while (cur.moveToNext());
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
            CustomerAdapterAllProducts=new CustomerAdapterAllProducts(getApplicationContext(),arrayList,images);
            gridview.setAdapter(CustomerAdapterAllProducts);
        }
        else
        {
            //Toast.makeText(this, "Record Not Found", //Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
}
