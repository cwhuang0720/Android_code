package com.example.cwhuang.viewpager_v1;

import android.app.Activity;

import android.app.Dialog;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private static final String TAG="LogDemo";
    private ViewPager mViewPager;
    private MyPagerAdapter pagerAdapter = null;
    //private NoScrollViewPager mViewPager;
    private float mPosX,mCurPosX,mPosY,mCurPosY;
    List<View> viewList;
    private View v1,v2,v3;



    private void create_viewpager(){
        LayoutInflater mInflater = getLayoutInflater().from(this);
        v1 = mInflater.inflate(R.layout.layout1, null);
        v2 = mInflater.inflate(R.layout.layout2, null);
        v3 = mInflater.inflate(R.layout.layout3, null);

        //添加页面数据
        viewList = new ArrayList<View>();
        viewList.add(v1);
        viewList.add(v2);
        viewList.add(v3);
        setGestureListener(v1, "v1");
        setGestureListener(v2, "v2");
        setGestureListener(v3, "v3");

        //实例化适配器
        pagerAdapter = new MyPagerAdapter(viewList);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        //mViewPager = (NoScrollViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(0); //设置默认当前页
        Log.d("Tag", "set viewpager!!!!");



    }

    private void setGestureListener(View v,String name){
        final String na = name;
        v.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        mPosX = event.getX();
                        mPosY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurPosX = event.getX();
                        mCurPosY = event.getY();

                        break;
                    case MotionEvent.ACTION_UP:
                        if (mCurPosY - mPosY > 0
                                && (Math.abs(mCurPosY - mPosY) > 25)) {
                                /*
                                mViewPager.removeView(v);
                                mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
                                */
                            final Toast toast = Toast.makeText(getApplicationContext(),
                                    "U delete "+na,
                                    Toast.LENGTH_SHORT);
                            toast.show();

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    toast.cancel();
                                }
                            }, 500);
                                pagerAdapter.removeView(mViewPager,v);
                                pagerAdapter.notifyDataSetChanged();
                            Log.d(TAG, "down !! remove view!!!");
                        } else if (mCurPosY - mPosY < 0
                                && (Math.abs(mCurPosY - mPosY) > 25)) {
                            //向上滑动

                            /*Toast.makeText(
                                    MainActivity.this,
                                    "U like "+na,
                                    Toast.LENGTH_SHORT)
                                    .show();*/
                            final Toast toast = Toast.makeText(getApplicationContext(),
                                    "U like "+na,
                                    Toast.LENGTH_SHORT);
                            toast.show();

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    toast.cancel();
                                }
                            }, 500);

                            Log.d(TAG, "up !!u like it!!!");

                        }

                        break;
                }
                return true;
            }

        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create_viewpager();
        Log.d("T","onCreate!!!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
