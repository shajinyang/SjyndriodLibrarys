package com.zx.xsk.sutillibrary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by sjy on 2017/5/26.
 */

public class DateUtils {

    /**
     * 返回时间段所有日期
     * @param dBegin
     * @param dEnd
     * @return
     */
    public static List<Date> findDates(Date dBegin, Date dEnd)
    {
        List lDate = new ArrayList();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime()))
        {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }


    /**
     * 距离某一时间days天前的日期
     * @param date
     * @param days
     * @return
     */
    public static  Date  beforeData(Date date,int days){
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -days);  //设置为前beforeNum天
        return calendar.getTime();   //得到前beforeNum天的时间
    }


    /**
     * 将日期转化为  几月几日的格式
     * @param date
     * @return
     */
    public static String convertDateToMd(Date date){
        SimpleDateFormat df = null;
        String returnValue = "";
        if (date != null) {
            df = new SimpleDateFormat("MM月dd日");
            returnValue = df.format(date);
        }
        return returnValue;
    }

}
