package com.cing.blog.controller;

import com.cing.blog.util.ResponseCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cover")
public class CoverController {
    @GetMapping("findAll")
    public ResponseCode findAll() {
        // 获取所在路径
        File file = new File("");
        file = new File(file.getAbsolutePath() + "\\src\\main\\resources\\static\\site\\images\\thumbs");
        File [] files = file.listFiles();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < files.length; i++)
        {
            list.add("/site/images/thumbs/"+files[i].getName());
        }
        return ResponseCode.success(list);
    }
}
