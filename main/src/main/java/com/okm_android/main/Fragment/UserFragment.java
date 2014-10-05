package com.okm_android.main.Fragment;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.okm_android.main.R;

/**
 * Created by chen on 14-9-22.
 */
public class UserFragment extends Fragment {
    private View parentView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().getActionBar().setDisplayShowTitleEnabled(true);
        getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        getActivity().invalidateOptionsMenu();
        parentView = inflater.inflate(R.layout.fragment_user, container, false);
        return parentView;
    }
}
