package com.okm_android.main.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.okm_android.main.R;

import java.util.List;
import java.util.Map;

/**
 * Created by QYM on 14-10-8.
 */
public class InstantOrderAdapter extends BaseAdapter {
    private Context context;                        //运行上下文
    private List<Map<String, String>> listItems;    //商品信息集合
    private LayoutInflater layoutInflater;
    public InstantOrderAdapter(Context c,List<Map<String,String>> list)
    {
        context=c;
        layoutInflater = LayoutInflater.from(context);
        listItems=list;
    }
    @Override
    public int getCount() {
        return listItems.size();
    }
    @Override
    public Map<String,String> getItem(int position) {
        return listItems.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView name = null;
        TextView number = null;
        TextView money = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.order_food_item, null);
            //得到条目中的子组件
        }
        name = (TextView) convertView.findViewById(R.id.food_name);
        number = (TextView) convertView.findViewById(R.id.food_count);
        money = (TextView) convertView.findViewById(R.id.money);
        //从list对象中为子组件赋值
        name.setText(listItems.get(position).get("name").toString());
        number.setText(listItems.get(position).get("number").toString());
        money.setText(listItems.get(position).get("money").toString());
        return convertView;
    }
}
