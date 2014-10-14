package com.okm_android.main.Fragment;

import android.app.ActionBar;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.okm_android.main.ApiManager.ChenApiManager;
import com.okm_android.main.ApiManager.MainApiManager;
import com.okm_android.main.Model.RestaurantDetailsBackData;
import com.okm_android.main.R;
import com.okm_android.main.Utils.AddObserver.NotificationCenter;
import com.okm_android.main.Utils.Constant;
import com.okm_android.main.Utils.ErrorUtils;
import com.okm_android.main.Utils.ToastUtils;

import rx.android.concurrency.AndroidSchedulers;
import rx.util.functions.Action1;

/**
 * Created by QYM on 14-10-9.
 */
public class ShopDetailFragment extends Fragment{
    private View parentView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_shop_detail, container, false);
        Log.e("ssss","ds");
        NotificationCenter.getInstance().addObserver("restaurantDetails", this, "restaurantDetails");
        getActivity().invalidateOptionsMenu();
        getActivity().getActionBar().setDisplayShowTitleEnabled(true);
        getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

        return parentView;
    }

    public void restaurantDetails(String restaurant_id) {
        Log.e("ssss","dsddd"+restaurant_id);
        getRestaurantDetails(restaurant_id, new MainApiManager.FialedInterface() {
            @Override
            public void onSuccess(Object object) {

                // 获取一个Message对象，设置what为1
                Message msg = Message.obtain();
                msg.obj = object;
                msg.what = Constant.MSG_TYPESUCCESS;
                // 发送这个消息到消息队列中
//                handler.sendMessage(msg);
            }

            @Override
            public void onFailth(int code) {
                ErrorUtils.setError(code, getActivity());
            }

            @Override
            public void onOtherFaith() {
                ToastUtils.setToast(getActivity(), "发生错误");
            }

            @Override
            public void onNetworkError() {
                ToastUtils.setToast(getActivity(), "网络错误");
            }
        });
    }

    private void getRestaurantDetails(String restaurant_id, final MainApiManager.FialedInterface fialedInterface) {
        ChenApiManager.RestaurantDetails(restaurant_id).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RestaurantDetailsBackData>() {
                    @Override
                    public void call(RestaurantDetailsBackData restaurantDetailsBackData) {
                        fialedInterface.onSuccess(restaurantDetailsBackData);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                        if (throwable.getClass().getName().toString().indexOf("RetrofitError") != -1) {
                            retrofit.RetrofitError e = (retrofit.RetrofitError) throwable;
                            if (e.isNetworkError()) {
                                fialedInterface.onNetworkError();

                            } else {
                                fialedInterface.onFailth(e.getResponse().getStatus());
                            }
                        } else {
                            fialedInterface.onOtherFaith();
                        }
                    }
                });
    }
}
