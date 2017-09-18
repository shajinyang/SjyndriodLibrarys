package com.zx.xsk.baseclass;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;


/**
 * 基础activity，基于databinding ,实现了takephoto接口,暂时无法裁切图片
 * Created by sjy on 2017/4/22.
 */

public abstract class BasePhotoActivity<T extends ViewDataBinding> extends AppCompatActivity
    implements View.OnClickListener,TakePhoto.TakeResultListener,InvokeListener{
    public T binding;
    public TakePhoto takePhoto;
    public Context mContext;

    private InvokeParam invokeParam;


    protected BasePhotoActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,bindLayout());
        mContext=this;
        bindData();
        initListener();
        getTakePhoto();

    }

    /**
     * 接收activity回调传值
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public abstract void onActivityMyResult(int requestCode, int resultCode, Intent data);

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


    @Override
    public void onClick(View view) {
        onClickMethod(view.getId());
    }

    /**
     * 点击事件处理
     * @param viewId
     */
    public abstract void onClickMethod(int viewId);

    /**
     *  获取TakePhoto实例
     * @return
     */
    public TakePhoto getTakePhoto(){
        if (takePhoto==null){
            takePhoto= (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this,this));
        }
        return takePhoto;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(this,type,invokeParam,this);
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        onActivityMyResult(requestCode,resultCode,data);
    }
}
