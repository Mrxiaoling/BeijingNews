package com.example.beijingnews.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beijingnews.domain.NewsCenterPagerBeam;
import com.example.beijingnews.utils.LogUtil;

import java.util.List;

public class LeftMenuFragment extends BaseFragment {

    private List<NewsCenterPagerBeam.DataBean> data;

    @Override
    public View initView() {
        return null;
    }

    @Override
    public void initData() {
        super.initData();
    }

    /**
     * 接受数据
     * @param data
     */
    public void setData(List<NewsCenterPagerBeam.DataBean> data) {
        this.data=data;
        for (int i = 0; i<data.size();i++){

            LogUtil.e("title=="+data.get(i).getTitle());
        }

    }
}
