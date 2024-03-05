package com.example.vocalcoach;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener listener;
    GestureDetector detector;

    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener onItemClickListener){
        listener = onItemClickListener;
        detector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e){
                return true;
            }
            @Override
            public void onLongPress(MotionEvent e){
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY() );
                if (child!=null && listener!= null){
                    listener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                }
            }
        });
    }


    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView!=null && listener!=null && detector.onTouchEvent(e)){
            listener.onItemClick(childView, rv.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    interface OnItemClickListener {
        public void onItemClick(View view, int position);
        public void onLongItemClick(View view, int position);
    }
}
