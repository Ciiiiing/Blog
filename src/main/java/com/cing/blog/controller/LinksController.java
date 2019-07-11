package com.cing.blog.controller;

import com.cing.blog.model.TbLinks;
import com.cing.blog.service.LinksService;
import com.cing.blog.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("links")
public class LinksController {
    @Autowired
    private LinksService linksService;

    @GetMapping("findAllCount")
    public ResponseCode findAllCount(){
        return ResponseCode.success(linksService.findAllCount());
    }

    @GetMapping("findById")
    public ResponseCode findById(int id){
        return ResponseCode.success(linksService.findById(id));
    }

    @PostMapping("findByPage")
    public ResponseCode findByPage(int pageCode, int pageSize, String name) {
        return ResponseCode.success(linksService.findByPage(pageCode, pageSize));
    }

    @PostMapping("save")
    public ResponseCode save(@RequestBody TbLinks tbLinks) {
        linksService.insert(tbLinks);
        return ResponseCode.success();
    }

    @PostMapping("delete")
    public ResponseCode delete(@RequestBody List<Integer> ids) {
        linksService.delete(ids);
        return ResponseCode.success();
    }

    @PutMapping("update")
    public ResponseCode update(@RequestBody TbLinks tbLinks) {
        linksService.update(tbLinks);
        return ResponseCode.success();
    }

}
