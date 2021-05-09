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
@ApiModel(value="AuditRecordNode obj", description="")
@Builder
public class AuditRecordNode implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "audit node id")
    @TableId(value = "nodeID", type = IdType.AUTO)
    private Integer nodeid;

    @ApiModelProperty(value = "audit id, needed when update", required = false)
    @TableField("auditID")
    private Integer auditid;

    @ApiModelProperty(value = "define by each module", required = true)
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "defined by module, and used as an identify.", required = true)
    @TableField("operateType")
    private String operatetype;

    @ApiModelProperty(value = "save / submit / handle / done / inform", required = false)
    @TableField("nodeStatus")
    private String nodestatus;

    @ApiModelProperty(value = "the time when node is generated", required = false)
    @TableField("generatedTime")
    private Date generatedtime;

    @ApiModelProperty(value = "processer' s org", required = false)
    @TableField("auditOrgID")
    private Integer auditorgid;

    @ApiModelProperty(value = "processer' s role", required = false)
    @TableField("auditRole")
    private String auditrole;

    @ApiModelProperty(value = "(final) processer' s user id, can be preset", required = false)
    @TableField("auditUser")
    private String audituser;

    @ApiModelProperty(value = "final process time", required = false)
    @TableField("auditTime")
    private Date audittime;

    @ApiModelProperty(value = "remarks", required = false)
    @TableField("remarks")
    private String remarks;

    @ApiModelProperty(value = "whether need to mark as urgent, will rank more forward and show tag", required = false)
    @TableField("urgentFlag")
    private Integer urgentflag;


    public Integer getNodeid() {
        return nodeid;
    }

    public void setNodeid(Integer nodeid) {
        this.nodeid = nodeid;
    }

    public Integer getAuditid() {
        return auditid;
    }

    public void setAuditid(Integer auditid) {
        this.auditid = auditid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOperatetype() {
        return operatetype;
    }

    public void setOperatetype(String operatetype) {
        this.operatetype = operatetype;
    }

    public String getNodestatus() {
        return nodestatus;
    }

    public void setNodestatus(String nodestatus) {
        this.nodestatus = nodestatus;
    }

    public Date getGeneratedtime() {
        return generatedtime;
    }

    public void setGeneratedtime(Date generatedtime) {
        this.generatedtime = generatedtime;
    }

    public Integer getAuditorgid() {
        return auditorgid;
    }

    public void setAuditorgid(Integer auditorgid) {
        this.auditorgid = auditorgid;
    }

    public String getAuditrole() {
        return auditrole;
    }

    public void setAuditrole(String auditrole) {
        this.auditrole = auditrole;
    }

    public String getAudituser() {
        return audituser;
    }

    public void setAudituser(String audituser) {
        this.audituser = audituser;
    }

    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getUrgentflag() {
        return urgentflag;
    }

    public void setUrgentflag(Integer urgentflag) {
        this.urgentflag = urgentflag;
    }

    @Override
    public String toString() {
        return "AuditRecordNode{" +
        "nodeid=" + nodeid +
        ", auditid=" + auditid +
        ", title=" + title +
        ", operatetype=" + operatetype +
        ", nodestatus=" + nodestatus +
        ", generatedtime=" + generatedtime +
        ", auditorgid=" + auditorgid +
        ", auditrole=" + auditrole +
        ", audituser=" + audituser +
        ", audittime=" + audittime +
        ", remarks=" + remarks +
        ", urgentflag=" + urgentflag +
        "}";
    }
}
