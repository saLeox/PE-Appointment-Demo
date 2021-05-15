package com.gof.springcloud.job;

import java.util.Date;
import java.util.List;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gof.springcloud.entity.Confirmschedule;
import com.gof.springcloud.mapper.ConfirmscheduleMapper;
import com.gof.springcloud.rabbit.DelayedSender;
import com.gof.springcloud.utils.TimeUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ConfirmScheduleJob implements SimpleJob {

	@Autowired
	private ConfirmscheduleMapper confirmscheduleMapper;
	@Autowired
	private DelayedSender delayedSender;

	@Override
	public void execute(ShardingContext shardingContext) {
		// shardingItem为shardIdx,
		// shardingParameter为shardingItemParameters中设置的val(k1=v1,k2=v2)
		List<Confirmschedule> arr = confirmscheduleMapper.queryUpcomingConfirmscheduleByMod(
				shardingContext.getShardingTotalCount(), shardingContext.getShardingItem());
		log.info("shardTotal:{}, shardIdx:{}, num:{}", shardingContext.getShardingTotalCount(),
				shardingContext.getShardingItem(), arr.size());
		if (arr.size() == 0) return;
		// send the confirm key to delayed queue to execute the task
		for (Confirmschedule schedule : arr) {
			log.info("Send confirm:{} to the delayed queue", schedule.getScheduleId().toString());
			delayedSender.delayedMessage(schedule.getScheduleId().toString(),
					TimeUtils.getMilliSecondGap(new Date(), schedule.getCoolingEnd()));
		}
	}

}
