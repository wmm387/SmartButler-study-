package com.wmm1995.smartbutler.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.wmm1995.smartbutler.R;
import com.wmm1995.smartbutler.adapter.GirlAdapter;
import com.wmm1995.smartbutler.entity.GirlData;
import com.wmm1995.smartbutler.ui.GirlActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/10.
 * 美女社区
 */

public class GirlFragment extends Fragment {

    private GridView mGridView;
    private List<GirlData> mList = new ArrayList<>();
    private GirlAdapter mAdapter;
    //图片地址的数据
    private List<String> mListUrl = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_girl, null);
        findView(view);
        return view;
    }

    //初始化
    private void findView(View view) {
        mGridView = (GridView) view.findViewById(R.id.mGridView);

        String welfare = null;
        try {
            //Gank升級 需要转码
            welfare = URLEncoder.encode(getString(R.string.text_welfare), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //解析
        RxVolley.get("http://gank.io/api/search/query/listview/category/"+welfare+"/count/20/page/1", new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                parsingJson(t);
            }
        });

        //监听点击事件
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),GirlActivity.class);
                String url = mListUrl.get(position).toString();
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });

    }

    //解析json
    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);
                String url = json.getString("url");

                mListUrl.add(url);

                GirlData data = new GirlData();
                data.setImgUrl(url);
                mList.add(data);
            }
            mAdapter = new GirlAdapter(getActivity(), mList);
            mGridView.setAdapter(mAdapter);//设置适配器

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
