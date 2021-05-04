package com.gof.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 用户确认计划
 * </p>
 *
 * @author ss
 * @since 2021-05-04
 */
@TableName("ConfirmSchedule")
@ApiModel(value="Confirmschedule对象", description="用户确认计划")
public class Confirmschedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户确认计划主键")
    @TableId(value = "schedule_id", type = IdType.AUTO)
    private Integer scheduleId;

    @ApiModelProperty(value = "预约主键")
    private Integer appointmentId;

    @ApiModelProperty(value = "冷却期开始时间")
    private Date coolingStart;

    @ApiModelProperty(value = "冷却期结束时间")
    private Date coolingEnd;

    @ApiModelProperty(value = "调用呼叫中心接口时间")
    private Date calloutTime;

    @ApiModelProperty(value = "呼叫中心返回结果时间")
    private Date callbackTime;

    @ApiModelProperty(value = "用户确认结果 (1成功 / 0失败)")
    private Integer result;


    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Date getCoolingStart() {
        return coolingStart;
    }

    public void setCoolingStart(Date coolingStart) {
        this.coolingStart = coolingStart;
    }

    public Date getCoolingEnd() {
        return coolingEnd;
    }

    public void setCoolingEnd(Date coolingEnd) {
        this.coolingEnd = coolingEnd;
    }

    public Date getCalloutTime() {
        return calloutTime;
    }

    public void setCalloutTime(Date calloutTime) {
        this.calloutTime = calloutTime;
    }

    public Date getCallbackTime() {
        return callbackTime;
    }

    public void setCallbackTime(Date callbackTime) {
        this.callbackTime = callbackTime;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Confirmschedule{" +
        "scheduleId=" + scheduleId +
        ", appointmentId=" + appointmentId +
        ", coolingStart=" + coolingStart +
        ", coolingEnd=" + coolingEnd +
        ", calloutTime=" + calloutTime +
        ", callbackTime=" + callbackTime +
        ", result=" + result +
        "}";
    }
}
