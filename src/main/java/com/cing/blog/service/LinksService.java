package com.cing.blog.service;

import com.cing.blog.mapper.TbLinksMapper;
import com.cing.blog.model.TbLinks;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinksService {
    @Autowired
    private TbLinksMapper tbLinksMapper;

    public List<TbLinks> findAll(){
        return tbLinksMapper.selectAll();
    }

    public int findAllCount() {
        return tbLinksMapper.selectCount(new TbLinks());
    }

    public TbLinks findById(int id) {
        return tbLinksMapper.selectByPrimaryKey(id);
    }

    public PageInfo<TbLinks> findByPage(int p, int limit) {
        PageHelper.startPage(p, limit);
        List<TbLinks> list = tbLinksMapper.selectAll();
        PageInfo<TbLinks> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public void insert(TbLinks tbLinks) {
        tbLinksMapper.insert(tbLinks);
    }

    public void delete(List<Integer> ids) {
        if (!ids.isEmpty()) {
            ids.forEach(id -> {
                tbLinksMapper.deleteByPrimaryKey(id);
            });
        }
    }

    public void update(TbLinks tbLinks) {
        if (tbLinks.getId() != 0) {
            tbLinksMapper.updateByPrimaryKeySelective(tbLinks);
        }
    }
}
