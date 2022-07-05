package com.example.ecommerce.util;

public class Result<T> {

    //classify different code like "success", "no authorization"
    private int resultCode;

    private String message;

    private T data;

    public Result(){

    }

    public Result(int resultCode, String message, T data) {
        this.resultCode = resultCode;
        this.message = message;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
