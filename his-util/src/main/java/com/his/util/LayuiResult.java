package com.his.util;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.List;

public class LayuiResult<T> implements Serializable {
    private static final long serialVersionUID = 8366306878124685218L;
    private long code;
    private String msg;
    private long count;
    private T data;

    public static LayuiResult<?> success(String msg) {
        LayuiResult<?> result = new LayuiResult<>();
        result.setCode(1);
        result.setMsg(msg);
        return result;
    }

    public static <Type> LayuiResult<Type> success(Type data) {
        LayuiResult<Type> result = new LayuiResult<>();
        result.setData(data);
        return result;
    }

    public static LayuiResult<?> failed(String msg) {
        LayuiResult<?> result = new LayuiResult<>();
        result.setCode(-1);
        result.setMsg(msg);
        return result;
    }

    public static <Type> LayuiResult<List<Type>> success(Page<Type> page) {
        LayuiResult<List<Type>> result = new LayuiResult<>();
        result.setData(page.getRecords());
        result.setCount(page.getTotal());
        return result;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LayuiResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
