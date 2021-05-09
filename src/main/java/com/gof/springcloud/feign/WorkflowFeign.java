package com.gof.springcloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.gof.springcloud.constants.Constants;
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
}
