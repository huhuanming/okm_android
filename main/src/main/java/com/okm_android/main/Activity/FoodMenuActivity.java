package com.okm_android.main.Activity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.okm_android.main.R;
import com.okm_android.main.Utils.AddObserver.NotificationCenter;

import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * Created by QYM on 14-10-10.
 */
public class FoodMenuActivity extends FragmentActivity {
    final String[] fragments={
            "com.okm_android.main.Fragment.FoodMenuFragment",
            "com.okm_android.main.Fragment.ShopDetailFragment"
    };
    RadioButton foodMenu;
    SegmentedGroup segmentedGroup;
    RadioButton shopDetail;
    public String rid=null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_message);

        Intent intent = this.getIntent();
        final Bundle bundle = intent.getExtras();
        rid=bundle.getString("rid");
        getIntent().putExtra("Restaurant_id", rid);
        //ButterKnife.inject(this);
        foodMenu=(RadioButton)findViewById(R.id.rbtn_food_menu);
        segmentedGroup=(SegmentedGroup)findViewById(R.id.shop_message_segmented);
        shopDetail=(RadioButton)findViewById(R.id.rbtn_shop_detail);

        ActionBar actionBar = this.getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        segmentedGroup.setTintColor(getResources().getColor(R.color.bbutton_info_edge), Color.WHITE);

        segmentedGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId)
                {
                    case R.id.rbtn_food_menu:
                        getIntent().putExtra("Restaurant_id", rid);
                        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                        tx.replace(R.id.shop_message_fragment, Fragment.instantiate(FoodMenuActivity.this, fragments[0]));
                        tx.commit();
                        break;
                    case R.id.rbtn_shop_detail:
                        FragmentTransaction tx2 = getSupportFragmentManager().beginTransaction();
                        tx2.replace(R.id.shop_message_fragment, Fragment.instantiate(FoodMenuActivity.this, fragments[1]));
                        tx2.commit();
                        break;
                }
            }
        });

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.shop_message_fragment, Fragment.instantiate(FoodMenuActivity.this, fragments[0]));
        tx.commit();

        NotificationCenter.getInstance().addObserver("sendRid", this, rid);
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            //监听返回键
            case android.R.id.home:
                FoodMenuActivity.this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}