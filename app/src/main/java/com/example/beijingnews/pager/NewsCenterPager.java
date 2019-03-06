package com.example.beijingnews.pager;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.beijingnews.Constans;
import com.example.beijingnews.acitity.MainActivity;
import com.example.beijingnews.base.BasePager;
import com.example.beijingnews.base.MenuDetailBasePager;
import com.example.beijingnews.domain.NewsCenterPagerBeam;
import com.example.beijingnews.fragment.LeftMenuFragment;
import com.example.beijingnews.menudetailpager.InteracMenuDetailPager;
import com.example.beijingnews.menudetailpager.NewsMenuDetailPager;
import com.example.beijingnews.menudetailpager.PhotoMenuDetailPager;
import com.example.beijingnews.menudetailpager.TopicMenuDetailPager;
import com.example.beijingnews.utils.CahcheUtils;
import com.example.beijingnews.utils.LogUtil;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class NewsCenterPager extends BasePager {

    List<NewsCenterPagerBeam.DataBean> data;//左侧菜单对应的数据集合
    private ArrayList<MenuDetailBasePager> menuDetailBasePagers;//详情页面的集合

    public NewsCenterPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        ib_menu.setVisibility(View.VISIBLE);
        LogUtil.e("新闻中心被初始化了");
        tv_title.setText("新闻中心");
        //联网请求数据，创建视图
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        //把子视图添加到BasePager的FrameLayout中
        fl_content.addView(textView);
        textView.setText("新闻");

        //绑定数据
        //获取缓存数据
        String saveJson = CahcheUtils.getString(context,Constans.NEWSCENTER_PAGER_URL);
//        if (saveJson!=null){
//            //.....还可能是空字符串，应用下面的
//        }
        if (!TextUtils.isEmpty(saveJson)){
            processData(saveJson);
        }
        //联网请求数据
        getDaraFromNet();
    }

    private void getDaraFromNet() {
        RequestParams params = new RequestParams(Constans.NEWSCENTER_PAGER_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                LogUtil.e("联网请求成功"+result);
                //缓存数据
                CahcheUtils.putString(context,Constans.NEWSCENTER_PAGER_URL,result);
                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("联网请求失败"+ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("联网请求取消"+ cex.getMessage());
            }

            @Override
            public void onFinished() {
                LogUtil.e("联网Finish");

            }
        });
    }

    /**
     * 解析json数据和显示数据
     * @param json
     */

    private void processData(String json) {

        NewsCenterPagerBeam newsCenterPagerBeam = parasedJson(json);
        String title = newsCenterPagerBeam.getData().get(0).getChildren().get(1).getTitle();
        LogUtil.e("json解析成功"+title);
        //给左侧菜单传递数据
        data = newsCenterPagerBeam.getData();
        MainActivity mainActivity = (MainActivity) context;
        //得到左侧菜单
        LeftMenuFragment leftMenuFragment = mainActivity.getLeftMenuFragment();
        //添加详情页面
        menuDetailBasePagers= new ArrayList<>();
        menuDetailBasePagers.add(new NewsMenuDetailPager(context,data.get(0)));
        menuDetailBasePagers.add(new TopicMenuDetailPager(context));
        menuDetailBasePagers.add(new PhotoMenuDetailPager(context));
        menuDetailBasePagers.add(new InteracMenuDetailPager(context));
        //把数据传递给左侧菜单
        leftMenuFragment.setData(data);

    }

    /**
     * 解析json数据：使用Gson
     * @param json
     * @return
     */
    private NewsCenterPagerBeam parasedJson(String json) {
        Gson gson = new Gson();
        NewsCenterPagerBeam beam = gson.fromJson(json, NewsCenterPagerBeam.class);

        return beam;
    }

    /**
     * 根据位置切换详情页面
     * @param position
     */
    public void swichPager(int position) {

//        MainActivity mainActivity = (MainActivity) context;
//        ContentFragment contentFragment = mainActivity.getContentFragment();
//        NewsCenterPager newsCenterPager = contentFragment.getNewsCenterPager();
//        newsCenterPager.swichPager(position);

        //1.设置标题
        tv_title.setText(data.get(position).getTitle());
        //2.移除之前内容
        fl_content.removeAllViews();//移除之前的视图

        //3.添加新内容
        MenuDetailBasePager detaiBasePager = menuDetailBasePagers.get(position);//
        View rootView = detaiBasePager.rootView;
        detaiBasePager.initData();//初始化数据


        fl_content.addView(rootView);
    }
}
