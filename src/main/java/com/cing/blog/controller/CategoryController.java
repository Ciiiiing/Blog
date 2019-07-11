package com.cing.blog.controller;

import com.cing.blog.model.TbCategory;
import com.cing.blog.service.CategoryService;
import com.cing.blog.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("findAllCount")
    public ResponseCode findAllCount() {
        return ResponseCode.success(categoryService.findAllCount());
    }

    @GetMapping("findAll")
    public ResponseCode findAll() {
        return ResponseCode.success(categoryService.findAll());
    }

    @PostMapping("findByPage")
    public ResponseCode findByPage(int pageCode, int pageSize) {
        return ResponseCode.success(categoryService.findByPage(pageCode, pageSize));
    }

    @PostMapping("save")
    public ResponseCode save(@RequestBody TbCategory tbCategory) {
        if (!categoryService.exists(tbCategory)) {
            categoryService.insert(tbCategory);
            return ResponseCode.success();
        }
        return ResponseCode.error();
    }

    @PostMapping("delete")
    public ResponseCode delete(@RequestBody List<Integer> ids) {
        categoryService.delete(ids);
        return ResponseCode.success();
    }

    @PutMapping("update")
    public ResponseCode update(@RequestBody TbCategory tbCategory) {
        categoryService.update(tbCategory);
        return ResponseCode.success();
    }
}
