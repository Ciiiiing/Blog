package com.cing.blog.service;

import com.cing.blog.mapper.TbLogMapper;
import com.cing.blog.model.TbLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class LogService {
    @Autowired
    private TbLogMapper tbLogMapper;

    public PageInfo<TbLog> findByPage(int p,int limit,TbLog tbLog){
        Example example = new Example(TbLog.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(tbLog.getUsername())) {
            criteria.andCondition("username=", tbLog.getUsername().toLowerCase());
        }
        if (!StringUtils.isEmpty(tbLog.getOperation())) {
            criteria.andCondition("operation like", "%" + tbLog.getOperation() + "%");
        }
        if (!StringUtils.isEmpty(tbLog.getLocation())) {
            criteria.andCondition("location=", tbLog.getLocation());
        }
        if (!StringUtils.isEmpty(tbLog.getTimeField())) {
            String[] split = tbLog.getTimeField().split(",");
            criteria.andCondition("date_format(CREATE_TIME, '%Y-%m-%d') >=", split[0]);
            criteria.andCondition("date_format(CREATE_TIME, '%Y-%m-%d') <=", split[1]);
        }
        example.setOrderByClause("create_time desc");
        PageHelper.startPage(p,limit);
        List<TbLog> list = tbLogMapper.selectByExample(example);
        PageInfo<TbLog> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public void delete(List<Integer> ids){
        if (!ids.isEmpty()) {
            ids.forEach(id->{
                tbLogMapper.deleteByPrimaryKey(id);
            });
        }
    }
}
