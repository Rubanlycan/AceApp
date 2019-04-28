package com.ionicframework.itech719214;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerClickListener implements RecyclerView.OnItemTouchListener {

    OnItemClickListener mListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    GestureDetector gestureDetector;

    public  RecyclerClickListener (Context context,OnItemClickListener listener)
    {
      mListener = listener;
      gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
          @Override
          public boolean onSingleTapUp(MotionEvent e) {
              return true;
          }
      });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView view, @NonNull MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && gestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
