package com.gof.springcloud.entity;

public class ResultVo<T> {

    private String msg;
    private boolean success;
    private int code; //response code (200、400、500)
    private T obj;

    public ResultVo(boolean success){
        this.success = success;
    }

    public ResultVo(String msg,boolean success){
        this.msg = msg;
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
