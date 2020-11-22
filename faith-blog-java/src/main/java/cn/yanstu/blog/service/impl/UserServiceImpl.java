package cn.yanstu.blog.service.impl;

import cn.yanstu.blog.entity.User;
import cn.yanstu.blog.mapper.UserMapper;
import cn.yanstu.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2020-11-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
