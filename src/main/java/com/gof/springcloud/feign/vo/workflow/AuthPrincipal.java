package com.gof.springcloud.feign.vo.workflow;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;
@Data
@ApiModel(value="AuthPrincipal", description="dont need to fill in")
public class AuthPrincipal {
	private String tokenValue;
	private String userName;
	private Integer orgId;
	private String orgCode;
	private Integer company;
	private List<String> role;
}
