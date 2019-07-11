package com.cing.blog.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_log")
public class TbLog implements Serializable {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 操作用户
     */
    private String username;

    /**
     * 操作描述
     */
    private String operation;

    /**
     * 耗时(毫秒)
     */
    private Integer time;

    /**
     * 操作方法
     */
    private String method;

    /**
     * 操作参数
     */
    private String params;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 操作时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 操作地点
     */
    private String location;

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
     * 获取操作用户
     *
     * @return username - 操作用户
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置操作用户
     *
     * @param username 操作用户
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取操作描述
     *
     * @return operation - 操作描述
     */
    public String getOperation() {
        return operation;
    }

    /**
     * 设置操作描述
     *
     * @param operation 操作描述
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * 获取耗时(毫秒)
     *
     * @return time - 耗时(毫秒)
     */
    public Integer getTime() {
        return time;
    }

    /**
     * 设置耗时(毫秒)
     *
     * @param time 耗时(毫秒)
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * 获取操作方法
     *
     * @return method - 操作方法
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置操作方法
     *
     * @param method 操作方法
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 获取操作参数
     *
     * @return params - 操作参数
     */
    public String getParams() {
        return params;
    }

    /**
     * 设置操作参数
     *
     * @param params 操作参数
     */
    public void setParams(String params) {
        this.params = params;
    }

    /**
     * 获取IP地址
     *
     * @return ip - IP地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置IP地址
     *
     * @param ip IP地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取操作时间
     *
     * @return create_time - 操作时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置操作时间
     *
     * @param createTime 操作时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取操作地点
     *
     * @return location - 操作地点
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置操作地点
     *
     * @param location 操作地点
     */
    public void setLocation(String location) {
        this.location = location;
    }

    @Transient
    private String timeField;

    public String getTimeField() {
        return timeField;
    }

    public void setTimeField(String timeField) {
        this.timeField = timeField;
    }
}