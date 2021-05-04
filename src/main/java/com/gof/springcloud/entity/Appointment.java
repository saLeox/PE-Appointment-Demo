package com.gof.springcloud.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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

    @ApiModelProperty(value = "产品主键")
    private Integer productId;

    @ApiModelProperty(value = "经理主键")
    private Integer managerId;

    @ApiModelProperty(value = "客户主键")
    private Integer clientId;

    @ApiModelProperty(value = "认购数量 (元)")
    private Float subscribeQty;

    @ApiModelProperty(value = "认购时间")
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

    public Date getSubscribeTime() {
        return subscribeTime;
    }

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