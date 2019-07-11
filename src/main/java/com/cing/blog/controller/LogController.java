package com.cing.blog.controller;

import com.cing.blog.model.TbLog;
import com.cing.blog.service.LogService;
import com.cing.blog.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("log")
public class LogController {
    @Autowired
    private LogService logService;

    @PostMapping("list")
    public ResponseCode findByPage(@RequestParam int pageCode, @RequestParam int pageSize, @RequestBody TbLog tbLog) {
        return ResponseCode.success(logService.findByPage(pageCode, pageSize, tbLog));
    }

    @PostMapping("/delete")
    public ResponseCode delete(@RequestBody List<Integer> ids) {
        logService.delete(ids);
        return ResponseCode.success();
    }
}
