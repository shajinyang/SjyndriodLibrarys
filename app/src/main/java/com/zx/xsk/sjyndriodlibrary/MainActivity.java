package com.zx.xsk.sjyndriodlibrary;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.zx.xsk.baseclass.BaseActivity;
import com.zx.xsk.managers.IntentManager;
import com.zx.xsk.managers.LoadingManager;
import com.zx.xsk.managers.ToastManager;
import com.zx.xsk.sjyndriodlibrary.databinding.ActivityMainBinding;
import com.zx.xsk.views.loadingview.OnStateClickListener;
import com.zx.xsk.views.loadingview.SLoadingView;
import com.zx.xsk.views.mutikeyboardview.MultiKeyBoardView;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private MultiKeyBoardView multiKeyBoardView;

    @Override
    public boolean isLoadingViewEnable() {
        return true;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initListener() {
        multiKeyBoardView= (MultiKeyBoardView) findViewById(R.id.mul);
        String[] strings={"插入图片","插入音乐","插入视频"};
        ArrayList<Fragment> list=new ArrayList<>();
        list.add(new TestFragment());
        list.add(new TestFragment());
        list.add(new TestFragment());
        WebView webView= (WebView) findViewById(R.id.content_view);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.baidu.com");
        multiKeyBoardView
                .initKeyBoardView(this,strings,list,webView)
                .setTabColor(0xff333333)
                .setTabTxtColor(0xff999999,0xffffffff);
        setOnStateClick(new OnStateClickListener() {
            @Override
            public void onClickError() {
                clearAll();
            }

            @Override
            public void onClickNoNet() {
                showError();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showNoNet();
                        LoadingManager.closeLoading();
                    }
                });

            }
        }).start();

    }

    @Override
    public void bindData() {
        binding.main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentManager.skipToActivity(mContext,MainActivity2.class);
            }
        });
        binding.toolbar.setTitle("试一试标题很长会有什么特别的效果");
        LoadingManager.showLoading(mContext);
    }

    @Override
    public void onClickMethod(int viewId) {

    }


    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

    }

}
