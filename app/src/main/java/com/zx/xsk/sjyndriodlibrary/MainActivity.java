package com.zx.xsk.sjyndriodlibrary;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zx.xsk.managers.LoadingManager;
import com.zx.xsk.views.mutikeyboardview.MultiKeyBoardView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MultiKeyBoardView multiKeyBoardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadingManager.showLoading(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LoadingManager.closeLoading();
                    }
                });
            }
        }).start();
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

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LoadingManager.destoryLoading();
    }
}
