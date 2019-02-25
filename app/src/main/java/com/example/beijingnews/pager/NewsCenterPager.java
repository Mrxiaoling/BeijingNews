package com.example.beijingnews.pager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.beijingnews.Constans;
import com.example.beijingnews.acitity.MainActivity;
import com.example.beijingnews.base.BasePager;
import com.example.beijingnews.domain.NewsCenterPagerBeam;
import com.example.beijingnews.fragment.LeftMenuFragment;
import com.example.beijingnews.utils.LogUtil;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

public class NewsCenterPager extends BasePager {

    List<NewsCenterPagerBeam.DataBean> data;//左侧菜单对应的数据集合

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
        textView.setText("新闻");
        //把子视图添加到BasePager的FrameLayout中
        fl_content.addView(textView);
        //绑定数据

        //联网请求数据
        getDaraFromNet();
    }

    private void getDaraFromNet() {
        RequestParams params = new RequestParams(Constans.NEWSCENTER_PAGER_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                LogUtil.e("联网请求成功"+result);
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
}
