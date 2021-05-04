package com.gof.springcloud.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gof.springcloud.entity.Appointment;

/**
 * <p>
 * 私募预约信息 Mapper 接口
 * </p>
 *
 * @author ss
 * @since 2021-05-04
 */
@Mapper
public interface AppointmentMapper extends BaseMapper<Appointment> {

}
