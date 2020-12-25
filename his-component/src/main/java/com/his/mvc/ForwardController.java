package com.his.mvc;

import com.his.util.MvcConstant;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * Description: TO DO
 * Date: 20-12-12
 *
 * @author yh
 */
@Controller
public class ForwardController {

    @GetMapping("/to/{root}/{catalog}/{page}")
    public String forwardHandler(@PathVariable String root, @PathVariable String catalog, @PathVariable String page, ModelMap modelMap) {
        modelMap.addAttribute(MvcConstant.ATTR_MENU, SecurityUtils.getSubject().getSession().getAttribute(MvcConstant.ATTR_MENU));
        return root + "/" + catalog + "/" + page;
    }
}