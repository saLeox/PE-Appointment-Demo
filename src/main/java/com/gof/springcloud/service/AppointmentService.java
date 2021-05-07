package com.gof.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gof.springcloud.entity.Appointment;
import com.gof.springcloud.vo.ResultVo;

/**
 * <p>
 * 私募预约信息 服务类
 * </p>
 *
 * @author ss
 * @since 2021-05-04
 */
public interface AppointmentService extends IService<Appointment> {

	public ResultVo<String> validate(Appointment appointment);
}
