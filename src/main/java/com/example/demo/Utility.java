package com.example.demo;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Utility {
    public static String getUTCFormattedDate()
    {
        ZonedDateTime currentDateTime = ZonedDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssxxx");
        return currentDateTime.format(formatter);
    }
}
