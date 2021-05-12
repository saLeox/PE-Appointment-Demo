package com.gof.springcloud.feign.vo.workflow;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <p>
 *
 * </p>
 *
 * @author ss
 * @since 2021-02-19
 */
@ApiModel(value="AuditRecordLink obj", description="")
@Builder
public class AuditRecordLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "linkID", type = IdType.AUTO)
    private Integer linkid;

    @TableField("modelLink")
    private String modellink;

    @TableField("startNode")
    private Integer startnode;

    @TableField("endNode")
    private Integer endnode;

    @TableField("auditID")
    private Integer auditid;


    public Integer getLinkid() {
        return linkid;
    }

    public void setLinkid(Integer linkid) {
        this.linkid = linkid;
    }

    public String getModellink() {
        return modellink;
    }

    public void setModellink(String modellink) {
        this.modellink = modellink;
    }

    public Integer getStartnode() {
        return startnode;
    }

    public void setStartnode(Integer startnode) {
        this.startnode = startnode;
    }

    public Integer getEndnode() {
        return endnode;
    }

    public void setEndnode(Integer endnode) {
        this.endnode = endnode;
    }

    public Integer getAuditid() {
        return auditid;
    }

    public void setAuditid(Integer auditid) {
        this.auditid = auditid;
    }

    @Override
    public String toString() {
        return "AuditRecordLink{" +
        "linkid=" + linkid +
        ", modellink=" + modellink +
        ", startnode=" + startnode +
        ", endnode=" + endnode +
        ", auditid=" + auditid +
        "}";
    }
}
