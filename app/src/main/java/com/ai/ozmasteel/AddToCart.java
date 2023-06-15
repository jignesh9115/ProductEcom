package com.ai.ozmasteel;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import com.ai.ozmasteel.Adapters.CustomAdapterAddToCart;
import com.ai.ozmasteel.POJOs.AllProductsPOJO;
import com.ai.ozmasteel.POJOs.POJOProductAddToCart;
import java.util.ArrayList;

public class AddToCart extends AppCompatActivity {

    ListView lv_product_addToCart;
    ArrayList<AllProductsPOJO> arrayList_AllProductsPOJOS;
    ArrayList<POJOProductAddToCart> arrayList_PojoProductAddToCarts;
    CustomAdapterAddToCart customAdapterAddToCart;
    Database db;
    int ProductId=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        lv_product_addToCart=(ListView)findViewById(R.id.lv_product_addToCart);
        db=new Database(getApplicationContext());

        //Toast.makeText(AddToCart.this, "productId==>"+ProductId, Toast.LENGTH_SHORT).show();
        getCartDate(ProductId);

    }




    public void getCartDate(int productId){
       try {
           arrayList_AllProductsPOJOS=new ArrayList<>();
           //   arrayList_PojoProductAddToCarts=new ArrayList<>();
           db.open();
           Cursor cur=db.getProductById(productId);
           // Cursor cursor=db.getAllCartData();
//        if(cursor.getCount() > 0) {
           if (cur.getCount() > 0) {
               if (cur.moveToFirst()) {
                   do {
                       arrayList_AllProductsPOJOS.add(new AllProductsPOJO(cur.getString(0),
                               cur.getString(1),
                               cur.getString(2),
                               cur.getString(3),
                               cur.getString(4),
                               cur.getString(5),
                               cur.getString(6),
                               cur.getString(7)));
                   } while (cur.moveToNext());
               }
           }
//        }else {
//            ////Toast.makeText(this, "NoData", //Toast.LENGTH_SHORT).show();
//        }
           customAdapterAddToCart=new CustomAdapterAddToCart(getApplicationContext(),arrayList_AllProductsPOJOS);
           lv_product_addToCart.setAdapter(customAdapterAddToCart);

           db.close();
       }catch (Exception e){
           //Toast.makeText(this, "LoadError=>"+e.toString(), //Toast.LENGTH_SHORT).show();
       }
    }
}
