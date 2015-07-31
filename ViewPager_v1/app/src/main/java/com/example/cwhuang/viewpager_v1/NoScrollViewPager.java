package com.example.cwhuang.viewpager_v1;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by cwhuang on 2015/7/30.
 */
public class NoScrollViewPager extends ViewPager{

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    //拦截 TouchEvent     返回false 拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return false;
    }

    //处理 TouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
//        return super.onTouchEvent(arg0);
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //让父类不拦截触摸事件就可以了。
        this.getParent().requestDisallowInterceptTouchEvent(false);
        return super.dispatchTouchEvent(ev);


    }
}
