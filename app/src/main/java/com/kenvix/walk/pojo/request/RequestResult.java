package com.kenvix.walk.pojo.request;

public abstract class RequestResult<T> {
    private int status = 0;
    private String info = "";
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public abstract T getData();
    public abstract void setData(T data);
}