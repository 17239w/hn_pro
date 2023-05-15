package com.hn.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Api(tags = "注销控制层")
public class LogoutController {

    /**
     * root类型用户注销账号
     * @param request
     * @param response
     * @return
     */
    @ApiOperation("root用户注销账号")
    @RequestMapping(value="/rootlogout", method = RequestMethod.GET)
    public String rootLogoutPage (HttpServletRequest request, HttpServletResponse response) {
        //1.获取当前用户的认证信息
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //2.判断当前用户的认证信息是否为空
        if (auth != null){
            //3.若不为空,则注销当前用户的认证信息
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        //4.重定向到登录页面
        return "redirect:/rootLogin?logout";
    }

    /**
     * admin类型用户注销账号
     * @param request
     * @param response
     * @return
     */
    @ApiOperation("admin用户注销账号")
    @RequestMapping(value="/adminLogout", method = RequestMethod.GET)
    public String adminLogoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/adminLogin?logout";
    }


}
