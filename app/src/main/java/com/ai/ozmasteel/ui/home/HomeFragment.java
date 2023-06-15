package com.ai.ozmasteel.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ai.ozmasteel.AllProducts;
import com.ai.ozmasteel.Database;
import com.ai.ozmasteel.ProductCategory;
import com.ai.ozmasteel.R;

public class HomeFragment extends Fragment{

    private HomeViewModel homeViewModel;

    ViewFlipper viewflipper;

    Button btn_allproduct,btn_product;

    Database db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        viewflipper=(ViewFlipper)root.findViewById(R.id.viewflipper);
        btn_allproduct=(Button)root.findViewById(R.id.btn_allproduct);
        btn_product=(Button)root.findViewById(R.id.btn_product);

        db=new Database(getActivity());
        db.open();
        db.close();

        checkdatabastatus();

       // insertDefaultdataInCategory();
        //insertDefaultdataInProduct();

        int images[]={R.drawable.product_001,
                R.drawable.product_002,
                R.drawable.product_003,
                R.drawable.product_004,
                R.drawable.product_005
        };

        for (int image:images)
        {
            imageflipper(image);
        }

        btn_allproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),AllProducts.class);
                startActivity(intent);

            }
        });

        btn_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ProductCategory.class);
                startActivity(intent);
            }
        });


        return root;
    }

    private void checkdatabastatus() {
        db.open();
        Cursor cur=db.getAllProduct(),cur1=db.getAllCategorie();
        if (cur.getCount()==0||cur1.getCount()==0)
        {
            insertDefaultdataInCategory();
            insertDefaultdataInProduct();
        }
        else
        {
            //Toast.makeText(getActivity(), "Data  Available", //Toast.LENGTH_SHORT).show();
        }

        db.close();
    }


    private void imageflipper(int image) {

        ImageView imageView=new ImageView(getActivity());
        imageView.setBackgroundResource(image);

        viewflipper.addView(imageView);
        viewflipper.setFlipInterval(2000);
        viewflipper.setAutoStart(true);
        viewflipper.setInAnimation(getActivity(),android.R.anim.slide_in_left);
        viewflipper.setOutAnimation(getActivity(),android.R.anim.slide_out_right);
    }

    public void insertDefaultdataInCategory()
    {

        db.open();
        db.insert_categorie("1","Handles");
        db.insert_categorie("2","Magnet");
        db.insert_categorie("3","Stopper");
        db.close();

    }
    public void insertDefaultdataInProduct()
    {
        db.open();
        db.insert_product("1","product_001","3","Stopper","","","150","250");
        db.insert_product("2","product_002","3","Stopper","","","150","300");
        db.insert_product("3","product_003","3","Stopper","","","150","150");
        db.insert_product("4","product_004","3","Stopper","","","150","100");
        db.insert_product("5","product_005","2","Magnet","","","100","350");
        db.insert_product("6","product_006","2","Magnet","","","100","400");
        db.insert_product("7","product_007","2","Magnet","","","100","400");
        db.insert_product("8","product_008","2","Magnet","","","100","450");
        db.insert_product("9","product_009","2","Magnet","","","100","500");
        db.insert_product("10","product_010","2","Magnet","","","100","360");
        db.insert_product("11","product_011","2","Magnet","","","100","430");
        db.insert_product("12","product_012","2","Magnet","","","100","400");
        db.insert_product("13","product_013","1","Handles","","","100","450");
        db.insert_product("14","product_014","1","Handles","","","100","460");
        db.insert_product("15","product_015","1","Handles","","","100","440");
        db.insert_product("16","product_016","1","Handles","","","100","410");
        db.insert_product("17","product_017","3","Stopper","","","100","520");
        db.insert_product("18","product_018","3","Stopper","","","100","550");
        db.insert_product("19","product_019","3","Stopper","","","100","350");
        db.insert_product("20","product_020","3","Stopper","","","100","501");
        db.close();
    }


}
