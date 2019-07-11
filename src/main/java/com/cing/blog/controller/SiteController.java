package com.cing.blog.controller;


import com.alibaba.fastjson.JSONArray;
import com.cing.blog.model.TbArticle;
import com.cing.blog.model.TbSetting;
import com.cing.blog.service.*;
import com.cing.blog.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class SiteController {

    @Autowired
    private ArticlesService articleService;

    @Autowired
    private CommentsService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private LinksService linkService;

    @Autowired
    private TagsService tagService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = {"", "/", "/index"})
    public String index(Model model) {
        initCommon(model);
        initIndex(1, model);
        return "site/index";
    }

    /**
     * 初始化公用数据
     *
     * @param model
     */
    private void initCommon(Model model) {
        //底部通用数据 近期文章，近期评论
        model.addAttribute("newArticle", articleService.findRecent());
        model.addAttribute("newComment", commentService.findRecent());
        TbSetting setting = userService.findSetting();
        setting.setSiteLinks(JSONArray.parseArray((String) setting.getSiteLinks()));
        model.addAttribute("setting", setting);
    }

    /**
     * 初始化首页数据
     *
     * @param pageCode
     * @param model
     */
    private void initIndex(Integer pageCode, Model model) {
        //分页文章数据
        Map<String, Object> map = articleService.findByPageForSite(pageCode, 6);
        map.put("total", Math.ceil(((Long) map.get("total")).doubleValue() / 6.0));
        //格式：[{...}, {...}, {...}]
        model.addAttribute("list", map);
        model.addAttribute("pageCode", pageCode);
    }

    @GetMapping("/page/{pageCode}")
    public String page(@PathVariable("pageCode") Integer pageCode, Model model) {
        if (pageCode != null && pageCode != 0) {
            initIndex(pageCode, model);
            initCommon(model);
            return "site/index";
        } else {
            return "site/index";
        }
    }

    private void getPage(Map<String, Object> map, Model model, Integer cp, Integer sort) {
        model.addAttribute("count", map.get("total"));
        map.put("total", (long) Math.ceil(((Long) map.get("total")).doubleValue() / (double) 6));
        model.addAttribute("talkList", map);
        model.addAttribute("cp", cp);
        model.addAttribute("sort", sort);
        initCommon(model);
    }

    @GetMapping("/article/{id}")
    public String generate(@PathVariable("id") Integer id,
                           @RequestParam(value = "cp", required = false) Integer cp, Model model) {
        if (id != null && id != 0) {
            articleService.addViews(id);
            TbArticle tbArticle = articleService.findById(id);
            List<String> tags = new ArrayList<>();
            if(!tbArticle.getTags().isEmpty()){
                //标签处理，将["1","2"]的标签拆分为1,2添加到List中
                tags = StringUtil.findBetweenStr(tbArticle.getTags(),"\"","\"");
            }
            model.addAttribute("article", tbArticle);
            model.addAttribute("tags",tags);
            if (cp == null) {
                //查询的第一页评论数据
                cp = 1;
            }
            //三个参数：1.pageCode当前页；2.PageSize默认每页显示6条（大）留言；3.文章ID值；4.sort当前是文章详情页，sort=0。
            //规定：sort=0表示文章详情页的评论信息；sort=1表示友链页的评论信息；sort=2表示关于我页的评论信息
            getPage(commentService.findCommentsList(cp, 6, new Long(id).intValue(), 0), model, cp, 0);
            return "site/page/detail";
        } else {
            return "site/index";
        }
    }

    @GetMapping("links")
    public String link(Model model, @RequestParam(value = "cp", required = false) Integer cp) {
        //加载友情链接数据
        model.addAttribute("list", linkService.findAll());
        if (cp == null) {
            //查询的第一页评论数据
            cp = 1;
        }
        //三个参数：1.pageCode当前页；2.PageSize默认每页显示6条（大）留言；3.文章ID值；4.sort当前是文章详情页，sort=0。
        //规定：sort=0表示文章详情页的评论信息；sort=1表示友链页的评论信息；sort=2表示关于我页的评论信息
        getPage(commentService.findCommentsList(cp, 6, 0, 1), model, cp, 1);
        return "site/page/links";
    }

    @GetMapping("/about")
    public String about(Model model, @RequestParam(value = "cp", required = false) Integer cp) {
        if (cp == null) {
            //查询的第一页评论数据
            cp = 1;
        }
        //三个参数：1.pageCode当前页；2.PageSize默认每页显示6条（大）留言；3.文章ID值；4.sort当前是文章详情页，sort=0。
        //规定：sort=0表示文章详情页的评论信息；sort=1表示友链页的评论信息；sort=2表示关于我页的评论信息
        getPage(commentService.findCommentsList(cp, 6, 0, 2), model, cp, 2);
        return "site/page/about";
    }

    @GetMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("list", articleService.findArchives());
        initCommon(model);
        return "site/page/archives";
    }

    @GetMapping("tags")
    public String tags(Model model){
        model.addAttribute("list",tagService.findAll());
        initCommon(model);
        return "site/page/tags/index";
    }

    @GetMapping("tags/{tag}")
    public String tag(@PathVariable("tag") String tag,Model model){
        model.addAttribute("list",articleService.findByTagName(tag));
        model.addAttribute("tag", tag);
        initCommon(model);
        return "site/page/tags/tag";
    }

    @GetMapping("categorys")
    public String categorys(Model model){
        model.addAttribute("list",categoryService.findAll());
        initCommon(model);
        return "site/page/categorys/index";
    }

    @GetMapping("categorys/{category}")
    public String category(@PathVariable("category") String category,Model model){
        model.addAttribute("list",articleService.findByCategoryName(category));
        model.addAttribute("category", category);
        initCommon(model);
        return "site/page/categorys/category";
    }
}
