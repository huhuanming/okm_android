package com.okm_android.main.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.okm_android.main.R;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by chen on 14-9-23.
 */
public class LoginFragment extends Fragment{
    private View parentView;
    private RelativeLayout sinaLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_login, container, false);

        initSina();


        return parentView;
    }

    private void initSina(){
        sinaLayout = (RelativeLayout)parentView.findViewById(R.id.fragment_login_sinalayout);

        sinaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareSDK.initSDK(getActivity(),"3361c24ca69f");
                Platform[] sina = ShareSDK.getPlatformList(getActivity());
//                sina[0].SSOSetting(true);
                sina[0].setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> stringObjectHashMap) {
                        Log.e("ssss", "platformok" + stringObjectHashMap.toString());
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        Log.e("ssss","throwable"+throwable.toString());
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        Log.e("ssss", "platformdfdfdffd");
                    }
                });
                if (sina[0].isValid()) {
                    sina[0].removeAccount();
//
//                    return;
                }


                sina[0].showUser(null);
            }
        });

    }
}
