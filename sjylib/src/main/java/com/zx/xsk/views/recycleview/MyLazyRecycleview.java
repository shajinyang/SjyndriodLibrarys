package com.zx.xsk.views.recycleview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.zx.xsk.views.nestedscrollview.OnScrollBottomListener;


/**
 * 带滚动到底部监听+懒加载的recycleview
 * Created by sjy on 2017/6/23.
 */

public class MyLazyRecycleview extends RecyclerView {
    public MyLazyRecycleview(Context context) {
        super(context);
    }

    public MyLazyRecycleview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLazyRecycleview(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    /**
     * 设置滚动到底部方法
     * @param onScrollBottomListener
     */
    public void setScrollBottomMethod( final OnScrollBottomListener onScrollBottomListener ){
        this.addOnScrollListener(new OnScrollListener() {
           /* @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(isSlideToBottom(recyclerView)){
                    onScrollBottomListener.onBottom();
                }
            }*/

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(isSlideToBottom(recyclerView)){
                    if(dy>0) {
                        onScrollBottomListener.onBottom();
                    }
                }
            }
        });
    }

    public  boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }


    public void setOnLazyImageLoader(){
        this.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState==RecyclerView.SCROLL_STATE_IDLE){

                }
            }
        });
    }

}
