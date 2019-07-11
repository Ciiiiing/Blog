package com.cing.blog.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "tb_setting")
public class TbSetting implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 网站名称
     */
    @Column(name = "site_name")
    private String siteName;

    /**
     * 音乐ID
     */
    @Column(name = "site_music")
    private String siteMusic;

    /**
     * 社交链接，JSON格式
     */
    @Column(name = "site_links")
    private Object siteLinks;

    /**
     * 关于我，HTML格式
     */
    private String about;

    /**
     * 关于我，Markdown格式
     */
    @Column(name = "about_md")
    private String aboutMd;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取网站名称
     *
     * @return site_name - 网站名称
     */
    public String getSiteName() {
        return siteName;
    }

    /**
     * 设置网站名称
     *
     * @param siteName 网站名称
     */
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    /**
     * 获取音乐ID
     *
     * @return site_music - 音乐ID
     */
    public String getSiteMusic() {
        return siteMusic;
    }

    /**
     * 设置音乐ID
     *
     * @param siteMusic 音乐ID
     */
    public void setSiteMusic(String siteMusic) {
        this.siteMusic = siteMusic;
    }

    /**
     * 获取社交链接，JSON格式
     *
     * @return site_links - 社交链接，JSON格式
     */
    public Object getSiteLinks() {
        return siteLinks;
    }

    /**
     * 设置社交链接，JSON格式
     *
     * @param siteLinks 社交链接，JSON格式
     */
    public void setSiteLinks(Object siteLinks) {
        this.siteLinks = siteLinks;
    }

    /**
     * 获取关于我，HTML格式
     *
     * @return about - 关于我，HTML格式
     */
    public String getAbout() {
        return about;
    }

    /**
     * 设置关于我，HTML格式
     *
     * @param about 关于我，HTML格式
     */
    public void setAbout(String about) {
        this.about = about;
    }

    /**
     * 获取关于我，Markdown格式
     *
     * @return about_md - 关于我，Markdown格式
     */
    public String getAboutMd() {
        return aboutMd;
    }

    /**
     * 设置关于我，Markdown格式
     *
     * @param aboutMd 关于我，Markdown格式
     */
    public void setAboutMd(String aboutMd) {
        this.aboutMd = aboutMd;
    }
}