package com.kkSpringBoot2.oauth2Security.utils;

import java.util.Date;

/**
 * description: 时间工具
 * author: ckk
 * create: 2019-04-15 22:33
 */
public class DateUtils {

    /**
     * 获取精确到秒的时间戳
     * @return
     */
    public static int getSecondTimestamp(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0,length-3));
        } else {
            return 0;
        }
    }
}
