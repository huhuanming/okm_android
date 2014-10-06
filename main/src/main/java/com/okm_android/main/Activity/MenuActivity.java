package com.okm_android.main.Activity;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.okm_android.main.Adapter.MenuAdapter;
import com.okm_android.main.R;

import java.util.ArrayList;


public class MenuActivity extends FragmentActivity {

    //    final String[] menuEntries = {"店铺管理","数据报表","订单管理","菜单管理","关于我们"};
    private ArrayList<String> menuEntries = new ArrayList<String>();
    private ArrayList<Integer> menuImage = new ArrayList<Integer>();
    final String[] fragments = {
            "com.okm_android.main.Fragment.HomeFragment",
            "com.okm_android.main.Fragment.UserFragment",
            "com.okm_android.main.Fragment.OrderFragment",
            "com.okm_android.main.Fragment.TruckFragment",
            "com.okm_android.main.Fragment.SettingFragment"
    };
    public static abstract interface MenuActionbarItemClick{
        public abstract void onClick(int id);
    }

    private ActionBarDrawerToggle drawerToggle;
    private MenuAdapter adapter;
    public static MenuActionbarItemClick  menuActionbarItemClick;
    private RelativeLayout relativeLayout;
    private int fragmentPositon = 0;
    String[] actions = new String[] {
            "成都市东软大道一号"
            ,"犀浦校园路"
            ,"地铁站"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getActionBar().setTitle(R.string.home);

        menuEntries.add("首页");
        menuEntries.add("个人资料");
        menuEntries.add("我的订单");
        menuEntries.add("送货地址");
        menuEntries.add("设置");

        menuImage.add(R.drawable.home);
        menuImage.add(R.drawable.user);
        menuImage.add(R.drawable.order);
        menuImage.add(R.drawable.truck);
        menuImage.add(R.drawable.setting);

        final DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        final ListView navList = (ListView) findViewById(R.id.drawer);
        relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        adapter = new MenuAdapter(getActionBar().getThemedContext(),menuEntries,menuImage);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawer,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
//                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
//                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        drawer.setDrawerListener(drawerToggle);

        navList.setAdapter(adapter);
        navList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int pos,long id){
                drawer.setDrawerListener( new DrawerLayout.SimpleDrawerListener(){
                    @Override
                    public void onDrawerClosed(View drawerView){
                        super.onDrawerClosed(drawerView);
                        fragmentPositon = pos;
                        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                        Fragment fragment = Fragment.instantiate(MenuActivity.this, fragments[pos]);
                        tx.replace(R.id.main, fragment);
                        getActionBar().setTitle(menuEntries.get(pos));

                        tx.commit();
                    }
                });
                drawer.closeDrawer(relativeLayout);
            }
        });
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.main,Fragment.instantiate(MenuActivity.this, fragments[0]));
        tx.commit();


        /** Create an array adapter to populate dropdownlist */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), R.layout.spinner_item_print, actions);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item_print);
        /** Enabling dropdown list navigation for the action bar */
        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        /** Defining Navigation listener */
        ActionBar.OnNavigationListener navigationListener = new ActionBar.OnNavigationListener() {


            @Override
            public boolean onNavigationItemSelected(int itemPosition, long itemId) {

                return false;
            }
        };
        /** Setting dropdown items and item navigation listener for the actionbar */
        getActionBar().setListNavigationCallbacks(adapter, navigationListener);


    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()){
            case R.id.menu_search:{
                menuActionbarItemClick.onClick(R.id.menu_search);
            }
            break;
            case R.id.menu_shake:{
                menuActionbarItemClick.onClick(R.id.menu_shake);
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menumanagement_right, menu);
        MenuItem searchMenuItem = menu.getItem(0);
        MenuItem shackMenuItem = menu.getItem(1);
        searchMenuItem.setVisible(false);
        shackMenuItem.setVisible(false);
        switch (fragmentPositon){
            case 0:{

                searchMenuItem.setVisible(true);
                shackMenuItem.setVisible(true);
                adapter.notifyDataSetChanged();
            }
            break;
            case 1:{
                getActionBar().setDisplayShowTitleEnabled(true);
                getActionBar().setTitle("个人资料");
                shackMenuItem.setVisible(false);
                searchMenuItem.setVisible(false);
                adapter.notifyDataSetChanged();
            }
            break;
            case 2:{
                getActionBar().setDisplayShowTitleEnabled(true);
                getActionBar().setTitle("我的订单");
                shackMenuItem.setVisible(false);
                searchMenuItem.setVisible(false);
                adapter.notifyDataSetChanged();
            }
            break;
            case 3:{
                getActionBar().setDisplayShowTitleEnabled(true);
                getActionBar().setTitle("送货地址");
                shackMenuItem.setVisible(false);
                searchMenuItem.setVisible(false);
                adapter.notifyDataSetChanged();
            }
            break;
            case 4:{
                getActionBar().setDisplayShowTitleEnabled(true);
                getActionBar().setTitle("设置");
                shackMenuItem.setVisible(false);
                searchMenuItem.setVisible(false);
                adapter.notifyDataSetChanged();
            }
            break;
        }
        return true;
    }

}
