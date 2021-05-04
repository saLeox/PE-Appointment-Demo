package com.gof.springcloud.service.impl;

import com.gof.springcloud.entity.Appointment;
import com.gof.springcloud.mapper.AppointmentMapper;
import com.gof.springcloud.service.AppointmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 私募预约信息 服务实现类
 * </p>
 *
 * @author ss
 * @since 2021-05-04
 */
@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment> implements AppointmentService {

}
