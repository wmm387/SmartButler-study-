package com.wmm1995.smartbutler.entity;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2017/2/12.
 * 用户属性
 */

public class MyUser extends BmobUser {

    private int age;
    private boolean sex;
    private String desc;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}