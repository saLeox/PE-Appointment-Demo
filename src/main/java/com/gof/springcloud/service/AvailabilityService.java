package com.gof.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gof.springcloud.entity.Availability;

/**
 * <p>
 * 私募产品剩余额度信息 服务类
 * </p>
 *
 * @author ss
 * @since 2021-05-04
 */
public interface AvailabilityService extends IService<Availability> {

	public boolean updateAvailability(int productId, float qty);
}
