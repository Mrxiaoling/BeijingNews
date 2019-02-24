package com.example.beijingnews.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 基本的Fragment，leftFragment和ContentFragment将继承它
 */
abstract class BaseFragment extends Fragment {

    public Activity context;//MainActivity

    /**
     * 当Fragment创建的时候回调该方法
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    /**
     * 当view被创建的时候回调
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();

    }

    /**
     * 让孩子实现自己的视图，达到特有的效果
     * @return
     */
    public  abstract View initView() ;

    /**
     * 当activity创建了的时候被回调
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 1.如果自页面没有数据，联网请求数据，并且绑定到initView初始化的视图上
     *  2.绑定到initView初始化的视图上
     */
    public void initData(){

    }
}
