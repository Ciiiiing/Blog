package com.cing.blog.service;

import com.cing.blog.mapper.TbCategoryMapper;
import com.cing.blog.model.TbCategory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private TbCategoryMapper tbCategoryMapper;
    public int findAllCount(){
        return tbCategoryMapper.selectCount(new TbCategory());
    }
    public List<TbCategory> findAll(){
        return tbCategoryMapper.selectAll();
    }
    public PageInfo<TbCategory> findByPage(int p,int limit){
        PageHelper.startPage(p,limit);
        List<TbCategory> list = tbCategoryMapper.selectAll();
        PageInfo<TbCategory> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public boolean exists(TbCategory tbCategory) {
        return tbCategoryMapper.selectCount(tbCategory)>0?true:false;
    }

    public void insert(TbCategory tbCategory){
        if(!exists(tbCategory)){
            tbCategoryMapper.insertSelective(tbCategory);
        }
    }

    public void delete(List<Integer> ids){
        if(!ids.isEmpty()){
            ids.forEach(id->{
                tbCategoryMapper.deleteByPrimaryKey(id);
            });
        }
    }

    public void update(TbCategory tbCategory){
        if(tbCategory.getId()!=0){
            tbCategoryMapper.updateByPrimaryKeySelective(tbCategory);
        }
    }
}
