package com.okm_android.main.Fragment;

import android.app.ActionBar;
import android.content.ClipData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.okm_android.main.Adapter.FoodMenuAdapter;
import com.okm_android.main.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by QYM on 14-10-9.
 */
public class FoodMenuFragment extends Fragment{
    private View parentView;
    FoodMenuAdapter foodMenuAdapter;
    final String[] menuName = new String[]{
        "主食","传统川菜","面"
    };
    final String[][] foodName = new String[][]{
            {"秘制牛肉饭","8","10"},
            {"秘制牛肉饭","8","10"},

            {"秘制牛肉饭","8","10"},
            {"秘制羊肉饭","8","10"},
            {"秘制羊肉饭","8","10"},
            {"秘制羊肉饭","8","10"},

            {"秘制牛肉饭","8","10"},
            {"秘制牛肉饭","8","10"},
            {"秘制牛肉饭","8","10"}
    };

    ListView listView;
    ArrayList<Map<String,String>> list;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_food_menu, container, false);
        getActivity().invalidateOptionsMenu();
        getActivity().getActionBar().setDisplayShowTitleEnabled(true);
        getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

        listView = (ListView)parentView.findViewById(R.id.food_menu_listView);

        list=getSearch();//数据加载过程
        foodMenuAdapter = new FoodMenuAdapter(parentView.getContext(),list);
        listView.setAdapter(foodMenuAdapter);
        return parentView;
    }
    private ArrayList<Map<String,String>> getSearch()
    {
        ArrayList<Map<String,String>> arrayList=new ArrayList<Map<String,String>>();
        Map<String,String> mapMenu;
        Map<String,String> mapFood;
        int i,j=0;
        int k=0;
        for(i=0;i<12;i++)
        {
            if(i==0||i==3||i==8) {
                mapMenu = new HashMap<String, String>();
                mapMenu.put("menuName", menuName[k++]);
                arrayList.add(mapMenu);
            }
            else{
                mapFood=new HashMap<String, String>();
                mapFood.put("foodName", foodName[j][0]);
                mapFood.put("foodPrice", foodName[j][1]);
                mapFood.put("monthSale", foodName[j++][2]);
                arrayList.add(mapFood);
            }
        }
        return arrayList;
    }
}


