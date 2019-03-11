package com.geektech.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String formatDefoultDate(Date data) {
        return SimpleDateFormat.getDateInstance().format(data);
    }
}
