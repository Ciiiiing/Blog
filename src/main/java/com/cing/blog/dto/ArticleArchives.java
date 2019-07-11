package com.cing.blog.dto;

import com.cing.blog.model.TbArticle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 用于封装Article表按时间归档的DTO
 *
 * @author TyCoding
 * @date 2018/10/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleArchives implements Serializable {
    private String date;
    private List<TbArticle> articles;
}
