package cn.yanstu.blog.service.impl;

import cn.yanstu.blog.entity.Blog;
import cn.yanstu.blog.mapper.BlogMapper;
import cn.yanstu.blog.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
