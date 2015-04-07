package com.jacob.zuimei.indicator.bean;

/**
 * Created by jacob-wj on 2015/4/7.
 */
public class AppBean {
   String name;
   int drawable;

    public AppBean(int drawable, String name) {
        this.drawable = drawable;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    @Override
    public String toString() {
        return "AppBean{" +
                "name='" + name + '\'' +
                ", drawable=" + drawable +
                '}';
    }
}
