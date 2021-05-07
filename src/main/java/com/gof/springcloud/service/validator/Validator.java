package com.gof.springcloud.service.validator;

import com.gof.springcloud.vo.ResultVo;

public interface Validator<Q> {

	<T> ResultVo<Q> validate(T t);
}
