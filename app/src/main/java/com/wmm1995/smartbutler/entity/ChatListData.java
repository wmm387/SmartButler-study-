package com.wmm1995.smartbutler.entity;

import android.content.Context;

import com.wmm1995.smartbutler.adapter.ChatListAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/2/13.
 */

public class ChatListData {

    private int type;

    //文本
    private String text;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
