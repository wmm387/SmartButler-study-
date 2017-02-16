package com.wmm1995.smartbutler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.wmm1995.smartbutler.R;
import com.wmm1995.smartbutler.entity.GirlData;
import com.wmm1995.smartbutler.utils.PicassoUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/2/14.
 */

public class GirlAdapter extends BaseAdapter {

    private Context mContext;
    private List<GirlData> mList;
    private LayoutInflater inflater;
    private GirlData data;

    private WindowManager windowManager;
    private int width;//屏幕宽度

    public GirlAdapter(Context mContext, List<GirlData> mList) {
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
            convertView = inflater.inflate(R.layout.girl_item, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
            convertView.setTag(viewHolder);//缓存
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        data = mList.get(position);
        //解析图片
        String url = data.getImgUrl();
        PicassoUtils.loadImageViewSize(mContext,url,width/2,500,viewHolder.imageView);

        return convertView;
    }

    class ViewHolder {
        private ImageView imageView;
    }
}
