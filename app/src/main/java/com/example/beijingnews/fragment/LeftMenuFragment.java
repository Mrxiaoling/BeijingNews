package com.example.beijingnews.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.beijingnews.R;
import com.example.beijingnews.acitity.MainActivity;
import com.example.beijingnews.domain.NewsCenterPagerBeam;
import com.example.beijingnews.pager.NewsCenterPager;
import com.example.beijingnews.utils.DensityUtil;
import com.example.beijingnews.utils.LogUtil;

import java.util.List;

public class LeftMenuFragment extends BaseFragment {

    private ListView listView;

    private LeftmenuFragmentAdapter adapter;

    private List<NewsCenterPagerBeam.DataBean> data;
    /**
     * 点击的位置
     */
    private int prePosition;

    @Override
    public View initView() {

        listView = new ListView(context);
        listView.setPadding(0, DensityUtil.dipTopx(context, 40), 0, 0);
        listView.setDividerHeight(0);
        listView.setCacheColorHint(Color.TRANSPARENT);
        //设置按下listView的Item不变色
        listView.setSelector(android.R.color.transparent);
        //设置item的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //1、记录点击的位置，变成红色
                prePosition = position;
                adapter.notifyDataSetChanged();////getCount()-->getView
                //2、把左侧菜单关闭
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.getSlidingMenu().toggle();//关<->开
                //3、切换到对应的详情页面：新闻详情页面，专题详情页面等等
                swichPager(prePosition);
            }
        });

        return listView;
    }
    /**
     * 根据位置切换不同详情页面
     * @param position
     */
    private void swichPager(int position) {

//        MainActivity mainActivity = (MainActivity) context;
//        ContentFragment contentFragment = mainActivity.getContentFragment();
//        NewsCenterPager newsCenterPager = contentFragment.getNewsCenterPager();
//        newsCenterPager.swichPager(position);
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
        adapter = new LeftmenuFragmentAdapter();
        //设置适配器
        listView.setAdapter(adapter);


    }

    class LeftmenuFragmentAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = (TextView) View.inflate(context, R.layout.item_leftmenu,null);
            textView.setText(data.get(position).getTitle());
//            if(position==prePosition){
//                //设置红色
//                textView.setEnabled(true);
//
//            }else{
//                textView.setEnabled(false);
//            }

            textView.setEnabled(position==prePosition);
            return textView;
        }
        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


    }
}
