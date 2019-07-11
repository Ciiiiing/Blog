package com.cing.blog.controller;

import com.cing.blog.model.TbArticle;
import com.cing.blog.service.ArticlesService;
import com.cing.blog.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("article")
public class ArticlesController {
    @Autowired
    private ArticlesService articlesService;

    @GetMapping("findAllCount")
    public ResponseCode findAllCount() {
        return ResponseCode.success(articlesService.findAllCount());
    }

    @GetMapping("findAll")
    public ResponseCode findAll() {
        return ResponseCode.success(articlesService.findRecent());
    }

    @GetMapping("findById")
    public ResponseCode findById(int id) {
        return ResponseCode.success(articlesService.findById(id));
    }

    @PostMapping("findByPage")
    public ResponseCode findByPage(int pageCode, int pageSize, String title) {
        return ResponseCode.success(articlesService.findByPage(pageCode, pageSize, title));
    }

    @PostMapping("save")
    public ResponseCode save(@RequestBody TbArticle tbArticle) {
        articlesService.insert(tbArticle);
        return ResponseCode.success();
    }

    @PostMapping("delete")
    public ResponseCode delete(@RequestBody List<Integer> ids) {
        articlesService.delete(ids);
        return ResponseCode.success();
    }

    @PutMapping("update")
    public ResponseCode update(@RequestBody TbArticle tbArticle) {
        articlesService.update(tbArticle);
        return ResponseCode.success();
    }
}
