package com.cing.blog.service;

import com.cing.blog.mapper.TbArticleMapper;
import com.cing.blog.mapper.TbTagsMapper;
import com.cing.blog.model.TbArticle;
import com.cing.blog.model.TbTags;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class TagsService {
    @Autowired
    private TbTagsMapper tbTagsMapper;

    public int findAllCount() {
        return tbTagsMapper.selectCount(new TbTags());
    }

    public List<TbTags> findAll() {
        return tbTagsMapper.selectAll();
    }

    public PageInfo<TbTags> findByPage(int p, int limit) {
        PageHelper.startPage(p, limit);
        List<TbTags> list = tbTagsMapper.selectAll();
        PageInfo<TbTags> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public boolean exists(TbTags tbTags) {
        return tbTagsMapper.selectCount(tbTags) > 0 ? true : false;
    }

    public void insert(TbTags tbTags) {
        if (!exists(tbTags)) {
            tbTagsMapper.insert(tbTags);
        }
    }

    public void delete(List<Integer> ids) {
        if (!ids.isEmpty()) {
            ids.forEach(id -> {
                tbTagsMapper.deleteByPrimaryKey(id);
            });
        }
    }

    public void update(TbTags tbTags) {
        if (tbTags.getId() != 0) {
            tbTagsMapper.updateByPrimaryKeySelective(tbTags);
        }
    }
}
