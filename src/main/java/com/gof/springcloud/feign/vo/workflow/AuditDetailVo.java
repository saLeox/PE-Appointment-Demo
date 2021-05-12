package com.gof.springcloud.feign.vo.workflow;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value="Approval detail")
public class AuditDetailVo {
	private AuditRecord record;
	private List<AuditRecordLink> link;
	private List<AuditRecordNode> node;
}
