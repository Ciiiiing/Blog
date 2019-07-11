package com.cing.blog.controller;

import com.cing.blog.enums.StatusEnums;
import com.cing.blog.model.TbUser;
import com.cing.blog.service.UserService;
import com.cing.blog.util.ResponseCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @ResponseBody
    @RequestMapping("/admin/login")
    public ResponseCode login(Model model,
                              @RequestParam(value = "username", required = false) String username,
                              @RequestParam(value = "password", required = false) String password,
                              @RequestParam(value = "remember", required = false) String remember) {
        if (username != null && password != null) {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            if (remember != null) {
                if (remember.equals("true")) {
                    //说明选择了记住我
                    token.setRememberMe(true);
                } else {
                    token.setRememberMe(false);
                }
            } else {
                token.setRememberMe(false);
            }
            try {
                subject.login(token);
                model.addAttribute("username", SecurityUtils.getSubject().getPrincipal());
                return ResponseCode.success();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ResponseCode.error(StatusEnums.ACCOUNT_ERROR);
    }

    @ResponseBody
    @GetMapping(value = "/admin/logout")
    public ResponseCode logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResponseCode.success();
    }

    @Autowired
    private UserService userService;

    @GetMapping("/admin/info")
    @ResponseBody
    public ResponseCode getInfo() {
        TbUser tbUser = (TbUser) SecurityUtils.getSubject().getPrincipal();
        return ResponseCode.success(userService.findByName(tbUser.getUsername()));
    }
}
