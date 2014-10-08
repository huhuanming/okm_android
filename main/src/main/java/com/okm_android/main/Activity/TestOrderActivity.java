package com.okm_android.main.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.okm_android.main.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * Created by QYM on 14-10-8.
 */
public class TestOrderActivity extends FragmentActivity {
    final String[] fragments={
        "com.okm_android.main.Fragment.OrderChooseFragment",
        "com.okm_android.main.Fragment.OrderDetailFragment"
    };
    @InjectView(R.id.btn_order_choose)RadioButton orderChoose;
    @InjectView(R.id.segmented2)SegmentedGroup segmentedGroup;
    @InjectView(R.id.btn_order_detail)RadioButton orderDetail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_order);
        ButterKnife.inject(this);
        ActionBar actionBar = this.getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        segmentedGroup.setTintColor(getResources().getColor(R.color.segment_color), Color.WHITE);

        segmentedGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId)
                {
                    case R.id.btn_order_choose:
                        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                        tx.replace(R.id.frame_order, Fragment.instantiate(TestOrderActivity.this, fragments[0]));
                        tx.commit();
                        break;
                    case R.id.btn_order_detail:
                        FragmentTransaction tx2 = getSupportFragmentManager().beginTransaction();
                        tx2.replace(R.id.frame_order, Fragment.instantiate(TestOrderActivity.this, fragments[1]));
                        tx2.commit();
                        break;
                }
            }
        });

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.frame_order, Fragment.instantiate(TestOrderActivity.this, fragments[0]));
        tx.commit();
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // TODO Auto-generated method stub
        switch (item.getItemId())
        {
            //监听返回键
            case android.R.id.home:
                TestOrderActivity.this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
