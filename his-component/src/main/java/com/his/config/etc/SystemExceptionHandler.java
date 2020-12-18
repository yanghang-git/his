package com.his.config.etc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.his.util.LayuiResult;
import com.his.util.Util;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description: TO DO
 * Date: 20-12-12
 *
 * @author yh
 */
@ControllerAdvice
public class SystemExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(SystemExceptionHandler.class);
    private final ObjectMapper mapper = new ObjectMapper();

    @ExceptionHandler(UnauthorizedException.class)
    public void unauthorizedExceptionHandler(HttpServletRequest req, HttpServletResponse resp) {
        commonResolve(LayuiResult.failed("权限不足"), req, resp);
    }

    private void commonResolve(LayuiResult<?> result, HttpServletRequest req, HttpServletResponse resp) {
        // 判断当前对象是否为ajax请求
        if (Util.judgeRequestTypeIsAjax(req)) {
            PrintWriter writer = null;
            resp.setContentType("application/json;charset=utf-8");
            try {
                writer = resp.getWriter();
                writer.print(mapper.writeValueAsString(result));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }
}