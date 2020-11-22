package cn.yanstu.blog.controller;


import cn.yanstu.blog.common.lang.Result;
import cn.yanstu.blog.entity.Link;
import cn.yanstu.blog.mapper.LinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yanstu
 * 友情链接控制类
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    LinkMapper linkMapper;

    @GetMapping("/all")
    public Result list() {
        List<Link> userList = linkMapper.selectList(null);
        return Result.succ(userList);
    }
}
