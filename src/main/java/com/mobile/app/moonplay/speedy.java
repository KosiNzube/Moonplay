package com.mobile.app.moonplay;

import android.content.Context;
import android.graphics.PointF;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

public class speedy extends LinearLayoutManager {
    private static final  float MILI=5f;
    public speedy(Context context) {
        super(context);
    }

    public speedy(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public speedy(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {

        final LinearSmoothScroller linearSmoothScroller=new LinearSmoothScroller(recyclerView.getContext()){


            public PointF compute(int targetp){
                return super.computeScrollVectorForPosition(targetp);
            }
            protected float cslcspeed(DisplayMetrics metrics){
                return MILI/metrics.densityDpi;
            }
        };
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);

    }
}
