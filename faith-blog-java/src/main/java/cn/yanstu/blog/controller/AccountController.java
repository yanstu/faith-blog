package cn.yanstu.blog.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import cn.yanstu.blog.common.dto.LoginDto;
import cn.yanstu.blog.common.lang.Result;
import cn.yanstu.blog.entity.User;
import cn.yanstu.blog.service.UserService;
import cn.yanstu.blog.util.JwtUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author yanstu
 * 账号控制类
 */
@RestController
public class AccountController {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;

    /**
     * 登录
     * @return 结果
     */
    @CrossOrigin
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user, "用户不存在");
        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码错误！");
        }
        //生成一个Token
        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        return Result.succ(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("nickname", user.getNickname())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );
    }

    /**
     * 退出
     * @return 操作成功
     */
    @GetMapping("/logout")
    @RequiresAuthentication
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }
}
