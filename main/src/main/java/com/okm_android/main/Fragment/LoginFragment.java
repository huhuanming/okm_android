package com.okm_android.main.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.okm_android.main.R;

/**
 * Created by chen on 14-9-23.
 */
public class LoginFragment extends Fragment{
    private View parentView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_login, container, false);
        return parentView;
    }
}
