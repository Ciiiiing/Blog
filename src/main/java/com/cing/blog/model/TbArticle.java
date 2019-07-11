package com.cing.blog.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_article")
public class TbArticle implements Serializable {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面图片
     */
    private String cover;

    /**
     * 作者
     */
    private String author;

    /**
     * 分类
     */
    private String category;

    /**
     * 标签
     */
    private String tags;

    /**
     * 状态
     */
    private String state;

    /**
     * 浏览数
     */
    private Integer views;

    /**
     * 发布时间
     */
    @Column(name = "publish_time")
    private Date publishTime;

    /**
     * 上次修改时间
     */
    @Column(name = "edit_time")
    private Date editTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 类型， 0原创 1转载
     */
    private Integer type;

    /**
     * 内容
     */
    private String content;

    /**
     * 内容-Markdown
     */
    @Column(name = "content_md")
    private String contentMd;

    private static final long serialVersionUID = 1L;

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取封面图片
     *
     * @return cover - 封面图片
     */
    public String getCover() {
        return cover;
    }

    /**
     * 设置封面图片
     *
     * @param cover 封面图片
     */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * 获取作者
     *
     * @return author - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取分类
     *
     * @return categorys - 分类
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置分类
     *
     * @param category 分类
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取标签
     *
     * @return tags - 标签
     */
    public String getTags() {
        return tags;
    }

    /**
     * 设置标签
     *
     * @param tags 标签
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * 获取状态
     *
     * @return state - 状态
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态
     *
     * @param state 状态
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取浏览数
     *
     * @return views - 浏览数
     */
    public Integer getViews() {
        return views;
    }

    /**
     * 设置浏览数
     *
     * @param views 浏览数
     */
    public void setViews(Integer views) {
        this.views = views;
    }

    /**
     * 获取发布时间
     *
     * @return publish_time - 发布时间
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * 设置发布时间
     *
     * @param publishTime 发布时间
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * 获取上次修改时间
     *
     * @return edit_time - 上次修改时间
     */
    public Date getEditTime() {
        return editTime;
    }

    /**
     * 设置上次修改时间
     *
     * @param editTime 上次修改时间
     */
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取类型， 0原创 1转载
     *
     * @return type - 类型， 0原创 1转载
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型， 0原创 1转载
     *
     * @param type 类型， 0原创 1转载
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取内容-Markdown
     *
     * @return content_md - 内容-Markdown
     */
    public String getContentMd() {
        return contentMd;
    }

    /**
     * 设置内容-Markdown
     *
     * @param contentMd 内容-Markdown
     */
    public void setContentMd(String contentMd) {
        this.contentMd = contentMd;
    }
}