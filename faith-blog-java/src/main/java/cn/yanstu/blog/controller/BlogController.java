package cn.yanstu.blog.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.yanstu.blog.common.lang.Result;
import cn.yanstu.blog.entity.Blog;
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

    /**
     * 博客统计
     *
     * @return json
     */
    @GetMapping("/blog/total")
    public Result getTotal() {
        Integer totalViews = blogService.getOne(new QueryWrapper<Blog>().select("sum(views) as views")).getViews();
        Integer totalArticles = blogService.getOne(new QueryWrapper<Blog>().select("count(1) as views")).getViews();
        Map<String, Integer> map = MapUtil.builder(new HashMap<String, Integer>())
                .put("totalViews", totalViews)
                .put("totalArticles", totalArticles).build();
        return Result.succ(map);
    }

    /*

    {
    "code": 200,
    "msg": "操作成功",
    "data": {
        "2020": {
            "11 - 21": [
                {
                    "id": 26,
                    "userId": 1,
                    "title": "啊沙发上",
                    "description": "山豆根山豆根第三个是单独",
                    "content": "# 士大夫士大夫士大夫大师傅似的 \n>saasafas",
                    "created": "2020-11-21 19:02:40",
                    "modified": "2020-11-21 19:02:37",
                    "status": 0,
                    "views": 15,
                    "top": true
                },
                {
                    "id": 25,
                    "userId": 1,
                    "title": "Flutter 获取屏幕宽高",
                    "description": "Flutter 获取屏幕宽高",
                    "content": "屏幕宽度：\n>MediaQuery.of(context).size.width\n\n屏幕高度：\n\n>MediaQuery.of(context).size.height",
                    "created": "2020-11-21 19:02:36",
                    "modified": "2020-11-21 19:16:04",
                    "status": 0,
                    "views": 24,
                    "top": false
                }
            ],
            "2 - 21": [
                {
                    "id": 27,
                    "userId": 1,
                    "title": "大飒飒发",
                    "description": "啊沙发沙发沙发",
                    "content": "士大夫士大夫多福多寿",
                    "created": "2020-02-21 19:24:51",
                    "modified": "2020-02-21 19:24:51",
                    "status": 0,
                    "views": 1,
                    "top": false
                }
            ]
        }
    }
}

     */

    /**
     * 文章归档
     *
     * @return json
     */
    @GetMapping("/archive")
    public Result archive() {
        List<Blog> years = blogMapper.selectList(new QueryWrapper<Blog>()
                .select("DISTINCT YEAR(`created`) AS `views`"));
        Map<Integer, Map<String, Object>> maps = new HashMap<>();
        System.out.println(years);
        for (Blog blog : years) {
            Integer year = blog.getViews();
            List<Blog> months = blogMapper
                    .selectList(new QueryWrapper<Blog>()
                            .select("DISTINCT YEAR(`created`) AS `year`,MONTH(`created`) AS `views`,DAY(`created`) AS `status`")
                            .eq("YEAR(`created`)", year)
                            .orderByDesc("created"));
            Map<String, Object> map = new HashMap<>();
            for (Blog month : months) {
                List<Blog> articles = blogMapper
                        .selectList(new QueryWrapper<Blog>()
                                .select("*")
                                .eq("YEAR(`created`)", year)
                                .eq("MONTH(`created`)", month.getViews())
                                .eq("DAY(`created`)", month.getStatus())
                                .orderByDesc("created"));
                map.put(month.getViews() + " - " + month.getStatus(), articles);
            }
            maps.put(year, map);
        }
        return Result.succ(maps);
    }
}
