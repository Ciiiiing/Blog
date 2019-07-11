package com.cing.blog.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "tb_links")
public class TbLinks implements Serializable {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 连接名称
     */
    private String name;

    /**
     * 连接URL
     */
    private String url;

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
     * 获取连接名称
     *
     * @return name - 连接名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置连接名称
     *
     * @param name 连接名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取连接URL
     *
     * @return url - 连接URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置连接URL
     *
     * @param url 连接URL
     */
    public void setUrl(String url) {
        this.url = url;
    }
}