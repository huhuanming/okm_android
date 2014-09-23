package com.okm_android.main.Activity;

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
    public static abstract interface MenuItemClick{
        public abstract void saveMenu();
    }

    private ActionBarDrawerToggle drawerToggle;
    private MenuAdapter adapter;
    public static MenuItemClick  menuItemClick;
    private RelativeLayout relativeLayout;

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
//                        fragmentPositon = pos;
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
//        this.item = item;
//        switch (item.getItemId()){
//            case R.id.menu_save:{
//                menuItemClick.saveMenu();
//            }
//            break;
//        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menumanagement_right, menu);
//        MenuItem saveMenuItem = menu.getItem(0);
//        saveMenuItem.setVisible(false);
//        switch (fragmentPositon){
//            case 1:{
//                saveMenuItem.setVisible(false);
//                adapter.notifyDataSetChanged();
//            }
//            break;
//            case 2:{
//                saveMenuItem.setVisible(false);
//                adapter.notifyDataSetChanged();
//            }
//            break;
//            case 3:{
//                saveMenuItem.setVisible(false);
//                adapter.notifyDataSetChanged();
//            }
//            break;
//            case 4:{
//                saveMenuItem.setVisible(true);
//                adapter.notifyDataSetChanged();
//            }
//            break;
//            case 5:{
//                saveMenuItem.setVisible(false);
//                adapter.notifyDataSetChanged();
//            }
//            break;
//        }
        return true;
    }

}
