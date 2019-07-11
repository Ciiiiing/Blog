package com.cing.blog.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "tb_tags")
public class TbTags implements Serializable {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 标签名称
     */
    private String name;

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
     * 获取标签名称
     *
     * @return name - 标签名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置标签名称
     *
     * @param name 标签名称
     */
    public void setName(String name) {
        this.name = name;
    }
}