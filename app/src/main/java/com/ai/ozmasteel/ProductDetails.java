package com.ai.ozmasteel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductDetails extends AppCompatActivity {

    TextView tv_product_name,tv_product_price,tv_product_desc;
    ImageView product_image;
    FloatingActionButton backbutton,addtofavorite,addtocart;
    String img="",ProductName="",ProductDesc="",ProductPrice="";
    int imglen;
    Database db;

    int favoriteitem=0,productId=0,CatId=0,favoriteitemid=0;

    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    int Qty = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        db=new Database(getApplicationContext());

        tv_product_name=(TextView)findViewById(R.id.tv_product_name);
        tv_product_price=(TextView)findViewById(R.id.tv_product_price);
        tv_product_desc=(TextView)findViewById(R.id.tv_product_desc);

        product_image=(ImageView)findViewById(R.id.product_image);

        backbutton=(FloatingActionButton)findViewById(R.id.backbutton);
        addtocart=(FloatingActionButton)findViewById(R.id.addtocart);
        addtofavorite=(FloatingActionButton)findViewById(R.id.addtofavorite);

        final Bundle bundle=getIntent().getExtras();
        CatId= Integer.parseInt(bundle.getString("CatId"));
        productId= Integer.parseInt(bundle.getString("ProductId"));
        ProductName=bundle.getString("ProductName");
        ProductPrice=bundle.getString("ProductPrice");
        ProductDesc=bundle.getString("ProductDesc");
        img=bundle.getString("ProductImg");

        //Toast.makeText(this, "CatId==>"+CatId, Toast.LENGTH_SHORT).show();

        if (CatId==0)
        {
            int[] images= {
                    R.drawable.product_013,
                    R.drawable.product_014,
                    R.drawable.product_015,
                    R.drawable.product_016,
                    R.drawable.product_005,
                    R.drawable.product_006,
                    R.drawable.product_007,
                    R.drawable.product_008,
                    R.drawable.product_009,
                    R.drawable.product_010,
                    R.drawable.product_011,
                    R.drawable.product_012,
                    R.drawable.product_001,
                    R.drawable.product_002,
                    R.drawable.product_003,
                    R.drawable.product_004,
                    R.drawable.product_017,
                    R.drawable.product_018,
                    R.drawable.product_019,
                    R.drawable.product_020};

            imglen=Integer.parseInt(img);
            product_image.setImageResource(images[imglen]);

        }
        else if (CatId==1)
        {
            int[] images1= {
                    R.drawable.product_013,
                    R.drawable.product_014,
                    R.drawable.product_015,
                    R.drawable.product_016};

            imglen=Integer.parseInt(img);
            product_image.setImageResource(images1[imglen]);

        }
        else if (CatId==2)
        {
            int[] images2= {
                    R.drawable.product_005,
                    R.drawable.product_006,
                    R.drawable.product_007,
                    R.drawable.product_008,
                    R.drawable.product_009,
                    R.drawable.product_010,
                    R.drawable.product_011,
                    R.drawable.product_012};
            imglen=Integer.parseInt(img);

            product_image.setImageResource(images2[imglen]);

        }
        else if (CatId==3)
        {
            int[] images3= {
                    R.drawable.product_001,
                    R.drawable.product_002,
                    R.drawable.product_003,
                    R.drawable.product_004,
                    R.drawable.product_017,
                    R.drawable.product_018,
                    R.drawable.product_019,
                    R.drawable.product_020};
            imglen=Integer.parseInt(img);
            product_image.setImageResource(images3[imglen]);

        }


       /* Bundle bundle=getIntent().getExtras();
        ProductId=bundle.getString("ProductId");
        ProductName=bundle.getString("ProductName");
        ProductPrice=bundle.getString("ProductPrice");
        ProductDesc=bundle.getString("ProductDesc");
        img=bundle.getString("ProductImg");*/
       // //Toast.makeText(this, "img=>"+img, //Toast.LENGTH_SHORT).show();


//        for(int i=0;i<images.length;i++){
//            if(img == images[i]+""){
//                //img_length=images[i] -1;
//                product_image.setImageResource(images[i]);
//            }
//        }
        //imglen = 0;

        tv_product_name.setText("Product Name : "+ProductName+"");
        tv_product_price.setText("Price : "+ProductPrice+"");
        tv_product_desc.setText("Description : "+ProductDesc+"");


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CatId ==0) {
                    Intent intent = new Intent(ProductDetails.this, AllProducts.class);
                    startActivity(intent);
                }else
                {
                    Intent intent = new Intent(ProductDetails.this, ProductByCategory.class);
                    startActivity(intent);
                }
            }
        });

        getfavoriteitem();

        addtofavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getfavoriteitem();
                if (favoriteitemid==0 || productId!=favoriteitem)
                {
                    Toast.makeText(ProductDetails.this, "Item Added into Favorite", Toast.LENGTH_SHORT).show();
                    addtofavorite.setImageResource(R.drawable.fillstar);
                    db.open();
                    db.insert_favorite(productId);
                    db.close();
                }
                else  if (productId==favoriteitem)
                {
                    addtofavorite.setImageResource(R.drawable.star);
                    db.open();
                    db.delete_favorite(productId+"");
                    Toast.makeText(ProductDetails.this, "Item Remove", Toast.LENGTH_SHORT).show();
                    db.close();
                }

            }
        });

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder=new AlertDialog.Builder(ProductDetails.this);
                View view=getLayoutInflater().inflate(R.layout.entity_cartdialog,null);

                ImageView iv_plus_qty,iv_minus_qty;
                TextView tv_product_name_cart,tv_product_price_cart;
                final EditText edt_item_qty;
                Button btn_addtocart,btn_cancel;


                iv_plus_qty=(ImageView)view.findViewById(R.id.iv_plus_qty);
                iv_minus_qty=(ImageView)view.findViewById(R.id.iv_minus_qty);

                tv_product_name_cart=(TextView)view.findViewById(R.id.tv_product_name_cart);
                tv_product_price_cart=(TextView)view.findViewById(R.id.tv_product_price_cart);

                edt_item_qty=(EditText)view.findViewById(R.id.edt_item_qty);

                btn_addtocart=(Button)view.findViewById(R.id.btn_addtocart);
                btn_cancel=(Button)view.findViewById(R.id.btn_cancel);

                tv_product_name_cart.setText("Product Name : "+ProductName);
                tv_product_price_cart.setText("Price : "+ProductPrice);

                iv_plus_qty.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Qty < 1)
                            edt_item_qty.setText("1");
                        else {
                           if (Qty>=1)
                           {
                               Qty++;
                               edt_item_qty.setText(Qty+"");
                           }
                        }
                    }
                });

                iv_minus_qty.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Qty > 1)
                        {
                              Qty--;
                              edt_item_qty.setText(Qty+"");
                        }
                        else
                            edt_item_qty.setText(Qty+"");
                    }
                });

                btn_addtocart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Toast.makeText(ProductDetails.this, "Product Id=>"+productId+"\n Qty==>"+Qty, Toast.LENGTH_SHORT).show();
                        
                        db.open();
                        db.insert_cart(productId,Qty+"");
                        Toast.makeText(ProductDetails.this, "Item Added Into Cart", Toast.LENGTH_SHORT).show();
                        db.close();
                        alertDialog.dismiss();
                    }
                });

                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        //getdatafromcart();
                    }
                });

                builder.setView(view);
                alertDialog=builder.create();
                alertDialog.show();

            }
        });

    }

    public void getfavoriteitem()
    {
        db.open();
        Cursor cursor=db.getAllFavoriteProduct();
        if (cursor.getCount()>0)
        {
           // Toast.makeText(this, "favorite item==>"+cursor.getCount(), Toast.LENGTH_SHORT).show();
            if (cursor.moveToFirst())
            {
                do {

                    favoriteitemid=cursor.getInt(0);
                    favoriteitem=cursor.getInt(1);
                    if (productId==favoriteitem)
                    {
                       // Toast.makeText(ProductDetails.this, "Aleardy Added", Toast.LENGTH_SHORT).show();
                        addtofavorite.setImageResource(R.drawable.fillstar);
                    }
                }while (cursor.moveToNext());
            }



        }
        else {
            Toast.makeText(this, "no favorite item available", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public void getdatafromcart()
    {
        db.open();
        Cursor cur=db.getAllCartData();
        if (cur.getCount()>0)
        {
            if (cur.moveToFirst())
            {
                do {
                    Toast.makeText(this, "Qty=>"+cur.getString(2), Toast.LENGTH_SHORT).show();
                }while (cur.moveToNext());
            }
        }
        db.close();
    }
}
