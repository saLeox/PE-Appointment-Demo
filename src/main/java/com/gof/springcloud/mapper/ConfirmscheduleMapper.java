package com.gof.springcloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gof.springcloud.entity.Confirmschedule;

/**
 * <p>
 * 用户确认计划 Mapper 接口
 * </p>
 *
 * @author ss
 * @since 2021-05-04
 */
@Mapper
public interface ConfirmscheduleMapper extends BaseMapper<Confirmschedule> {

	/**
	 * 根据mod 查询30分钟内即将解冻的呼叫确认事件
	 * @return
	 */
	public List<Confirmschedule> queryUpcomingConfirmscheduleByMod(@Param("shardTotal") int shardTotal,
			@Param("shardIdx") int shardIdx);

}
