package com.gof.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gof.springcloud.entity.Confirmschedule;
import com.gof.springcloud.vo.ResultVo;

/**
 * <p>
 * 用户确认计划 服务类
 * </p>
 *
 * @author ss
 * @since 2021-05-04
 */
public interface ConfirmscheduleService extends IService<Confirmschedule> {

	public ResultVo<String> schedule(int approvalId, int key);

	public ResultVo<String> failure (int approvalId, int key);

	public ResultVo<String> success (int approvalId, int key);
}
