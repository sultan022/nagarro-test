package com.nagarro.test.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {


    public static Date parseToDate(String stringOfDate, String formatToBeParsed)  {
        if (stringOfDate != null && !stringOfDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat(formatToBeParsed);
            try {
                return  format.parse(stringOfDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

       return null;
    }
    public static String changeStringDateFormat(String strDate,String inputFormat, String formatRequired)  {
        SimpleDateFormat format1 = new SimpleDateFormat(inputFormat);
        Date dateValue = null;
        try {
            dateValue = format1.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        SimpleDateFormat format2 = new SimpleDateFormat(formatRequired);
        return format2.format(dateValue);

    }

}
