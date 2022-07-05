package com.travellers.community.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilManager {
    private static final DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Date formatDate(String date) {
        try {
            return sourceFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
