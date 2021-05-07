package com.gof.springcloud.feign.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

/**
 * <p>
 * 呼叫中心任务
 * </p>
 *
 * @author ss
 * @since 2021-05-07
 */
@Builder
@TableName("CallTask")
@ApiModel(value="Calltask对象", description="呼叫中心任务")
public class Calltask implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "任务主键")
    @TableId(value = "task_id", type = IdType.AUTO)
    private Integer taskId;

    @NotNull
    @ApiModelProperty(value = "销售经理主键")
    private Integer managerId;

    @NotNull
    @ApiModelProperty(value = "客户主键")
    private Integer clientId;

    @NotNull
    @ApiModelProperty(value = "预约主键")
    private Integer appointmentId;

    @NotNull
    @ApiModelProperty(value = "产品代码")
    private String productCode;

    @ApiModelProperty(value = "执行状态 0未执行/1已执行")
    private Integer status;


    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
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

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Calltask{" +
        "taskId=" + taskId +
        ", managerId=" + managerId +
        ", clientId=" + clientId +
        ", appointmentId=" + appointmentId +
        ", productCode=" + productCode +
        ", status=" + status +
        "}";
    }
}
