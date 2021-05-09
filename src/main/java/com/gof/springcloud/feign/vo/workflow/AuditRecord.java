package com.gof.springcloud.feign.vo.workflow;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

/**
 * <p>
 *
 * </p>
 *
 * @author ss
 * @since 2021-02-19
 */
@ApiModel(value="AuditRecord obj", description="")
@Builder
public class AuditRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "auditID", type = IdType.AUTO)
    @ApiModelProperty(value = "auditID, needed when update", required = false)
    private Integer auditid;

    @ApiModelProperty(value = "under which module", required = true)
    @TableField("auditModel")
    private Integer auditmodel;

    @ApiModelProperty(value = "save / pending/ finished", required = true)
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "submitUser", required = false)
    @TableField("submitUser")
    private String submituser;

    @ApiModelProperty(value = "submitTime", required = false)
    @TableField("submitTime")
    private Date submittime;


    public Integer getAuditid() {
        return auditid;
    }

    public void setAuditid(Integer auditid) {
        this.auditid = auditid;
    }

    public Integer getAuditmodel() {
        return auditmodel;
    }

    public void setAuditmodel(Integer auditmodel) {
        this.auditmodel = auditmodel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubmituser() {
        return submituser;
    }

    public void setSubmituser(String submituser) {
        this.submituser = submituser;
    }

    public Date getSubmittime() {
        return submittime;
    }

    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }

    @Override
    public String toString() {
        return "AuditRecord{" +
        "auditid=" + auditid +
        ", auditmodel=" + auditmodel +
        ", status=" + status +
        ", submituser=" + submituser +
        ", submittime=" + submittime +
        "}";
    }
}
