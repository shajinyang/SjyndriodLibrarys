package com.zx.xsk.views.toolbar;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sjy on 2017/9/27.
 */

public class SToolBarView extends Toolbar {
    private Context mContext;
    public SToolBarView(Context context) {
        this(context,null);
        mContext=context;
    }

    public SToolBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mContext instanceof Activity){
                    ((Activity) mContext).finish();
                }
            }
        });

    }

    public SToolBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mContext instanceof Activity){
                    ((Activity) mContext).finish();
                }
            }
        });
    }


    public void addMenu(int resid,OnMenuItemClickListener onMenuItemClickListener){
        inflateMenu(resid);
        setOnMenuItemClickListener(onMenuItemClickListener);
    }

}
