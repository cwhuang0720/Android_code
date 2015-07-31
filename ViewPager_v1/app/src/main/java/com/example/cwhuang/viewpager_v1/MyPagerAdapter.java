package com.example.cwhuang.viewpager_v1;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwhuang on 2015/7/29.
 */
public class MyPagerAdapter extends PagerAdapter {

    private List<View> mListViews;

    public MyPagerAdapter() {
        super();
    }

    public MyPagerAdapter(List<View> mListViews) {
        super();
        this.mListViews = mListViews;
        Log.d("TAG", "creator?");
    }


    @Override
    public void destroyItem(View collection, int position, Object arg2) {
        Log.d("k", "destroyItem?");
        ((ViewPager) collection).removeView(mListViews.get(position));
    }

    @Override
    public void finishUpdate(View arg0) {
        // TODO Auto-generated method stub
        Log.d("k", "finishUpdate?");
    }

    @Override
    public int getCount() {
        Log.d("k", "getCount?"+mListViews.size());
        return mListViews.size();
    }
    /*
    @Override
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        Log.d("k", "getItemPosition?");
        return POSITION_NONE;
    }*/
    //add
    @Override
    public int getItemPosition (Object object)
    {
        int index = mListViews.indexOf (object);
        if (index == -1)
            return POSITION_NONE;
        else
            return index;
    }

    @Override
    public Object instantiateItem(View collection, int position) {
        Log.d("k", "instantiateItem?"+position);
        ((ViewPager) collection).addView(mListViews.get(position),0);
        return mListViews.get(position);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        Log.d("k", "isViewFromObject?");
        return arg0==(arg1);
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
        // TODO Auto-generated method stub
        Log.d("k", "restoreState?");
    }

    @Override
    public Parcelable saveState() {
        // TODO Auto-generated method stub
        Log.d("k", "saveState?");
        return null;
    }

    @Override
    public void startUpdate(View arg0) {
        // TODO Auto-generated method stub
        Log.d("k", "startUpdate?");
    }

    public int addView(View v)
    {
        return addView(v, mListViews.size());
    }

    //-----------------------------------------------------------------------------
    // Add "view" at "position" to "views".
    // Returns position of new view.
    // The app should call this to add pages; not used by ViewPager.
    public int addView(View v, int position)
    {
        mListViews.add(position, v);
        return position;
    }

    //-----------------------------------------------------------------------------
    // Removes "view" from "views".
    // Retuns position of removed view.
    // The app should call this to remove pages; not used by ViewPager.
    public int removeView(ViewPager pager, View v)
    {
        return removeView (pager, mListViews.indexOf (v));
    }

    //-----------------------------------------------------------------------------
    // Removes the "view" at "position" from "views".
    // Retuns position of removed view.
    // The app should call this to remove pages; not used by ViewPager.
    public int removeView (ViewPager pager, int position)
    {
        // ViewPager doesn't have a delete method; the closest is to set the adapter
        // again.  When doing so, it deletes all its views.  Then we can delete the view
        // from from the adapter and finally set the adapter to the pager again.  Note
        // that we set the adapter to null before removing the view from "views" - that's
        // because while ViewPager deletes all its views, it will call destroyItem which
        // will in turn cause a null pointer ref.
        pager.setAdapter (null);
        mListViews.remove (position);
        pager.setAdapter (this);

        return position;
    }

    //-----------------------------------------------------------------------------
    // Returns the "view" at "position".
    // The app should call this to retrieve a view; not used by ViewPager.
    public View getView (int position)
    {
        return mListViews.get (position);
    }

    // Other relevant methods:

    // finishUpdate - called by the ViewPager - we don't care about what pages the
    // pager is displaying so we don't use this method.

}
