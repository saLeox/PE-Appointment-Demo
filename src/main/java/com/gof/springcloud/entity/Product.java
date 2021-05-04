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
 * 私募产品信息
 * </p>
 *
 * @author ss
 * @since 2021-05-04
 */
@TableName("Product")
@ApiModel(value="Product对象", description="私募产品信息")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "产品主键")
    @TableId(value = "product_id", type = IdType.AUTO)
    private Integer productId;

    @ApiModelProperty(value = "产品代码")
    private Integer prodoctCode;

    @ApiModelProperty(value = "产品全称")
    private String productName;

    @ApiModelProperty(value = "销售日期")
    private Date saleDate;

    @ApiModelProperty(value = "投资人数上限")
    private Integer investorLimit;

    @ApiModelProperty(value = "资金规模 (元)")
    private Float fundCapacity;

    @ApiModelProperty(value = "基金管理人")
    private String fundManager;

    @ApiModelProperty(value = "冷却期 (天)")
    private Integer coolingPeriod;

    @ApiModelProperty(value = "最小认购金额 (元)")
    private Float minimumInvestment;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProdoctCode() {
        return prodoctCode;
    }

    public void setProdoctCode(Integer prodoctCode) {
        this.prodoctCode = prodoctCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getInvestorLimit() {
        return investorLimit;
    }

    public void setInvestorLimit(Integer investorLimit) {
        this.investorLimit = investorLimit;
    }

    public Float getFundCapacity() {
        return fundCapacity;
    }

    public void setFundCapacity(Float fundCapacity) {
        this.fundCapacity = fundCapacity;
    }

    public String getFundManager() {
        return fundManager;
    }

    public void setFundManager(String fundManager) {
        this.fundManager = fundManager;
    }

    public Integer getCoolingPeriod() {
        return coolingPeriod;
    }

    public void setCoolingPeriod(Integer coolingPeriod) {
        this.coolingPeriod = coolingPeriod;
    }

    public Float getMinimumInvestment() {
        return minimumInvestment;
    }

    public void setMinimumInvestment(Float minimumInvestment) {
        this.minimumInvestment = minimumInvestment;
    }

    @Override
    public String toString() {
        return "Product{" +
        "productId=" + productId +
        ", prodoctCode=" + prodoctCode +
        ", productName=" + productName +
        ", saleDate=" + saleDate +
        ", investorLimit=" + investorLimit +
        ", fundCapacity=" + fundCapacity +
        ", fundManager=" + fundManager +
        ", coolingPeriod=" + coolingPeriod +
        ", minimumInvestment=" + minimumInvestment +
        "}";
    }
}
