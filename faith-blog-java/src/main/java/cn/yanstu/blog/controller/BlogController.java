package cn.yanstu.blog.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.yanstu.blog.common.lang.Result;
import cn.yanstu.blog.entity.Blog;
import cn.yanstu.blog.entity.Link;
import cn.yanstu.blog.mapper.BlogMapper;
import cn.yanstu.blog.service.BlogService;
import cn.yanstu.blog.util.ShiroUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author yanstu
 * 博客文章控制类
 */
@RestController
public class BlogController {

    @Autowired
    BlogService blogService;
    @Autowired
    BlogMapper blogMapper;

    /**
     * 分页查询，返回当前页所有文章
     *
     * @param currentPage 当前页
     * @return 所有文章
     */
    @GetMapping("/blogs/{currentPage}")
    public Result list(@PathVariable(name = "currentPage", required = false) Integer currentPage) {
        IPage data = blogService.page(new Page(currentPage, 10), new QueryWrapper<Blog>().orderByDesc("top = 1").orderByDesc("created"));
        return Result.succ(data);
    }

    /**
     * 查询博客文章
     *
     * @param id 博客ID
     * @return 文章
     */
    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Integer id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "博客不存在");
        return Result.succ(blog);
    }

    /**
     * 添加/编辑博客文章
     *
     * @param blog 博客文章
     * @return 结果
     */
    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog) {
        Blog temp = null;
        if (blog.getId() != null) {
            temp = blogService.getById(blog.getId());
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(), "没有权限编辑");
        } else {
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(new Date());
            temp.setStatus(0);
        }
        BeanUtil.copyProperties(blog, temp, "id", "userId", blog.getCreated() == null ? "created" : "", "status");
        temp.setModified(new Date());
        blogService.saveOrUpdate(temp);
        return Result.succ(temp);
    }

    /**
     * 增加博客文章浏览量
     *
     * @param id 博客id
     * @return 操作成功
     */
    @GetMapping("/view/{id}")
    public Result addViews(@PathVariable(name = "id") Integer id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "博客文章不存在");
        blog.setViews(blog.getViews() + 1);
        blogService.saveOrUpdate(blog);
        return Result.succ(null);
    }

    @GetMapping("/blog/total")
    public Result getTotal() {
        Integer totalViews = blogService.getOne(new QueryWrapper<Blog>().select("sum(views) as views")).getViews();
        Integer totalArticles = blogService.getOne(new QueryWrapper<Blog>().select("count(1) as views")).getViews();
        Map<String, Integer> map = MapUtil.builder(new HashMap<String, Integer>())
                .put("totalViews", totalViews)
                .put("totalArticles", totalArticles).build();
        return Result.succ(map);
    }

}

