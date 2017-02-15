package com.wmm1995.smartbutler.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.wmm1995.smartbutler.R;
import com.wmm1995.smartbutler.adapter.CourierAdapter;
import com.wmm1995.smartbutler.entity.CourierData;
import com.wmm1995.smartbutler.utils.L;
import com.wmm1995.smartbutler.utils.StaticClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TODO:
 * 1，快递公司下拉菜单选择
 * 2，快递未查询到和无物流信息的显示
 * 3，更好的快递API接口
 * 4，可保持某个快递，方便以后继续查看
 */

public class CourierActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_name,et_number;
    private Button btn_get_courier;
    private ListView mListView;

    private List<CourierData> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courier);

        initView();
    }

    //初始化View
    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_number = (EditText) findViewById(R.id.et_number);
        mListView = (ListView) findViewById(R.id.mListView);
        btn_get_courier = (Button) findViewById(R.id.btn_get_courier);
        btn_get_courier.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_courier:
                //获取输入框的内容
                String name = et_name.getText().toString();
                String number = et_number.getText().toString();

                //拼接我们的URL
                String url = "http://v.juhe.cn/exp/index?key=" + StaticClass.COURIER_KEY
                        + "&com=" + name + "&no=" + number;
                
                //判断是否为空
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(number)) {
                    //拿到数据去请求数据（JSon）
                    RxVolley.get(url, new HttpCallback() {
                        @Override
                        public void onSuccess(String t) {
//                            Toast.makeText(CourierActivity.this, t, Toast.LENGTH_SHORT).show();
                            L.i("courier:" + t);
                            //解析数据
                            parsingJson(t);
                        }
                    });

                } else {
                    Toast.makeText(this, R.string.text_tost_empty, Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
    //解析数据方法
    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonResult = jsonObject.getJSONObject("result");
            JSONArray jsonArray = jsonResult.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);

                CourierData data = new CourierData();
                data.setRemark(json.getString("remark"));
                data.setZone(json.getString("zone"));
                data.setDatetime(json.getString("datetime"));
                mList.add(data);
            }

            Collections.reverse(mList);//倒序

            CourierAdapter adapter = new CourierAdapter(this, mList);
            mListView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
