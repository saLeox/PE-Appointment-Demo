package com.gof.springcloud.feign.vo.workflow;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel(value="InitWorkflow Param", description="")
@Data
@Builder
public class InitWorkflowParam {

	@ApiModelProperty(required = true, name = "audit detail", value = "Audit detail")
	private AuditRecord audit;

	@ApiModelProperty(required = true, name = "curNode", value = "Current audit node")
	private AuditRecordNode curNode;

	@ApiModelProperty(required = false, name = "nextNode", value = "Next audit node, compulsory when need to pass to next step, except from iniation")
	private AuditRecordNode nextNode;

	@ApiModelProperty(required = false, name = "obj", value = "Obj used to be param when call external api")
	private Object obj;

	@ApiModelProperty(required = false, name = "auth", value = "Auth detail")
	private AuthPrincipal auth;


}
