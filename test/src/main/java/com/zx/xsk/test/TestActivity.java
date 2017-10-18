package com.zx.xsk.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jph.takephoto.model.TResult;
import com.orhanobut.logger.Logger;
import com.zx.xsk.baseclass.BaseActivity;
import com.zx.xsk.listeners.IOnChoosePicListener;
import com.zx.xsk.test.databinding.ActivityTestBinding;
import com.zx.xsk.views.LoadingRelativeLayout;
import com.zx.xsk.views.loadingview.OnStateClickListener;
import com.zx.xsk.weight.ImageLoder;
import java.io.File;

public class TestActivity extends BaseActivity<ActivityTestBinding> {
    private LoadingRelativeLayout loadingRelativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        loadingRelativeLayout=((LoadingRelativeLayout)findViewById(R.id.loading));
        findViewById(R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicWithCompress(new IOnChoosePicListener() {
                    @Override
                    public void onSuccess(final TResult result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Bitmap bitmap= BitmapFactory.decodeFile(result.getImage().getOriginalPath());
                                ((ImageView)findViewById(R.id.imageView)).setImageBitmap(bitmap);
                            }
                        });
                    }
                    @Override
                    public void onFailure(TResult result, String msg) {
                    }

                    @Override
                    public void onCancel() {
                        Logger.e("取消选择");
                    }
                },true);
            }
        });

        loadingRelativeLayout.setOnStateClick(new OnStateClickListener() {
            @Override
            public void onClickError() {
                loadingRelativeLayout.clearAll();
            }

            @Override
            public void onClickNoNet() {
                loadingRelativeLayout.showError();
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loadingRelativeLayout.showNoNet();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @Override
    public int bindLayout() {
        return R.layout.activity_test;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {

    }

    @Override
    public void onClickMethod(int viewId) {

    }


}
