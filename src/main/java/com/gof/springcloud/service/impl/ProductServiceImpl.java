package com.gof.springcloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.gof.springcloud.entity.Availability;
import com.gof.springcloud.entity.Product;
import com.gof.springcloud.mapper.AvailabilityMapper;
import com.gof.springcloud.mapper.ProductMapper;
import com.gof.springcloud.service.ProductService;

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

	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private AvailabilityMapper availabilityMapper;

	@Override
	@Transactional
	public boolean save(Product entity) {
		boolean res1 = SqlHelper.retBool(productMapper.insert(entity));
		Availability availability = Availability.builder()
				.productId(entity.getProductId())
				.fundAvailability(entity.getFundCapacity())
				.investorAvailability(entity.getInvestorLimit())
				.build();
		boolean res2 = SqlHelper.retBool(availabilityMapper.insert(availability));
		return res1 && res2;
	}

}
