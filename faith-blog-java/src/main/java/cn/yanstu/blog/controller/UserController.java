package cn.yanstu.blog.controller;


import cn.yanstu.blog.common.lang.Result;
import cn.yanstu.blog.entity.User;
import cn.yanstu.blog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 * @author yanstu
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 通过用户id查询用户
     *
     * @param id id
     * @return 用户
     */
    //@RequiresAuthentication
    @GetMapping("/{id}")
    public Result index(@PathVariable(name = "id") Integer id) {
        User user = userService.getById(id);
        return Result.succ(user);
    }

    /**
     * 保存一个用户
     *
     * @param user 用户实例
     * @return 结果
     */
    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user) {
        return Result.succ(user);
    }

}
