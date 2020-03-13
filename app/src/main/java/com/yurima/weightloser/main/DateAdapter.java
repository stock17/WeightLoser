package com.yurima.weightloser.main;

import android.arch.persistence.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateAdapter {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");


    long DAY = 1000 * 60 * 60 * 24;
    long WEEK = DAY * 7;

    public static Date getTodayTime(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


    public static String getTodayDate (){
        return simpleDateFormat.format(getTodayTime());
    }

    @TypeConverter
    public static String TimestampToString(Date date) {
        return simpleDateFormat.format(date);
    }

    @TypeConverter
    public static Date StringToTimeStamp(String date) {
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
