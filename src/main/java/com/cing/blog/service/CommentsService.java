package com.cing.blog.service;

import com.cing.blog.mapper.TbCommentsMapper;
import com.cing.blog.model.TbComments;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class CommentsService {
    @Autowired
    private TbCommentsMapper tbCommentsMapper;

    public int findAllCount() {
        return tbCommentsMapper.selectCount(new TbComments());
    }

    public List<TbComments> findRecent() {
        Example example = new Example(TbComments.class);
        example.setOrderByClause("time desc");
        //选出最近的8个评论
        return tbCommentsMapper.selectByExampleAndRowBounds(example, new RowBounds(0, 8));
    }

    public PageInfo<TbComments> findByPage(int p, int limit, String name) {
        Example example = new Example(TbComments.class);
        if (!StringUtils.isEmpty(name)) {
            Example.Criteria criteria = example.createCriteria();
            criteria.andLike("name", "%" + name + "%");
        }
        PageHelper.startPage(p, limit);
        List<TbComments> list = tbCommentsMapper.selectByExample(example);
        PageInfo<TbComments> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public void insert(TbComments tbComments) {
        tbComments.setTime(new Date());
        tbCommentsMapper.insert(tbComments);
    }

    public void delete(List<Integer> ids) {
        if (!ids.isEmpty()) {
            ids.forEach(id -> {
                tbCommentsMapper.deleteByPrimaryKey(id);
            });
        }
    }

    public void update(TbComments tbComments) {
        if (tbComments.getId() != 0) {
            tbCommentsMapper.updateByPrimaryKeySelective(tbComments);
        }
    }

    public Map<String, Object> findCommentsList(Integer pageCode, Integer pageSize, Integer articleId, Integer type) {
        Map<String, Object> map = new HashMap<>();
        Example example = new Example(TbComments.class);
        Example.Criteria criteria = example.createCriteria();
        if(articleId!=0){
            criteria.andEqualTo("articleId",articleId);
        }
        criteria.andEqualTo("type",type);
        PageHelper.startPage(pageCode, pageSize);
        List<TbComments> list = tbCommentsMapper.selectByExample(example);
        PageInfo<TbComments> pageInfo = new PageInfo<>(list);
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());
        return map;
    }
}
