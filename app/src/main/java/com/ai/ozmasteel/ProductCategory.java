package com.ai.ozmasteel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProductCategory extends AppCompatActivity {

    Button btn_cathandles,btn_catmagnet,btn_catstopper;
    String Categorie="";
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_category);

        db=new Database(getApplicationContext());

        btn_cathandles=(Button)findViewById(R.id.btn_cathandles);
        btn_catmagnet=(Button)findViewById(R.id.btn_catmagnet);
        btn_catstopper=(Button)findViewById(R.id.btn_catstopper);

        btn_cathandles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getCategorie(1);
                Intent intent=new Intent(ProductCategory.this,ProductByCategory.class);
                intent.putExtra("CatId",1);
                startActivity(intent);
                //Toast.makeText(ProductCategory.this, "Cat=>"+Categorie, //Toast.LENGTH_SHORT).show();
            }
        });

        btn_catmagnet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getCategorie(2);
                Intent intent=new Intent(ProductCategory.this,ProductByCategory.class);
                intent.putExtra("CatId",2);
                startActivity(intent);
                //Toast.makeText(ProductCategory.this, "Cat=>"+Categorie, //Toast.LENGTH_SHORT).show();
            }
        });

        btn_catstopper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCategorie(3);
                Intent intent=new Intent(ProductCategory.this,ProductByCategory.class);
                intent.putExtra("CatId",3);
                startActivity(intent);
                ////Toast.makeText(ProductCategory.this, "Cat=>"+Categorie, //Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getCategorie(int id){
        db.open();
        Cursor cursor=db.getCategorieById(id);
        
        if(cursor == null){
            //Toast.makeText(this, "No Dat Found", //Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                Categorie=cursor.getString(1);
            }
        }
        
        db.close();
    }
}
