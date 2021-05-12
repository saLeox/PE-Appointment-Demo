package com.gof.springcloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gof.springcloud.constants.Constants;
import com.gof.springcloud.feign.WorkflowFeign;
import com.gof.springcloud.feign.vo.workflow.AuditDetailVo;
import com.gof.springcloud.feign.vo.workflow.AuditRecord;
import com.gof.springcloud.feign.vo.workflow.AuditRecordNode;
import com.gof.springcloud.feign.vo.workflow.InitWorkflowParam;
import com.gof.springcloud.service.ApprovalService;
import com.gof.springcloud.vo.ResultVo;

import lombok.extern.slf4j.Slf4j;
/**
 * <p>
 * 私募流程审批 服务实现类
 * </p>
 *
 * @author ss
 * @since 2021-05-04
 */
@Service
@Slf4j
public class ApprovalServiceImpl implements ApprovalService {

	@Autowired
	private WorkflowFeign workflowFeign;

	@Override
	public Integer startProcess(String token, Integer clientID, Integer productID) {

		AuditRecord audit = AuditRecord.builder()
				.auditmodel(Constants.WORKFLOW_KEY_APPOINTMENT)
				.status(Constants.WORKFLOW_STATUS_PENDING)
				.build();
		AuditRecordNode curNode = AuditRecordNode.builder()
				.title(String.format("私募产品%s 客户%s预约 - 营业部客户经理申请", productID, clientID))
				.operatetype(Constants.OPERATE_TYPE_PE_APPOINTMENT)
				.build();
		AuditRecordNode nextNode = AuditRecordNode.builder()
				.title(String.format("私募产品%s 客户%s预约 - 营业部领导审批", productID, clientID))
				.operatetype(Constants.OPERATE_TYPE_PE_APPOINTMENT)
				.build();

		InitWorkflowParam param = InitWorkflowParam.builder()
				.audit(audit)
				.curNode(curNode)
				.nextNode(nextNode).build();
		ResultVo<Integer> resultVo = workflowFeign.initWorkflow(token, param);
		// success
		if (resultVo.isSuccess()) return resultVo.getObj();
		// fail
		log.error("工作流初始化异常:{}", resultVo.getMsg());
		return null;
	}

	@Override
	public AuditDetailVo getApprovalDetail(Integer approvalId) {
		return workflowFeign.getProgress(approvalId).getObj();
	}

}
