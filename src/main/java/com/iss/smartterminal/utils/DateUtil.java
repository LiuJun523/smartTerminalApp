package com.iss.smartterminal.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil{

    private static SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
    private static final String EMPTY = "";

    /**
     * 转换Date为String
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static String convertDate( Date date ) throws Exception{

        String dateValue = EMPTY;

        if( date != null ){
            dateValue = dateFormat.format( date );
        }

        return dateValue;
    }

    /**
     * 格式化时间.做异常处理
     *
     * @param dt
     * @param fmt
     * @return
     * @throws Throwable
     */
    public static String DateTimeFormat( Date dt, String fmt ){

        if( dt == null ){
            return "";
        }
        SimpleDateFormat sdf;
        try{
            sdf = new SimpleDateFormat( fmt );
        }
        catch( Throwable tb ){
            sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        }
        String time = "";
        if( getDateCompareTo( dt, new Date() ) == -1 ){
            time = sdf.format( dt );
        }
        else{
            time = "today";
        }

        return time;
    }

    /**
     * 获得到几天前的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore( Date d, int day ){

        Calendar now = Calendar.getInstance();

        now.setTime( d );
        now.set( Calendar.DATE, now.get( Calendar.DATE ) - day );

        return now.getTime();
    }
    
    /**
     * 获得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter( Date d, int day ){

        Calendar now = Calendar.getInstance();

        now.setTime( d );
        now.set( Calendar.DATE, now.get( Calendar.DATE ) + day );

        return now.getTime();
    }

    /**
     * 字符串转化为日期
     */
    public static Date str2Date( String dateStr, String pattern ){

        try{
            DateFormat parser = new SimpleDateFormat( pattern );
            return parser.parse( dateStr );
        }
        catch( ParseException ex ){
            return null;
        }
    }

    /**
     * 字符串转化为日期
     */
    public static String date2str( Date date, String pattern ){

        String result = "";

        try{
            DateFormat parser = new SimpleDateFormat( pattern );
            result = parser.format( date );
        }
        catch( Exception ex ){

        }

        return result;
    }

    /**
     * 比较时间大小 ,前面比后面小返回-1，相等返回0，更大返回1
     *
     * @param startTime
     * @param EndTime
     * @return
     */
    public static Integer getDateCompareTo( Date startTime, Date EndTime ){

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime( startTime );
        c2.setTime( EndTime );

        int result = c1.compareTo( c2 );
        return result;

    }

    /**
     * 计算两个日期相差的秒数，如果date2 > date1 返回正数，否则返回负数
     */
    public static long dayDiff( Date date1, Date date2 ){

        return ( date2.getTime() - date1.getTime() ) / 1000;
    }

    /**
     * 精确计算相隔天数的方法
     *
     * @param startTime
     * @param EndTime
     * @return
     */
    public static int getIntervalDays( Date startTime, Date EndTime ){

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime( startTime );
        c2.setTime( EndTime );

        if( c1.after( c2 ) ){
            Calendar swap = c1;
            c1 = c2;
            c2 = swap;
        }
        int days = c2.get( Calendar.DAY_OF_YEAR ) - c1.get( Calendar.DAY_OF_YEAR );
        int y2 = c2.get( Calendar.YEAR );
        if( c1.get( Calendar.YEAR ) != y2 ){
            c1 = ( Calendar )c1.clone();
            do{
                days += c1.getActualMaximum( Calendar.DAY_OF_YEAR );// 得到当年的实际天数
                c1.add( Calendar.YEAR, 1 );
            }while( c1.get( Calendar.YEAR ) != y2 );
        }

        return days;
    }

    /**
     * 根据当前日期对象获取格式后的日期字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String getFormateDateByDate( Date date, String format ){

        SimpleDateFormat sdf = null;

        try{
            if( format == null || format.length() < 0 ){
				sdf = new SimpleDateFormat( "yyyy-MM-dd" );
            }
            else{
				sdf = new SimpleDateFormat( format );
            }
        }
        catch( Exception e ){
            sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        }

        String time = sdf.format( new Date() );
        return time;
    }
}