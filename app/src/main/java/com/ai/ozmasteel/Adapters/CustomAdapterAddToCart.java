package com.ai.ozmasteel.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ai.ozmasteel.Database;
import com.ai.ozmasteel.POJOs.AllProductsPOJO;
import com.ai.ozmasteel.POJOs.POJOProductAddToCart;
import com.ai.ozmasteel.R;

import java.util.ArrayList;

public class CustomAdapterAddToCart extends BaseAdapter {

    Context context;
    ArrayList<AllProductsPOJO> arrayList_AllProducts;
    ArrayList<POJOProductAddToCart> arrayList_PojoProductAddToCarts;
    int start = 1;
    Database db;

    public CustomAdapterAddToCart(Context context, ArrayList<AllProductsPOJO> arrayList_AllProducts) {
        this.context = context;
        this.arrayList_AllProducts = arrayList_AllProducts;
    }

    @Override
    public int getCount() {
        return arrayList_AllProducts.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList_AllProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.entity_add_to_cart, null);
        }

        db=new Database(context);
        final AllProductsPOJO pjs = arrayList_AllProducts.get(position);
        //final POJOProductAddToCart pojo=arrayList_PojoProductAddToCarts.get(position);

        ImageView iv_img_AC = (ImageView) view.findViewById(R.id.iv_img_AC);
        ImageView iv_plus_AC = (ImageView) view.findViewById(R.id.iv_plus_AC);
        ImageView iv_minus_AC = (ImageView) view.findViewById(R.id.iv_minus_AC);

        final EditText edt_item_AC = (EditText) view.findViewById(R.id.edt_item_AC);

        TextView tv_product_name_AC = (TextView) view.findViewById(R.id.tv_product_name_AC);
        TextView tv_product_price_AC = (TextView) view.findViewById(R.id.tv_product_price_AC);

        tv_product_name_AC.setText(pjs.getProduct_name() + "");
        tv_product_price_AC.setText(pjs.getPrice() + "");
        edt_item_AC.setText(start + "");
        iv_plus_AC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (start < 1) {
                    edt_item_AC.setText("1");
                } else {
                    if (start >= 1) {
                        start++;
                        edt_item_AC.setText(start+"");
                        db.open();
                        db.update_cart(position+"",start+"");
                        db.close();
                    }
                }
            }
        });

        iv_minus_AC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (start > 1 && start != 0 && start != -1) {
                    start--;
                    edt_item_AC.setText(start+"");
                    db.open();
                    db.update_cart(position+"",start+"");
                    db.close();
                }
                else {
                    edt_item_AC.setText("1");
                }
            }
        });

        return view;
    }
}
