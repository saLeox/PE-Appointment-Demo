package com.gof.springcloud.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 私募产品剩余额度信息
 * </p>
 *
 * @author ss
 * @since 2021-05-04
 */
@TableName("Availability")
@ApiModel(value="Availability对象", description="私募产品剩余额度信息")
public class Availability implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "额度主键")
    @TableId(value = "availability_id", type = IdType.AUTO)
    private Integer availabilityId;

    @NotNull
    @ApiModelProperty(value = "产品主键")
    private Integer productId;

    @NotNull
    @Range(min = 0, message = "认购金额剩余额度必须大于等于0")
    @ApiModelProperty(value = "认购金额剩余额度")
    private Float fundAvailability;

    @NotNull
    @Range(min = 0, message = "认购人数剩余额度必须大于等于0")
    @ApiModelProperty(value = "认购人数剩余额度")
    private Integer investorAvailability;


    public Integer getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(Integer availabilityId) {
        this.availabilityId = availabilityId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Float getFundAvailability() {
        return fundAvailability;
    }

    public void setFundAvailability(Float fundAvailability) {
        this.fundAvailability = fundAvailability;
    }

    public Integer getInvestorAvailability() {
        return investorAvailability;
    }

    public void setInvestorAvailability(Integer investorAvailability) {
        this.investorAvailability = investorAvailability;
    }

    @Override
    public String toString() {
        return "Availability{" +
        "availabilityId=" + availabilityId +
        ", productId=" + productId +
        ", fundAvailability=" + fundAvailability +
        ", investorAvailability=" + investorAvailability +
        "}";
    }
}
