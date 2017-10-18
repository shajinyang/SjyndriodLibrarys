package com.zx.xsk.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.zx.xsk.views.loadingview.OnStateClickListener;
import com.zx.xsk.views.loadingview.SLoadingView;

/**
 * 带加载状态的父布局
 * Created by sjy on 2017/10/13.
 */

public class LoadingRelativeLayout extends RelativeLayout {
    private SLoadingView sLoadingView;
    private OnStateClickListener onStateClickListener;
    public LoadingRelativeLayout(Context context) {
        super(context);

    }

    public LoadingRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void addLoadingView(){
        sLoadingView=new SLoadingView(getContext());
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        sLoadingView.setLayoutParams(params);
        addView(sLoadingView);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(sLoadingView==null){
            addLoadingView();
        }
        if(sLoadingView!=null&&onStateClickListener!=null){
            sLoadingView.setOnStateClick(onStateClickListener);
        }
    }

    /**
     * 显示加载中
     */
    public void showLoading(){
        if(sLoadingView!=null){
            sLoadingView.showLoading();
        }
    }
    /**
     * 显示数据错误
     */
    public void showError(){
        if(sLoadingView!=null){
            sLoadingView.showError();
        }
    }
    /**
     * 显示无网络
     */
    public void showNoNet(){
        if(sLoadingView!=null){
            sLoadingView.showNoNet();
        }
    }
    /**
     * 显示空数据
     */
    public void showEmpty(){
        if(sLoadingView!=null){
            sLoadingView.showEmpty();
        }
    }

    /**
     * 清空进度view
     */
    public void clearAll(){
        if(sLoadingView!=null){
            sLoadingView.clearAll();
        }
    }

    /**
     * 加载状态点击监听
     * @param onStateClickListener
     */
    public void setOnStateClick(OnStateClickListener onStateClickListener){
        this.onStateClickListener=onStateClickListener;
    }
}
