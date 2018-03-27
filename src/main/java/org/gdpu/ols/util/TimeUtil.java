package org.gdpu.ols.util;

public class TimeUtil {

    public static String getHour(String date){

        return String.valueOf(Long.parseLong(date)/(24*60*60))+"h";
    }
}
