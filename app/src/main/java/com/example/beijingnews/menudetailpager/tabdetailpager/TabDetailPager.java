package com.example.beijingnews.menudetailpager.tabdetailpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.beijingnews.Constans;
import com.example.beijingnews.R;
import com.example.beijingnews.base.MenuDetailBasePager;
import com.example.beijingnews.domain.NewsCenterPagerBeam;
import com.example.beijingnews.domain.TabDetailPagerBean;
import com.example.beijingnews.utils.CahcheUtils;
import com.example.beijingnews.utils.DensityUtil;
import com.example.beijingnews.utils.LogUtil;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

public class TabDetailPager extends MenuDetailBasePager {

    private ViewPager viewpager;
    private TextView tv_title;
    private LinearLayout ll_point_group;
    private ListView listview;

    private ImageOptions imageOptions;

    private final NewsCenterPagerBeam.DataBean.ChildrenBean childrenData;
//    public TextView textView;
    private String url;
    private int prePosition;//之前高亮点的位置

    //顶部轮播图部分数据
    private List<TabDetailPagerBean.DataBean.TopnewsData> topNews;
    private List<TabDetailPagerBean.DataBean.NewsData> news;
    private MyTabPagerAdapter adapter;
    //    public TabDetailPager(Context context) {
//        super(context);
//
//    }

    public TabDetailPager(Context context, NewsCenterPagerBeam.DataBean.ChildrenBean childrenData) {
        super(context);
        this.childrenData= childrenData;
        imageOptions = new ImageOptions.Builder()
                .setSize(org.xutils.common.util.DensityUtil.dip2px(80), org.xutils.common.util.DensityUtil.dip2px(80))
                .setRadius(org.xutils.common.util.DensityUtil.dip2px(5))
                .setCrop(true)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.drawable.news_pic_default)
                .setFailureDrawableId(R.drawable.news_pic_default)
                .build();

    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.tabdetail_pager, null );
        listview = view.findViewById(R.id.listview);
        View topNewsView = View.inflate(context, R.layout.topnews, null);
        viewpager = topNewsView.findViewById(R.id.viewpager);
        tv_title = topNewsView.findViewById(R.id.tv_title);
        ll_point_group = topNewsView.findViewById(R.id.ll_point_group);
        //把顶部轮播视图部分视图，以头的方式添加到ListView
        listview.addHeaderView(topNewsView);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        LogUtil.e(childrenData.getTitle());
        url = Constans.BASE_URL+childrenData.getUrl();
        //将缓存的数据取出
        String savedJson = CahcheUtils.getString(context, url);
        if (!TextUtils.isEmpty(savedJson)){
            //解析数据
            processData(savedJson);
        }
        LogUtil.e(childrenData.getTitle()+"的联网地址:"+url);
        getDataFromNet();
    }

    private void getDataFromNet() {
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                //缓存数据
                CahcheUtils.putString(context, url, result);
                LogUtil.e(childrenData.getTitle()+"页面数据请求成功"+result);
                //解析和处理显示数据
                processData(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                LogUtil.e(childrenData.getTitle()+"解析成功"+childrenData.getTitle()+"页面数据请求shibai ");

            }

            @Override
            public void onCancelled(CancelledException cex) {

                LogUtil.e(childrenData.getTitle()+"页面数据请求quxioa "+cex.getMessage());

            }

            @Override
            public void onFinished() {

                LogUtil.e(childrenData.getTitle()+"页面数据请求finish");

            }
        });
    }

    private void processData(String json) {

        TabDetailPagerBean bean = parasedJson(json);
        LogUtil.e(bean.getData().getNews().get(0).getTitle());
        //顶部轮播图数据
        topNews = bean.getData().getTopnews();
        //设置viewpager的适配器
        viewpager.setAdapter(new TaDetailPagerTopNewsAdapter());
        ll_point_group.removeAllViews();//移除所有红点非常,重要
        for (int i = 0; i<topNews.size();i++){

            ImageView imageView = new ImageView(context);
            imageView.setBackgroundResource(R.drawable.point_selector);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dipTopx(context, 8),DensityUtil.dipTopx(context, 8));
            if (i==0){
                imageView.setEnabled(true);
            }else {
                imageView.setEnabled(false);
                params.leftMargin= org.xutils.common.util.DensityUtil.dip2px(8);
            }
            imageView.setLayoutParams(params);
            ll_point_group.addView(imageView);
        }

        //监听页面变化，设置红点变化和文本变化
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());
        tv_title.setText(topNews.get(0).getTitle());//默认选中第一个


        //准备listview的数据
        news = bean.getData().getNews();
        //准备listview对应的集合数据
        adapter = new MyTabPagerAdapter();
        listview.setAdapter(adapter);

    }

    class MyTabPagerAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return news.size();
        }

        @Override
        public Object getItem(int position) {
            return news.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView==null){
                convertView = View.inflate(context, R.layout.item_tabdetail_pager, null);
                viewHolder=new ViewHolder();
                viewHolder.iv_icon=convertView.findViewById(R.id.iv_icon);
                viewHolder.tv_title=convertView.findViewById(R.id.tv_title);
                viewHolder.tv_time=convertView.findViewById(R.id.tv_time);
                convertView.setTag(viewHolder);
            }else {

                viewHolder = (ViewHolder) convertView.getTag();

            }

            //根据位置得到数据
            TabDetailPagerBean.DataBean.NewsData newsData=news.get(position);
            String imageUrl = Constans.BASE_URL+newsData.getListimage();
            //请求图片
            x.image().bind(viewHolder.iv_icon, imageUrl,imageOptions);
            //设置标题
            viewHolder.tv_title.setText(newsData.getTitle());
            //设置时间
            viewHolder.tv_time.setText(newsData.getPubdate());
            return convertView;
        }
    }

    static class ViewHolder{
        ImageView iv_icon;
        TextView tv_title;
        TextView tv_time;
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int i, float v, int i1) {
            //1.设置文本
            tv_title.setText(topNews.get(i).getTitle());
            //2.对应页面的点高亮--红色
            ll_point_group.getChildAt(prePosition).setEnabled(false);
            ll_point_group.getChildAt(i).setEnabled(true);
            prePosition=i;
        }

        @Override
        public void onPageSelected(int i) {

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

    private TabDetailPagerBean parasedJson(String json) {

        return new Gson().fromJson(json, TabDetailPagerBean.class);
    }

    class TaDetailPagerTopNewsAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return topNews.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view==o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            imageView.setBackgroundResource(R.drawable.home_scroll_default);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(imageView);
            TabDetailPagerBean.DataBean.TopnewsData  topnewsData = topNews.get(position);
            String imageUrl = Constans.BASE_URL+topnewsData.getTopimage();
            //使用xutils联网请求图片
//            x.image().bind(imageView, imageUrl);
            //使用glide请求图片
            Glide.with(context)
                    .load(imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
