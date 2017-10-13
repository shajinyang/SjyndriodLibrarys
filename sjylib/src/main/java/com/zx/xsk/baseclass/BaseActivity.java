package com.zx.xsk.baseclass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.zx.xsk.listeners.IOnChoosePicListener;
import com.zx.xsk.managers.UIer;
import com.zx.xsk.sutil.DisplayUtil;
import com.zx.xsk.views.loadingview.OnStateClickListener;
import com.zx.xsk.views.loadingview.SLoadingView;

import java.io.File;


/**
 *基本activity 基于databinding
 * Created by sjy on 2017/4/22.
 */

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity
    implements View.OnClickListener,InvokeListener,TakePhoto.TakeResultListener {
    public T binding;
    public Activity mActivity;
    public Context mContext;
    public boolean isHideStateBar=false;//是否隐藏状态栏，默认不隐藏
    public boolean isTransStateBar=false;//是否透明状态栏，默认否，配合fitsystemwindow使用（可改变某一个activity的状态栏颜色）
    private InvokeParam invokeParam;
    private  boolean isCompress=false;//是否压缩
    private  boolean isCut=false;//是否裁切
    private int maxChooseSize=1;//最大选择数
    private  TakePhoto takePhoto;
    private IOnChoosePicListener iOnChoosePicListener;
    private Uri imageUri;
    protected BaseActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=this;
        mContext=this;
        binding=DataBindingUtil.setContentView(this,bindLayout());
        initListener();
        bindData();
        setStateBar();
    }
    @Override
    public void onClick(View view) {
        onClickMethod(view.getId());
    }

    /**
     * 选择拍照或图片，不压缩
     * @param iOnChoosePicListener
     */
    public void choosePic(IOnChoosePicListener iOnChoosePicListener){
        this.iOnChoosePicListener=iOnChoosePicListener;
        if (takePhoto==null){
            takePhoto= (TakePhoto)TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(mActivity,this));
        }
        takePhoto.onPickFromGallery();
    }
    /**
     * 选择拍照或图片，压缩
     * @param iOnChoosePicListener
     */
    public void choosePicWithCompress(IOnChoosePicListener iOnChoosePicListener,boolean isCompress){
        if(isCompress){
            this.iOnChoosePicListener=iOnChoosePicListener;
            if (takePhoto==null){
                takePhoto= (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(mActivity,this));
            }
            configCompress(takePhoto);
            takePhoto.onPickFromGallery();
        }else {
            choosePic(iOnChoosePicListener);
        }

    }
    /**
     * 选择拍照或图片，裁剪
     * @param iOnChoosePicListener
     */
    public void choosePicWithCut(IOnChoosePicListener iOnChoosePicListener,boolean isCut){
        if(isCut){
            this.iOnChoosePicListener=iOnChoosePicListener;
            if (takePhoto==null){
                takePhoto= (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(mActivity,this));
            }
            File file=getDiskCacheDir(mContext,"takephotos");
            if (!file.getParentFile().exists())file.getParentFile().mkdirs();
            imageUri = Uri.fromFile(file);
            takePhoto.onPickFromGalleryWithCrop(imageUri,getCropOptions());
        }else {
            choosePic(iOnChoosePicListener);
        }

    }
    /**
     * 获取缓存目录
     */
    private File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath = context.getCacheDir().getPath();
        return new File(cachePath + File.separator + uniqueName,System.currentTimeMillis() + ".jpg");
    }

    private void configCompress(TakePhoto takePhoto){
        CompressConfig compressConfig=new CompressConfig.Builder().setMaxSize(50*1024).setMaxPixel(800).create();
        takePhoto.onEnableCompress(compressConfig,true);
    }
    private CropOptions getCropOptions(){
        CropOptions cropOptions=new CropOptions.Builder()
                .setAspectX(1)
                .setAspectY(1)
                .setWithOwnCrop(true)
                .create();
        return cropOptions;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UIer.destoryLoading();
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //初始化图片选择回调
        if(takePhoto!=null) {
            takePhoto.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
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
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(mActivity),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }


    @Override
    public void takeSuccess(TResult result) {
        iOnChoosePicListener.onSuccess(result);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        iOnChoosePicListener.onFailure(result,msg);
    }

    @Override
    public void takeCancel() {
        iOnChoosePicListener.onCancel();
    }

}
