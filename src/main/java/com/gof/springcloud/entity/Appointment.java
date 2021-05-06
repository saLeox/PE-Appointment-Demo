package com.gof.springcloud.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 私募预约信息
 * </p>
 *
 * @author ss
 * @since 2021-05-04
 */
@TableName("Appointment")
@ApiModel(value="Appointment对象", description="私募预约信息")
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "预约主键")
    @TableId(value = "appointment_id", type = IdType.AUTO)
    private Integer appointmentId;

    @NotNull
    @ApiModelProperty(value = "产品主键")
    private Integer productId;

    @NotNull
    @ApiModelProperty(value = "经理主键")
    private Integer managerId;

    @NotNull
    @ApiModelProperty(value = "客户主键")
    private Integer clientId;

    @NotNull
    @Size(min = 1, message = "认购数量必须大于0")
    @ApiModelProperty(value = "认购数量 (元)")
    private Float subscribeQty;

    @ApiModelProperty(value = "认购时间 /yyyy-MM-dd HH:mm:ss")
    private Date subscribeTime;

    @ApiModelProperty(value = "申请状态 (Pending / Success / Failure)")
    private String status;

    @ApiModelProperty(value = "审批主键")
    private Integer approvalId;


    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Float getSubscribeQty() {
        return subscribeQty;
    }

    public void setSubscribeQty(Float subscribeQty) {
        this.subscribeQty = subscribeQty;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getSubscribeTime() {
        return subscribeTime;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(Integer approvalId) {
        this.approvalId = approvalId;
    }

    @Override
    public String toString() {
        return "Appointment{" +
        "appointmentId=" + appointmentId +
        ", productId=" + productId +
        ", managerId=" + managerId +
        ", clientId=" + clientId +
        ", subscribeQty=" + subscribeQty +
        ", subscribeTime=" + subscribeTime +
        ", status=" + status +
        ", approvalId=" + approvalId +
        "}";
    }
}
