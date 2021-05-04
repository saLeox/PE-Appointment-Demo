package com.gof.springcloud.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gof.springcloud.entity.Product;

/**
 * <p>
 * 私募产品信息 Mapper 接口
 * </p>
 *
 * @author ss
 * @since 2021-05-04
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}
