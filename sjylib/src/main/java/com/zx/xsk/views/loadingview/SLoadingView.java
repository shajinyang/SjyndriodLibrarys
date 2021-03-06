package com.zx.xsk.views.loadingview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.zx.xsk.sutillibrary.R;

/**
 * 加载view（加载中，无网络，网络错误，无数据）
 * Created by sjy on 2017/9/27.
 */

public class SLoadingView extends FrameLayout {

    public static final int STATE_LOADING=0;//加载中
    public static final int STATE_NO_NET=1;//无网络
    public static final int STATE_ERROR_NET=2;//网络错误
    public static final int STATE_EMPTY_DATA=3;//无数据

    private View loadingView;
    private View noNetView;
    private View errorNetView;
    private View emptyDataView;

    private int loadingviewid=R.layout.sloadingview_loading;
    private int nonetviewid=R.layout.sloadingview_no_net;
    private int errornetid=R.layout.sloadingview_error_net;
    private int emptyviewid=R.layout.sloadingview_no_data;



    public SLoadingView(@NonNull Context context) {
        this(context,null);
    }

    public SLoadingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public SLoadingView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if(attrs!=null){
            TypedArray typedArray=getContext().obtainStyledAttributes(attrs, R.styleable.SLoadingView);
            loadingviewid=typedArray.getInteger(R.styleable.SLoadingView_loading_view,loadingviewid);
            nonetviewid=typedArray.getInteger(R.styleable.SLoadingView_no_net_view,nonetviewid);
            errornetid=typedArray.getInteger(R.styleable.SLoadingView_error_net_view,errornetid);
            emptyviewid=typedArray.getInteger(R.styleable.SLoadingView_empty_data_view,emptyviewid);
        }
        loadingView= LayoutInflater.from(getContext()).inflate(loadingviewid,null);
        noNetView= LayoutInflater.from(getContext()).inflate(nonetviewid,null);
        errorNetView= LayoutInflater.from(getContext()).inflate(errornetid,null);
        emptyDataView= LayoutInflater.from(getContext()).inflate(emptyviewid,null);
        noNetView.setVisibility(GONE);
        errorNetView.setVisibility(GONE);
        emptyDataView.setVisibility(GONE);
        addView(loadingView);
        addView(noNetView);
        addView(errorNetView);
        addView(emptyDataView);
    }

    public void showLoading(){
        if(errorNetView!=null&&errorNetView.isShown()){
            errorNetView.setVisibility(GONE);
        }
        if(noNetView!=null&&noNetView.isShown()){
            noNetView.setVisibility(GONE);
        }
        if(emptyDataView!=null&&emptyDataView.isShown()){
            emptyDataView.setVisibility(GONE);
        }
        if(loadingView!=null&&!loadingView.isShown()){
            loadingView.setVisibility(VISIBLE);
        }

    }

    public void showNoNet(){
        if(errorNetView!=null&&errorNetView.isShown()){
            errorNetView.setVisibility(GONE);
        }
        if(loadingView!=null&&loadingView.isShown()){
            loadingView.setVisibility(GONE);
        }
        if(emptyDataView!=null&&emptyDataView.isShown()){
            emptyDataView.setVisibility(GONE);
        }
        if(noNetView!=null&&!noNetView.isShown()){
            noNetView.setVisibility(VISIBLE);
        }
    }

    public void showError(){
        if(loadingView!=null&&loadingView.isShown()){
            loadingView.setVisibility(GONE);
        }
        if(noNetView!=null&&noNetView.isShown()){
            noNetView.setVisibility(GONE);
        }
        if(emptyDataView!=null&&emptyDataView.isShown()){
            emptyDataView.setVisibility(GONE);
        }
        if(errorNetView!=null&&!errorNetView.isShown()){
            errorNetView.setVisibility(VISIBLE);
        }
    }

    public void showEmpty(){
        if(errorNetView!=null&&errorNetView.isShown()){
            errorNetView.setVisibility(GONE);
        }
        if(noNetView!=null&&noNetView.isShown()){
            noNetView.setVisibility(GONE);
        }
        if(loadingView!=null&&loadingView.isShown()){
            loadingView.setVisibility(GONE);
        }
        if(emptyDataView!=null&&!emptyDataView.isShown()){
            emptyDataView.setVisibility(VISIBLE);
        }
    }

    public void clearAll(){
        if(errorNetView!=null&&errorNetView.isShown()){
            errorNetView.setVisibility(GONE);
        }
        if(noNetView!=null&&noNetView.isShown()){
            noNetView.setVisibility(GONE);
        }
        if(emptyDataView!=null&&emptyDataView.isShown()){
            emptyDataView.setVisibility(GONE);
        }
        if(loadingView!=null&&loadingView.isShown()){
            loadingView.setVisibility(GONE);
        }
    }

    /**
     * 暴露点击回调
     * @param onStateClickListener
     */
    public void  setOnStateClick(final OnStateClickListener onStateClickListener){
        errorNetView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onStateClickListener.onClickError();
            }
        });
        noNetView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onStateClickListener.onClickNoNet();
            }
        });
        loadingView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        emptyDataView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }





}
