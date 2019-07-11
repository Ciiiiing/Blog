package com.cing.blog.controller;

import com.cing.blog.model.TbTags;
import com.cing.blog.service.TagsService;
import com.cing.blog.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tags")
public class TagsController {
    @Autowired
    private TagsService tagsService;

    @GetMapping("findAllCount")
    public ResponseCode findAllCount() {
        return ResponseCode.success(tagsService.findAllCount());
    }

    @GetMapping("findAll")
    public ResponseCode findAll(){
        return ResponseCode.success(tagsService.findAll());
    }

    @PostMapping("findByPage")
    public ResponseCode findByPage(int pageCode,int pageSize){
        return ResponseCode.success(tagsService.findByPage(pageCode,pageSize));
    }

    @PostMapping("save")
    public ResponseCode save(@RequestBody TbTags tbTags){
        if(!tagsService.exists(tbTags)){
            tagsService.insert(tbTags);
            return ResponseCode.success();
        }
        return ResponseCode.error();
    }

    @PostMapping("delete")
    public ResponseCode delete(@RequestBody List<Integer> ids){
        tagsService.delete(ids);
        return ResponseCode.success();
    }

    @PutMapping("update")
    public ResponseCode update(@RequestBody TbTags tbTags){
        tagsService.update(tbTags);
        return ResponseCode.success();
    }
}
