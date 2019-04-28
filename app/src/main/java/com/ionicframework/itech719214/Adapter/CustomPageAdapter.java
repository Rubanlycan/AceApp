package com.ionicframework.itech719214.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ionicframework.itech719214.Data;
import com.ionicframework.itech719214.R;

import java.util.List;

public class CustomPageAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;

   List<Data> list;

    public CustomPageAdapter(Context Context) {
        mContext = Context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
         return view == ((LinearLayout) o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.ads_banner, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageView.setImageResource(Integer.valueOf(list.get(0).getSB_1st_Path()));

        container.addView(itemView);

        return itemView;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
