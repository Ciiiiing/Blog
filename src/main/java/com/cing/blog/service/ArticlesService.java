package com.cing.blog.service;

import com.cing.blog.dto.ArticleArchives;
import com.cing.blog.mapper.TbArticleMapper;
import com.cing.blog.mapper.TbTagsMapper;
import com.cing.blog.model.TbArticle;
import com.cing.blog.model.TbTags;
import com.cing.blog.model.TbUser;
import com.cing.blog.util.StringUtil;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class ArticlesService {
    @Autowired
    private TbArticleMapper tbArticleMapper;
    @Autowired
    private TagsService tagsService;

    public int findAllCount() {
        return tbArticleMapper.selectCount(new TbArticle());
    }

    public TbArticle findById(int id) {
        return tbArticleMapper.selectByPrimaryKey(id);
    }

    public List<TbArticle> findRecent() {
        Example example = new Example(TbArticle.class);
        example.setOrderByClause("publish_time desc");
        //选出最近的8个文章
        return tbArticleMapper.selectByExampleAndRowBounds(example, new RowBounds(0, 8));
    }

    public PageInfo<TbArticle> findByPage(int p, int limit, String title) {
        Example example = new Example(TbArticle.class);
        if (!StringUtils.isEmpty(title)) {
            Example.Criteria criteria = example.createCriteria();
            criteria.andLike("title", "%" + title + "%");
        }
        PageHelper.startPage(p, limit);
        List<TbArticle> list = tbArticleMapper.selectByExample(example);
        PageInfo<TbArticle> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public void insert(TbArticle tbArticle) {
        if (tbArticle.getState().equals("1")) {
            tbArticle.setPublishTime(new Date());
        }
        //随机选择封面
        int i = (int) (Math.random() * 18 + 1);
        tbArticle.setCover("/site/images/thumbs/" + String.valueOf(i) + ".jpg");
        tbArticle.setEditTime(new Date());
        tbArticle.setCreateTime(new Date());
        tbArticle.setViews(0);
        //标签同步
        List<String> list = StringUtil.findBetweenStr(tbArticle.getTags(), "\"", "\"");
        list.forEach(tag -> {
            TbTags tbTags = new TbTags();
            tbTags.setName(tag);
            tagsService.insert(tbTags);
        });
        tbArticleMapper.insert(tbArticle);
    }

    public void delete(List<Integer> ids) {
        if (!ids.isEmpty()) {
            ids.forEach(id -> {
                tbArticleMapper.deleteByPrimaryKey(id);
            });
        }
    }

    public void update(TbArticle tbArticle) {
        if (tbArticle.getId() != 0) {
            tbArticle.setEditTime(new Date());
            tbArticleMapper.updateByPrimaryKeySelective(tbArticle);
            if(tbArticle.getCover().isEmpty()){
                //标签同步
                List<String> list = StringUtil.findBetweenStr(tbArticle.getTags(), "\"", "\"");
                list.forEach(tag -> {
                    TbTags tbTags = new TbTags();
                    tbTags.setName(tag);
                    tagsService.insert(tbTags);
                });
            }
        }
    }

    //站点视图数据
    public Map<String, Object> findByPageForSite(int pageCode, int pageSize) {
        PageHelper.startPage(pageCode, pageSize);
        Page<TbArticle> page = tbArticleMapper.findByPageForSite();
        List<TbArticle> articleList = page.getResult();
        Map<String, Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("data", articleList);
        return map;
    }

    //增加浏览数
    public void addViews(Integer id) {
        if (!id.equals(null) && id != 0) {
            try {
                tbArticleMapper.addViews(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<ArticleArchives> findArchives() {
        List<ArticleArchives> articleArchivesList = new ArrayList<ArticleArchives>();
        try {
            //搜索所有文章日期
            List<String> dates = tbArticleMapper.findArchivesDates();
            dates.forEach(date -> {
                //搜索该日期下所有文章
                List<TbArticle> articleList = tbArticleMapper.findArchivesByDate(date);
                ArticleArchives articleArchives = new ArticleArchives(date, articleList);
                articleArchivesList.add(articleArchives);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleArchivesList;
    }

    public List<TbArticle> findByTagName(String name) {
        Example example = new Example(TbArticle.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("tags", "%" + name + "%");
        return tbArticleMapper.selectByExample(example);
    }

    public List<TbArticle> findByCategoryName(String name) {
        Example example = new Example(TbArticle.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("category", name);
        return tbArticleMapper.selectByExample(example);
    }
}
