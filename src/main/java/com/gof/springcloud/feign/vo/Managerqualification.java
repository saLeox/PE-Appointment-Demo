package com.gof.springcloud.feign.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 客户经理销售资质信息
 * </p>
 *
 * @author ss
 * @since 2021-05-06
 */
@ApiModel(value="Managerqualification对象", description="客户经理销售资质信息")
public class Managerqualification implements Serializable {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "客户经理主键")
	@NotNull(message = "客户经理主键不能为空")
	private Integer managerId;

	@ApiModelProperty(value = "客户经理资质 (1具备/0不具备)")
	@NotNull(message = "客户经理资质不能为空")
	@Range(min = 0, max = 1, message = "客户经理资质取值为0~1")
	private Integer haspri;


    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getHaspri() {
        return haspri;
    }

    public void setHaspri(Integer haspri) {
        this.haspri = haspri;
    }

    @Override
    public String toString() {
        return "Managerqualification{" +
        "managerId=" + managerId +
        ", haspri=" + haspri +
        "}";
    }
}
