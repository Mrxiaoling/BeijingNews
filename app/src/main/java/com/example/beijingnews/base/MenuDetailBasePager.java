package com.example.beijingnews.base;

import android.content.Context;
import android.view.View;

/**
 * 详情页面的基类
 */
public abstract class MenuDetailBasePager {
    public final Context context;
    public View rootView;//代表各个详情页面的视图
    public MenuDetailBasePager(Context context){
        this.context= context;
        rootView = initView();
    }

    /**
     * 抽象方法，因为各个详情页面不同
     * @return
     */
    public abstract View initView() ;

    /**
     * 子页面需要绑定数据，联网请求数据的时候，重写该方法
     */
    public void initData(){

    }

}
