package com.zx.xsk.baseclass;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.zx.xsk.sutillibrary.DisplayUtil;
import com.zx.xsk.sutillibrary.R;
import com.zx.xsk.views.loadingview.OnStateClickListener;
import com.zx.xsk.views.loadingview.SLoadingView;


/**
 *基本activity 基于databinding
 * Created by sjy on 2017/4/22.
 */

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity
    implements View.OnClickListener{
    public T binding;
    public Activity mActivity;
    public Context mContext;
    public boolean isHideStateBar=false;//是否隐藏状态栏，默认不隐藏
    public boolean isTransStateBar=false;//是否透明状态栏，默认否，配合fitsystemwindow使用（可改变某一个activity的状态栏颜色）
    public SLoadingView sLoadingView;
    private int marTop=0;//loadingview距离顶部距离
    protected BaseActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=this;
        mContext=this;
        binding=DataBindingUtil.setContentView(this,bindLayout());
        marTop=DisplayUtil.dip2px(mContext,45);//默认45dp
        if(isLoadingViewEnable()){
            sLoadingView=new SLoadingView(mContext);
            RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            params.topMargin=marTop ;//设置距离顶部距离，避免遮挡toolbar,此高度等于toolbar高度
            addContentView(sLoadingView,params);
        }
        initListener();
        bindData();
        setStateBar();
    }
    @Override
    public void onClick(View view) {
        onClickMethod(view.getId());
    }

    /**
     * 设置loadingview距离顶部高度
     * @param marTop
     */
    public void setMarTop(int marTop) {
        this.marTop = marTop;
    }
    /**
     * 设置loadingview距离顶部高度
     * @param
     */
    public void setMarTop(View view) {
        this.marTop =view.getHeight();
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
        if(sLoadingView!=null) {
            sLoadingView.setOnStateClick(onStateClickListener);
        }
    }



    /**
     * 设置状态栏
     */
    private void setStateBar() {
        if(isHideStateBar){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if(isTransStateBar){
            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
                getWindow().setStatusBarColor(Color.TRANSPARENT);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }else {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        }
    }

    /**
     * 是否需要显示加载进度框
     * @return
     */
    public abstract boolean isLoadingViewEnable();

    /**
     * 绑定布局
     * @return
     */
    public abstract int bindLayout();

    /**
     * 初始化交互事件
     */
    public abstract void initListener();

    /**
     * 绑定数据xml与pojo
     */
    public abstract void bindData();


    /**
     * 点击事件处理
     * @param viewId
     */
    public abstract void onClickMethod(int viewId);




}
