package com.gof.springcloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.gof.springcloud.constants.Constants;
import com.gof.springcloud.feign.vo.workflow.AuditDetailVo;
import com.gof.springcloud.feign.vo.workflow.InitWorkflowParam;
import com.gof.springcloud.vo.ResultVo;


@FeignClient(name = "workflow")
public interface WorkflowFeign {

	/**
	 * Return the auditID
	 * @param token
	 * @param param
	 * @return
	 */
	@PostMapping("/auditRecord/initWorkflow")
	public ResultVo<Integer> initWorkflow(@RequestHeader(value = Constants.TOKEN_HEADER) String token,
			@RequestBody InitWorkflowParam param);

	/**
	 * Return the progress of approval
	 * @param audit
	 * @return
	 */
	@GetMapping(value = "/auditRecord/getProgress")
	public ResultVo<AuditDetailVo> getProgress(@RequestParam Integer audit);

}
