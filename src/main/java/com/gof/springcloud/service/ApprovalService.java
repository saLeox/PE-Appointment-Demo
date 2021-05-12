package com.gof.springcloud.service;

import com.gof.springcloud.feign.vo.workflow.AuditDetailVo;

/**
 * <p>
 * 私募流程审批 服务类
 * </p>
 *
 * @author ss
 * @since 2021-05-04
 */
public interface ApprovalService {

	/**
	 *  开始审批流程
	 * @param token
	 * @param appointmentID
	 * @return approvalId
	 */
	public Integer startProcess(String token, Integer clientID, Integer productID);

	public AuditDetailVo getApprovalDetail(Integer approvalId);
}
