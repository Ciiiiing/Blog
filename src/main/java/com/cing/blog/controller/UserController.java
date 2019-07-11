package com.cing.blog.controller;

import com.cing.blog.model.TbSetting;
import com.cing.blog.model.TbUser;
import com.cing.blog.service.UserService;
import com.cing.blog.util.PasswordHelper;
import com.cing.blog.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordHelper passwordHelper;

    @GetMapping(value = "findById")
    public ResponseCode findById(int id) {
        return ResponseCode.success(userService.findById(id));
    }

    @PostMapping("update")
    public ResponseCode update(@RequestBody TbUser tbUser) {
        userService.update(tbUser);
        return ResponseCode.success();
    }

    @GetMapping("getSetting")
    public ResponseCode getSetting() {
        return ResponseCode.success(userService.findSetting());
    }

    @PostMapping("updateSetting")
    public ResponseCode updateSetting(@RequestBody TbSetting tbSetting) {
        userService.updateSetting(tbSetting);
        return ResponseCode.success();
    }
}
