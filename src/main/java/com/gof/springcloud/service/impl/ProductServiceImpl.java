package com.gof.springcloud.service.impl;

import com.gof.springcloud.entity.Product;
import com.gof.springcloud.mapper.ProductMapper;
import com.gof.springcloud.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 私募产品信息 服务实现类
 * </p>
 *
 * @author ss
 * @since 2021-05-04
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
