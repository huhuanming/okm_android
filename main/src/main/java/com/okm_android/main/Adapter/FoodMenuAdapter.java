package com.okm_android.main.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.okm_android.main.R;
import com.okm_android.main.View.ListView.PinnedSectionListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by QYM on 14-10-9.
 */
public class FoodMenuAdapter extends BaseAdapter {
    final int TYPE_food = 2;
    final int VIEW_TYPE = 0;
    final int TYPE_Menu = 1;


    Context mContext;
    LinearLayout linearLayout = null;
    LayoutInflater inflater;
    ArrayList<Map<String,String>> listItems;


    public FoodMenuAdapter(Context context,ArrayList<Map<String,String>> list){
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        listItems=list;
    }
    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public int getItemViewType(int position) {
        if(position==0||position==3||position==8)
        {
            return TYPE_Menu;
        }
        else return TYPE_food;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        viewHolder1 holder1 = null;
        viewHolder2 holder2 = null;
        int type = getItemViewType(position);
        switch(type)
        {
            case TYPE_Menu:
            {
                convertView = inflater.inflate(R.layout.food_group_item, parent, false);
                holder1 = new viewHolder1();
                holder1.menuName = (TextView)convertView.findViewById(R.id.food_group_name);
                Log.e("convertView = ", "NULL TYPE_1");
                convertView.setTag(holder1);
                holder1.menuName.setText(listItems.get(position).get("menuName").toString());
            }break;
            case TYPE_food:
            {
                convertView = inflater.inflate(R.layout.food_menu_item, parent, false);
                holder2 = new viewHolder2();
                holder2.foodName = (TextView) convertView.findViewById(R.id.food_menu_name);
                holder2.foodPrice = (TextView) convertView.findViewById(R.id.food_price);
                holder2.saleCount = (TextView) convertView.findViewById(R.id.show_sale_count);
                convertView.setTag(holder2);
                holder2.foodName.setText(listItems.get(position).get("foodName").toString());
                holder2.foodPrice.setText(listItems.get(position).get("foodPrice").toString());
                holder2.saleCount.setText("月售"+listItems.get(position).get("monthSale").toString()+"份");
            }break;
        }
        return convertView;
    }
    class viewHolder1{
        TextView menuName;
    }
    class viewHolder2{
        TextView foodName;
        TextView foodPrice;
        TextView saleCount;
    }

}
