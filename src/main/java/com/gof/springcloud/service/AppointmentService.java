package com.gof.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gof.springcloud.entity.Appointment;
import com.gof.springcloud.vo.AppointmentDetail;
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

	/**
	 * Get detail of appointment
	 * @param key
	 * @return
	 */
	public AppointmentDetail getDetail(int key);

	public ResultVo<String> validate(Appointment appointment);

	/**
	 * Launch the appointment application process
	 * @param appointment
	 * @param token
	 * @return
	 */
	public ResultVo<Appointment> saveTransaction(Appointment appointment, String token);

	/**
	 * Cancel the appointment during the approval process
	 * @param approvalId
	 * @param key
	 * @return
	 */
	public ResultVo<String> cancel(int approvalId, int key);

	/**
	 * Validate whether the appointment is still pending
	 * @param approvalId
	 * @param appointmentId
	 * @return if true return the entity, otherwise return the false msg.
	 */
	public ResultVo<Appointment> legalValidate(int approvalId, int appointmentId);
}
