package com.cing.blog.service;

import com.cing.blog.mapper.TbSettingMapper;
import com.cing.blog.mapper.TbUserMapper;
import com.cing.blog.model.TbSetting;
import com.cing.blog.model.TbUser;
import com.cing.blog.util.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private PasswordHelper passwordHelper;

    public TbUser findById(int id) {
        return tbUserMapper.selectByPrimaryKey(id);
    }

    public void save(TbUser tbUser) {
        try {
            passwordHelper.encryptPassword(tbUser); //加密
            tbUserMapper.insert(tbUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void update(TbUser tbUser) {
        if (tbUser.getId() != 0) {
            try {
                if (tbUser.getPassword() != null && !"".equals(tbUser.getPassword())) {
                    passwordHelper.encryptPassword(tbUser); //加密
                }
                tbUserMapper.updateByPrimaryKeySelective(tbUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(List<Long> ids) {
        if (!ids.isEmpty()) {
            try {
                Example example = new Example(TbUser.class);
                example.createCriteria().andIn("id", ids);
                tbUserMapper.deleteByExample(example);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public TbUser findByName(String username) {
        if (!username.isEmpty()) {
            TbUser user = new TbUser();
            user.setUsername(username);
            return tbUserMapper.select(user).get(0);
        } else {
            return new TbUser();
        }
    }

    @Autowired
    private TbSettingMapper tbSettingMapper;

    public TbSetting findSetting() {
        return tbSettingMapper.selectAll().get(0);
    }

    public void updateSetting(TbSetting tbSetting) {
        tbSettingMapper.updateByPrimaryKeySelective(tbSetting);
    }
}
