package com.wmm1995.smartbutler.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.wmm1995.smartbutler.R;
import com.wmm1995.smartbutler.adapter.WeChatAdapter;
import com.wmm1995.smartbutler.entity.WeChatData;
import com.wmm1995.smartbutler.ui.WebViewActivity;
import com.wmm1995.smartbutler.utils.StaticClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/2/10.
 * 微信精选
 */

public class WechatFragment extends Fragment {

    private ListView mListView;
    private List<WeChatData> mList = new ArrayList<>();

    private List<String> mListTitle = new ArrayList<>();//标题
    private List<String> mListUrl = new ArrayList<>();//地址

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wechat, null);
        findView(view);
        return view;
    }

    //初始化View
    private void findView(View view) {
        mListView = (ListView) view.findViewById(R.id.mListView);

        //解析接口
        String url = "http://v.juhe.cn/weixin/query?key="+ StaticClass.WECHAT_KEY;
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                parsingJson(t);
            }
        });

        //点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("title", mListTitle.get(position));
                intent.putExtra("url", mListUrl.get(position));
                startActivity(intent);
            }
        });
    }

    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonResult = jsonObject.getJSONObject("result");
            JSONArray jsonList = jsonResult.getJSONArray("list");

            for (int i = 0; i < jsonList.length(); i++) {
                JSONObject json = (JSONObject) jsonList.get(i);
                WeChatData data = new WeChatData();

                String title = json.getString("title");
                String url = json.getString("url");

                data.setTitle(title);
                data.setSource(json.getString("source"));
                data.setImgUrl(json.getString("firstImg"));

                mList.add(data);

                mListTitle.add(title);
                mListUrl.add(url);
            }

            WeChatAdapter adapter = new WeChatAdapter(getActivity(),mList);
            mListView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}