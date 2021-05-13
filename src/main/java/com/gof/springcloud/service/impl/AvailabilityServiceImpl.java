package com.gof.springcloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gof.springcloud.entity.Availability;
import com.gof.springcloud.mapper.AvailabilityMapper;
import com.gof.springcloud.service.AvailabilityService;

/**
 * <p>
 * 私募产品剩余额度信息 服务实现类
 * </p>
 *
 * @author ss
 * @since 2021-05-04
 */
@Service
public class AvailabilityServiceImpl extends ServiceImpl<AvailabilityMapper, Availability> implements AvailabilityService {

	@Autowired
	private AvailabilityMapper availabilityMapper;

	@Override
	public boolean updateAvailability(int productId, float qty) {
		return availabilityMapper.updateAvailability(productId, qty);
	}

}
