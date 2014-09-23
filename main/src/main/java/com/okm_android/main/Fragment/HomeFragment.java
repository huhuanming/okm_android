package com.okm_android.main.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.okm_android.main.Activity.LoginRegisterActivity;
import com.okm_android.main.R;

/**
 * Created by chen on 14-9-22.
 */
public class HomeFragment extends Fragment {
    private View parentView;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_home, container, false);
        Button button = (Button)parentView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), LoginRegisterActivity.class);
                startActivity(intent);
            }
        });
        return parentView;
    }
}
