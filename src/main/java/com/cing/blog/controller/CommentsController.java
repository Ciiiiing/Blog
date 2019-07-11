package com.cing.blog.controller;

import com.cing.blog.model.TbComments;
import com.cing.blog.service.CommentsService;
import com.cing.blog.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comments")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;

    @GetMapping("findAllCount")
    public ResponseCode findAllCount() {
        return ResponseCode.success(commentsService.findAllCount());
    }

    @GetMapping("findAll")
    public ResponseCode findAll() {
        return ResponseCode.success(commentsService.findRecent());
    }

    @PostMapping("findByPage")
    public ResponseCode findByPage(int pageCode, int pageSize, String name) {
        return ResponseCode.success(commentsService.findByPage(pageCode, pageSize, name));
    }

    @PostMapping("save")
    public ResponseCode save(@RequestBody TbComments tbComments) {
        commentsService.insert(tbComments);
        return ResponseCode.success();
    }

    @PostMapping("delete")
    public ResponseCode delete(@RequestBody List<Integer> ids) {
        commentsService.delete(ids);
        return ResponseCode.success();
    }

    @PutMapping("update")
    public ResponseCode update(@RequestBody TbComments tbComments) {
        commentsService.update(tbComments);
        return ResponseCode.success();
    }
}
