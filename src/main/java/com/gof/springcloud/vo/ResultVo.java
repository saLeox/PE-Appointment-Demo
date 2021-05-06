package com.gof.springcloud.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResultVo<T> {

	private boolean success;
	private T obj;
	private Integer code; // response code (200、400、500)
	private String msg;

	public void success(T obj) {
		this.setSuccess(true);
		this.setObj(obj);
	}

	public void failure(Integer code, String msg) {
		this.setSuccess(false);
		this.setCode(code);
		this.setMsg(msg);
	}

}
