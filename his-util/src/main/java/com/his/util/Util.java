package com.his.util;

import javax.servlet.http.HttpServletRequest;

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
}