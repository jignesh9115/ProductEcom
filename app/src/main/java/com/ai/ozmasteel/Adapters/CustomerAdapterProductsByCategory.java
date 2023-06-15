package com.ai.ozmasteel.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.ai.ozmasteel.POJOs.AllProductsPOJO;
import com.ai.ozmasteel.R;

import java.util.ArrayList;

public class CustomerAdapterProductsByCategory extends BaseAdapter {

    Context context;
    ArrayList<AllProductsPOJO> arrayList_AllProductsPOJOS;
    int[] images;

    public CustomerAdapterProductsByCategory(Context context, ArrayList<AllProductsPOJO> arrayList_AllProductsPOJOS,int[] images) {
        this.context = context;
        this.arrayList_AllProductsPOJOS = arrayList_AllProductsPOJOS;
        this.images = images;
    }

    @Override
    public int getCount() {
        return arrayList_AllProductsPOJOS.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList_AllProductsPOJOS.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=convertView;

        if(view==null){
            LayoutInflater inflater=LayoutInflater.from(context);
            view=inflater.inflate(R.layout.entity_product_details,null);
        }

        AllProductsPOJO pjs=arrayList_AllProductsPOJOS.get(position);

        CardView product_card;
        ImageView  product_image;
        TextView tv_product_name,tv_product_price,tv_product_desc;

        product_card=view.findViewById(R.id.product_card);
        product_image=view.findViewById(R.id.product_image);
        tv_product_name=view.findViewById(R.id.tv_product_name);
        tv_product_price=view.findViewById(R.id.tv_product_price);
        tv_product_desc=view.findViewById(R.id.tv_product_desc);

        int[] images= {R.drawable.product_001,
                R.drawable.product_001,
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
        int img_pos=Integer.parseInt(pjs.getProduct_id());
        product_image.setImageResource(images[img_pos]);


        tv_product_name.setText(pjs.getProduct_name()+"");
        tv_product_price.setText(pjs.getPrice()+"");
        tv_product_desc.setText(pjs.getCat_name()+"");



        return view;
    }
}
