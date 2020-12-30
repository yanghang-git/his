package com.his.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Description: TO DO
 * Date: 20-12-16
 *
 * @author yh
 */
public class Util {
    private Util() {}

    public static boolean judgeRequestTypeIsAjax(HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        String xWith = request.getHeader("X-Requested-With");
        return accept != null && accept.contains("application/json") || xWith != null && xWith.equals("XMLHttpRequest");
    }


    public static String generateOrderFormId(String id) {
        if (StringUtils.isEmpty(id)) {
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "0001";
        }
        String oldDate = id.substring(0, 8);
        return  LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")).equals(oldDate)
                ? oldDate + beforeFullNumberForZero(4, Integer.parseInt(id.substring(8)) + 1 + "")
                : LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "0001";
    }


    public static String beforeFullNumberForZero(int i, String j) {
        if (j.length() == i) {
            return j;
        }
        StringBuilder result = new StringBuilder();
        for (int q = j.length(); q < i; q++) {
            result.append("0");
        }
        return result.toString() + j;
    }
}