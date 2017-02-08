package eyedsion.soft.eyedsionhis.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import eyedsion.soft.eyedsionhis.application.Contant;
/**
 * Created by Administrator on 09/03/2016.
 * Describe :
 */
public class EyedsionTimer {

    public static int getAge(String birthDay) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        if (birthDay == null)
            return 0;
        try {
            date = myFormatter.parse(birthDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(date);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            } else {
                age--;
            }
        }

        return age;
    }

    public static int getAgeTData(String birthDay) {
        Date date = null;

        date = getTDate(birthDay);
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(date);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            } else {
                age--;
            }
        }

        return age;
    }

    //年月日
    public static String GetTDataString(String time) {
        try {
            String ss[] = time.split("T");
            return ss[0];
        } catch (Exception e) {
            return "";
        }

    }

    //月日时间

    public static String GetTDataYueString(String time) {
        String ss[] = time.split("T");
        String s[] = ss[0].split("-");
        String s1[] = ss[1].split(":");
        return s[1] + "-" + s[2] + " " + s1[0] + ":" + s1[1];
    }

    public static String getMonthDay(String time) {
        String ss[] = time.split("T");
        String s[] = ss[0].split("-");
        String s1[] = ss[1].split(":");
        return s[1] + "月" + s[2] + "日";
    }

    public static String getMonthDay2(String time) {
        if (time == null)
            return " ";
        String ss[] = time.split("T");
        String s[] = ss[0].split("-");
        String s1[] = ss[1].split(":");
        return s[1] + "/" + s[2];
    }


    public static String getDay(String time) {
        String ss[] = time.split("T");
        String s[] = ss[0].split("-");
        String s1[] = ss[1].split(":");
        return s[2] + "日";
    }

    public static String getTime(String time) {
        if (time == null || time.length() == 0) {
            return "00:00";
        }

        String ss[] = time.split("T");
        String s[] = ss[0].split("-");
        String s1[] = ss[1].split(":");
        return s1[0] + ":" + s1[1];

    }

    public static String getHour(String time) {
        String ss[] = time.split("T");
        String s[] = ss[0].split("-");
        String s1[] = ss[1].split(":");
        return s1[0] + "时";
    }


    public static Date getTDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.US);
        Date date = null;
        try {

            time = time.replaceAll("T", " ");
            date = sdf.parse(time);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 返回unix时间戳 (1970年至今的秒数)
     *
     * @return
     */
    public static long getUnixStamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 得到昨天的日期
     *
     * @return
     */
    public static String getYestoryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String yestoday = sdf.format(calendar.getTime());
        return yestoday;
    }

    /**
     * 得到今天的日期
     *
     * @return
     */
    public static Date getTodayDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());

        Date dateString = null;
        try {
            dateString = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateString;
    }



    public static Date getNowDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = sdf.format(new Date());

        Date dateString = null;
        try {
            dateString = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateString;
    }


    /**
     * 时间戳转化为时间格式
     *
     * @param timeStamp
     * @return
     */
    public static String timeStampToStr(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(timeStamp * 1000);
        return date;
    }

    /**
     * 得到日期   yyyy-MM-dd
     *
     * @param timeStamp 时间戳
     * @return
     */
    public static String formatDate(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(timeStamp * 1000);
        return date;
    }

    /**
     * 得到时间  HH:mm:ss
     *
     * @param timeStamp 时间戳
     * @return
     */
    public static String getTime(long timeStamp) {
        String time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(timeStamp * 1000);
        String[] split = date.split("\\s");
        if (split.length > 1) {
            time = split[1];
        }
        return time;
    }

    /**
     * 得到时间  yyyy-MM-dd
     *
     * @param timeStamp 时间戳
     * @return
     */
    public static String getMonthDay(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(timeStamp * 1000);
        String[] strings = date.split("\\s");
        if (strings.length > 0) {
            return strings[0];
        }
        return "";
    }

    public static String getTMonthDay(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(timeStamp * 1000);
        date = date.replaceAll(" ","T");
        return date;
    }

    /**
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param timeStamp
     * @return
     */
    public static String convertTimeToFormat(long timeStamp) {
        timeStamp = timeStamp / 1000;
        long curTime = System.currentTimeMillis() / (long) 1000;
        long time = curTime - timeStamp;

        if (time < 0)
            return "刚刚";
        if (time < 60 && time >= 0) {
            return "刚刚";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时前";
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {
            return time / 3600 / 24 + "天前";
        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 + "个月前";
        } else if (time >= 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 / 12 + "年前";
        } else {
            return "很久很久以前";
        }
    }

    /**
     * 将一个时间戳转换成提示性时间字符串，(多少分钟)
     *
     * @param timeStamp
     * @return
     */
    public static String timeStampToFormat(long timeStamp) {
        long curTime = System.currentTimeMillis() / (long) 1000;
        long time = curTime - timeStamp;
        return time / 60 + "";
    }

    public static long getDayInit(long time) {
        return time - time % (3600 * 24 * 1000) - 3600 * 8 * 1000;
    }

    public static long getWeekInit(long time) {
        return time - (time + 3600 * 24 * 1000 * 3) % (3600 * 24 * 1000 * 7) - 3600 * 8 * 1000;
    }

    public static long getMonthInit(int month) {

        Date date = new Date(Long.valueOf(Contant.GetCurrueTime()));
        date = new Date(date.getYear(), date.getMonth() - month, 1);
        return date.getTime();
    }

    public static int getDayOfWeek(String time) {
//星期一是第一天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int dayForWeek = 1;
        if (c.get(Calendar.DAY_OF_WEEK) == 1 ) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    public static List<Date> dateToWeek(Date mdate) {
        int b = mdate.getDay();
        if(b==0)
            b=7;
        Date fdate;
        List<Date> list = new ArrayList<Date>();
        Long fTime = mdate.getTime() - b * 24 * 3600000;
        for (int a = 1; a <= 7; a++) {
            fdate = new Date();
            fdate.setTime(fTime + (a * 24 * 3600000));
            list.add(a-1, fdate);
        }
        return list;
    }
}
