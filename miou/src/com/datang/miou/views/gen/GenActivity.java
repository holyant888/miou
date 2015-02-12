package com.datang.miou.views.gen;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;

import com.datang.miou.R;

/**
 * 
 * 
 * @author suntongwei
 */
public class GenActivity extends FragmentActivity {

	//定义FragmentTabHost对象  
    public FragmentTabHost mTabHost;  
      
    //定义一个布局  
    private LayoutInflater layoutInflater;  
          
    //定义数组来存放Fragment界面  
    private Class<?> fragmentArray[] = {GenMapFragment.class, GenParamsFragment.class, 
    		GenSignalFragment.class, GenTelStatFragment.class};  
      
    //Tab选项卡的文字  
    private String mTextviewArray[] = {"地图", "指标", "信令", "话务统计"};
    
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.gen);  
        initView();  
    }  
       
    /**
     * 初始化组件 
     */  
    private void initView(){  
        //实例化布局对象  
        layoutInflater = LayoutInflater.from(this);  
        //实例化TabHost对象，得到TabHost  
        mTabHost = (FragmentTabHost)findViewById(R.id.gen_tabhost);  
        mTabHost.setup(this, getSupportFragmentManager(), R.id.gen_realtabcontent);   
        //得到fragment的个数  
        int count = fragmentArray.length;     
        for(int i = 0; i < count; i++){    
            //为每一个Tab按钮设置图标、文字和内容  
            TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));  
            //将Tab按钮添加进Tab选项卡中  
            mTabHost.addTab(tabSpec, fragmentArray[i], null);  
        }  
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				TabWidget widget = mTabHost.getTabWidget();
				for (int i = 0; i < widget.getChildCount(); i++) {
					TextView textView = (TextView) widget.getChildAt(i);
					if (mTextviewArray[i].equals(tabId)) {
						textView.setBackgroundResource(R.color.title_blue);
						textView.setTextColor(getResources().getColor(R.color.white));
					} else {
						textView.setBackgroundResource(R.color.menu_gray);
						textView.setTextColor(getResources().getColor(R.color.black));
					}
				}
			}
		});
    }  
                  
    /** 
     * 给Tab按钮设置图标和文字 
     */  
    private View getTabItemView(int index) {  
        TextView view = (TextView) layoutInflater.inflate(R.layout.gen_menu_item, null, false);
        view.setText(mTextviewArray[index]);
        if(index == 0) {
        	view.setBackgroundResource(R.color.title_blue);
        	view.setTextColor(getResources().getColor(R.color.white));
        }
        return view;  
    }  
}
