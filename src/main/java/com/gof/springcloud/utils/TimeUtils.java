package com.gof.springcloud.utils;

import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

	/**
	 * Delay specific hour on given time
	 * @param curTime
	 * @param hour
	 * @return
	 */
	public static Date delayHour(Date curTime, int hour) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(curTime);
		cal.add(Calendar.HOUR, hour);// 24小时制
		return cal.getTime();
	}

	/**
	 * Calculate the milliSecond gap between given time1 (earlier) and time2 (later)
	 * @param t1
	 * @param t2
	 * @return
	 */
	public static int getMilliSecondGap(Date t1, Date t2) {
		return (int) (t2.getTime() - t1.getTime());
	}

}
