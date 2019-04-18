package com.springboot.mybatisplus.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//分页工具：
// 1、easyui只需2个属性，total(总数),datas（分页数据）就能实现分页
// 2、layui 需4个属性，code,msg,count(总数),data（分页数据）实现分页
@Data
public class PageList<T> {
    private int code=1;
    private String msg;

    private long count;//总条数
    private List<T> data = new ArrayList<>();//装前台当前页的数据

    //提供有参构造方法，方便测试
    public PageList(long count, List<T> data) {
        this.count = count;
        this.data = data;
    }
    //除了有参构造方法，还需要提供一个无参构造方法
    public PageList() { }
}
