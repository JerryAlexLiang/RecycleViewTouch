package com.liangyang.recycleviewtouch.bean;

/**
 * 创建日期：2017/1/17 on 16:18
 * 作者:杨亮 liangyang
 * 描述:数据源，存储item的信息
 */
public class DataBean {

    private String name;
    private String time;
    private String message;
    private int image_id;

    public DataBean(String name, String time, String message, int image_id) {
        this.name = name;
        this.time = time;
        this.message = message;
        this.image_id = image_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }
}
