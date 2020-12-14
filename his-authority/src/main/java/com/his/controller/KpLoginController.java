package com.his.controller;

import com.his.service.KpPermissionService;
import com.his.util.HisMvcConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description: His登入及异常处理
 * Date: 20-12-11
 *
 * @author yh
 */
@Controller
public class KpLoginController {

    private static final Logger log = LoggerFactory.getLogger(KpLoginController.class);

    @Autowired
    private KpPermissionService kpPermissionService;

    @GetMapping("/login")
    public String toLogin() {
        return "redirect:/login.jsp";
    }

    @PostMapping("/login")
    public String doLogin(String loginName, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String doIndex(ModelMap modelMap) {
        Session sess = SecurityUtils.getSubject().getSession();
        sess.setAttribute(HisMvcConstant.ATTR_MENU, kpPermissionService.searchMenuMap());
        modelMap.addAttribute(HisMvcConstant.ATTR_MENU, sess.getAttribute(HisMvcConstant.ATTR_MENU));
        return "index";
    }

    @ExceptionHandler(UnknownAccountException.class)
    public ModelAndView catchUnknownAccountException(UnknownAccountException ex) {
        ModelAndView modelAndView = new ModelAndView()
                .addObject("exception", "帐号不存在");
        modelAndView.setViewName("forward:/login.jsp");
        return modelAndView;
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public ModelAndView catchIncorrectCredentialsException(IncorrectCredentialsException ex) {
        ModelAndView modelAndView = new ModelAndView()
                .addObject("exception", "密码错误");
        modelAndView.setViewName("forward:/login.jsp");
        return modelAndView;
    }
}