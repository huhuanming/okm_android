package com.okm_android.main.Fragment;

import android.app.ActionBar;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.amap.api.location.AMapLocation;
import com.okm_android.main.Adapter.FoodMenuAdapter;
import com.okm_android.main.ApiManager.MainApiManager;
import com.okm_android.main.ApiManager.MerchantsApiManager;
import com.okm_android.main.ApiManager.QinApiInterface;
import com.okm_android.main.ApiManager.QinApiManager;
import com.okm_android.main.Model.FoodDataResolve;
import com.okm_android.main.Model.RestaurantBackData;
import com.okm_android.main.Model.RestaurantMenu;
import com.okm_android.main.R;
import com.okm_android.main.Utils.AddObserver.NotificationCenter;
import com.okm_android.main.Utils.Constant;
import com.okm_android.main.Utils.ErrorUtils;
import com.okm_android.main.Utils.ToastUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit.RestAdapter;
import rx.android.concurrency.AndroidSchedulers;
import rx.util.functions.Action1;

/**
 * Created by QYM on 14-10-9.
 */
public class FoodMenuFragment extends Fragment{
    private View parentView;
    public FoodMenuAdapter foodMenuAdapter;
    public String rid;
    private Handler handler;
    private List<RestaurantMenu> restaurantMenus = new ArrayList<RestaurantMenu>();
    ListView listView;
    List<Map<String,String>> list = new ArrayList<Map<String, String>>();
    FoodDataResolve foodDataResolve;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_food_menu, container, false);
        getActivity().invalidateOptionsMenu();
        rid= getActivity().getIntent().getStringExtra("Restaurant_id");

        getActivity().getActionBar().setDisplayShowTitleEnabled(true);
        getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        listView = (ListView)parentView.findViewById(R.id.food_menu_listView);
        restaurantData();

        handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub

                switch(msg.what)
                {
                    //获取成功
                    case Constant.MSG_SUCCESS:
                        if(msg.obj != null)
                        {
                            restaurantMenus.clear();
                            restaurantMenus.addAll((List<RestaurantMenu>) msg.obj);
                            foodDataResolve=new FoodDataResolve(restaurantMenus);
                            list=getSearch();//数据加载过程
                            foodMenuAdapter = new FoodMenuAdapter(getActivity(),list,foodDataResolve.getTypeLong());
                            listView.setAdapter(foodMenuAdapter);
                            foodMenuAdapter.notifyDataSetChanged();
                        }
                        break;
                }
                super.handleMessage(msg);
            }

        };

        return parentView;
    }
    private ArrayList<Map<String,String>> getSearch()
    {
        ArrayList<Map<String,String>> arrayList=new ArrayList<Map<String,String>>();
        Map<String,String> mapMenu;
        Map<String,String> mapFood;
        int i,j=0;
        int k=0,isMenu=0;
        for(i=0;i < foodDataResolve.getTypeNameCount(); i++)
        {
            for(int m=0;m < foodDataResolve.getTypeLong().size();m++){
                if(foodDataResolve.getTypeLong().get(m)==i)
                {
                    isMenu=1;
                    break;
                }
            }
            if(isMenu==1)
            {
                mapMenu = new HashMap<String, String>();
                mapMenu.put("menuName", foodDataResolve.getTypeName().get(k).toString());
                arrayList.add(mapMenu);
                k++;
                isMenu=0;
            }
            else{
                mapFood=new HashMap<String, String>();
                mapFood.put("foodName", foodDataResolve.getFoods().get(j).food_name);
                mapFood.put("foodPrice", foodDataResolve.getFoods().get(j).food_price);
                mapFood.put("monthSale", foodDataResolve.getFoods().get(j++).sold_number);
                arrayList.add(mapFood);
            }
        }
        return arrayList;
    }

    public void restaurantData()
    {
        getRestaurantDta(rid, new MainApiManager.FialedInterface() {
            @Override
            public void onSuccess(Object object) {
                // 获取一个Message对象，设置what为1
                Message msg = Message.obtain();
                msg.obj = object;
                msg.what = Constant.MSG_SUCCESS;
                // 发送这个消息到消息队列中
                handler.sendMessage(msg);
            }

            @Override
            public void onFailth(int code) {
                ErrorUtils.setError(code, getActivity());
//                progressbar.setVisibility(View.GONE);
            }

            @Override
            public void onOtherFaith() {
//                progressbar.setVisibility(View.GONE);
                ToastUtils.setToast(getActivity(), "发生错误");
            }

            @Override
            public void onNetworkError() {
//                progressbar.setVisibility(View.GONE);
                ToastUtils.setToast(getActivity(), "网络错误");
            }
        });
    }

    private void getRestaurantDta(String restaurant_id, final MainApiManager.FialedInterface fialedInterface)
    {
        QinApiManager.RestaurantFood(restaurant_id).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<RestaurantMenu>>() {
                    @Override
                    public void call(List<RestaurantMenu> restaurantMenus) {
                        fialedInterface.onSuccess(restaurantMenus);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                        if(throwable.getClass().getName().toString().indexOf("RetrofitError") != -1) {
                            retrofit.RetrofitError e = (retrofit.RetrofitError) throwable;
                            if(e.isNetworkError())
                            {
                                fialedInterface.onNetworkError();

                            }
                            else {
                                fialedInterface.onFailth(e.getResponse().getStatus());
                            }
                        }
                        else{
                            fialedInterface.onOtherFaith();
                        }
                    }
                });
    }
}


