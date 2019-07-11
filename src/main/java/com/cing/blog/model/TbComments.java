package com.cing.blog.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_comments")
public class TbComments implements Serializable {
    /**
     * ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 文章标题
     */
    @Column(name = "article_title")
    private String articleTitle;

    /**
     * 文章ID
     */
    @Column(name = "article_id")
    private Long articleId;

    /**
     * 昵称
     */
    private String name;

    /**
     * 留言时间
     */
    private Date time;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 网址
     */
    private String url;

    /**
     * 分类：0:默认，文章详情页，1:友链页，2:关于页
     */
    private Long type;

    /**
     * 留言内容
     */
    private String content;

    private static final long serialVersionUID = 1L;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取文章标题
     *
     * @return article_title - 文章标题
     */
    public String getArticleTitle() {
        return articleTitle;
    }

    /**
     * 设置文章标题
     *
     * @param articleTitle 文章标题
     */
    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    /**
     * 获取文章ID
     *
     * @return article_id - 文章ID
     */
    public Long getArticleId() {
        return articleId;
    }

    /**
     * 设置文章ID
     *
     * @param articleId 文章ID
     */
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    /**
     * 获取昵称
     *
     * @return name - 昵称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置昵称
     *
     * @param name 昵称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取留言时间
     *
     * @return time - 留言时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置留言时间
     *
     * @param time 留言时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取网址
     *
     * @return url - 网址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置网址
     *
     * @param url 网址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取分类：0:默认，文章详情页，1:友链页，2:关于页
     *
     * @return type - 分类：0:默认，文章详情页，1:友链页，2:关于页
     */
    public Long getType() {
        return type;
    }

    /**
     * 设置分类：0:默认，文章详情页，1:友链页，2:关于页
     *
     * @param type 分类：0:默认，文章详情页，1:友链页，2:关于页
     */
    public void setType(Long type) {
        this.type = type;
    }

    /**
     * 获取留言内容
     *
     * @return content - 留言内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置留言内容
     *
     * @param content 留言内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}