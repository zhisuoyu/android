package utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class TimeUtils {


    public static void main(String[] args) {
        long ms = System.currentTimeMillis();
        long startMsOfDay = startMsOfDay(ms);
        System.out.println("same:" + disDay(startMsOfDay, startMsOfDay));
        System.out.println("-1:" + disDay(startMsOfDay, startMsOfDay - 1));
        System.out.println("-1:" + disDay(startMsOfDay - 1, startMsOfDay));
        System.out.println("+1:" + disDay(startMsOfDay, startMsOfDay + 1));
        System.out.println("+1:" + disDay(startMsOfDay, startMsOfDay + 1));


        System.out.println("-max:" + disDay(startMsOfDay - (MS_NUM_DAY - 1), startMsOfDay));
        System.out.println("-max:" + disDay(startMsOfDay, startMsOfDay - (MS_NUM_DAY - 1)));
        System.out.println("+max:" + disDay(startMsOfDay + (MS_NUM_DAY - 1), startMsOfDay));
        System.out.println("+max:" + disDay(startMsOfDay, startMsOfDay + (MS_NUM_DAY - 1)));
//        System.out.println(SDF_DATETIME_DEFAULT.format(passedMsOfDay(ms)));
//        System.out.println(SDF_DATETIME_DEFAULT.format(startMsOfDay(ms)));


        System.out.println("-day:" + disDay(startMsOfDay - (MS_NUM_DAY), startMsOfDay));
        System.out.println("-day:" + disDay(startMsOfDay, startMsOfDay - (MS_NUM_DAY)));
        System.out.println("+day:" + disDay(startMsOfDay + (MS_NUM_DAY), startMsOfDay));
        System.out.println("+day:" + disDay(startMsOfDay, startMsOfDay + (MS_NUM_DAY)));


        System.out.println("-day1:" + disDay(startMsOfDay - (MS_NUM_DAY + 1), startMsOfDay));
        System.out.println("-day1:" + disDay(startMsOfDay, startMsOfDay - (MS_NUM_DAY + 1)));
        System.out.println("+day1:" + disDay(startMsOfDay + (MS_NUM_DAY + 1), startMsOfDay));
        System.out.println("+day1:" + disDay(startMsOfDay, startMsOfDay + (MS_NUM_DAY + 1)));
    }

    public static final SimpleDateFormat SDF_DATETIME_DEFAULT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    public static final SimpleDateFormat SDF_DATE_DEFAULT = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    public static final SimpleDateFormat SDF_TIME_DEFAULT = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
    public static final SimpleDateFormat SDF_DM = new SimpleDateFormat("HH:mm", Locale.CHINA);

    public static final long MS_NUM_DAY = 24 * 60 * 60 * 1000;
    public static final long MS_NUM_ZONE = 8 * 60 * 60 * 1000;

    public static long passedMsOfDay(long ms) {
        return (ms + MS_NUM_ZONE) % MS_NUM_DAY;
    }

    public static long startMsOfDay(long ms) {
        return ms - passedMsOfDay(ms);
    }

    public static long disDay(long fromMs, long toMs) {
        return (toMs + MS_NUM_ZONE) / MS_NUM_DAY - (fromMs + MS_NUM_ZONE) / MS_NUM_DAY;
    }


}
