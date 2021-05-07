package com.gof.springcloud.feign.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 客户信息
 * </p>
 *
 * @author ss
 * @since 2021-05-06
 */
@ApiModel(value="Client对象", description="客户信息")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户主键")
	@NotNull(message = "客户主键不能为空")
    private Integer clientId;

    @ApiModelProperty(value = "客户年龄")
    @NotNull(message = "客户年龄不能为空")
    @Range(min = 0, message = "客户年龄不能为负数")
    private Integer age;

    @ApiModelProperty(value = "客户风险等级标识 (1-5)")
    @NotNull(message = "客户风险等级标识不能为空")
    @Range(min = 1, max = 5, message = "客户风险等级标识取值为1~5")
    private Integer risklevel;

    @ApiModelProperty(value = "专业投资者标识")
    @NotNull(message = "专业投资者标识不能为空")
    private Integer proftype;


    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getRisklevel() {
        return risklevel;
    }

    public void setRisklevel(Integer risklevel) {
        this.risklevel = risklevel;
    }

    public Integer getProftype() {
        return proftype;
    }

    public void setProftype(Integer proftype) {
        this.proftype = proftype;
    }

    @Override
    public String toString() {
        return "Client{" +
        "clientId=" + clientId +
        ", age=" + age +
        ", risklevel=" + risklevel +
        ", proftype=" + proftype +
        "}";
    }
}
