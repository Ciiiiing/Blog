package com.cing.blog.mapper;

import com.cing.blog.model.TbArticle;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TbArticleMapper extends Mapper<TbArticle> {
    @Select("select * from tb_article where state = '1' order by publish_time desc")
    Page<TbArticle> findByPageForSite();

    @Update("update tb_article set views = (views + 1) where id = #{id}")
    void addViews(long id);

    @Select("SELECT DISTINCT DATE_FORMAT(publish_time, '%Y-%m') FROM tb_article ORDER BY DATE_FORMAT(publish_time, '%Y-%m') DESC")
    List<String> findArchivesDates();

    @Select("SELECT id, title, publish_time FROM tb_article WHERE publish_time LIKE CONCAT ('%', #{date} ,'%')")
    List<TbArticle> findArchivesByDate(String date);
}