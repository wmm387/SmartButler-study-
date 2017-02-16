package com.wmm1995.smartbutler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wmm1995.smartbutler.R;
import com.wmm1995.smartbutler.entity.WeChatData;
import com.wmm1995.smartbutler.utils.PicassoUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/2/14.
 * 微信精选适配器
 */

public class WeChatAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<WeChatData> mList;
    private WeChatData data;

    private int width;
    private WindowManager windowManager;

    public WeChatAdapter(Context mContext, List<WeChatData> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        width = windowManager.getDefaultDisplay().getWidth();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.wechat_item, null);
            viewHolder.iv_img = (ImageView) convertView.findViewById(R.id.iv_img);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_source = (TextView) convertView.findViewById(R.id.tv_source);
            convertView.setTag(viewHolder);//设置缓存
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        data = mList.get(position);
        viewHolder.tv_title.setText(data.getTitle());
        viewHolder.tv_source.setText(data.getSource());

        //加载图片
        if (data.getImgUrl().isEmpty()) {
            viewHolder.iv_img.setImageResource(R.mipmap.ic_launcher);
        } else{
            PicassoUtils.loadImageViewSize(mContext,data.getImgUrl(),width/3,250,viewHolder.iv_img);
        }

        return convertView;
    }

    class ViewHolder {
        private ImageView iv_img;
        private TextView tv_title;
        private TextView tv_source;
    }
}
