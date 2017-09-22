package com.zx.xsk.sjyndriodlibrary;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zx.xsk.baseclass.ViewPagerCommonAdapter;
import com.zx.xsk.views.NoScrollViewPager;
import com.zx.xsk.views.mutikeyboardview.MultiKeyBoardView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MultiKeyBoardView multiKeyBoardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
