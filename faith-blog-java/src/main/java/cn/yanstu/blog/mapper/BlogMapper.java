package cn.yanstu.blog.mapper;

import cn.yanstu.blog.entity.Blog;
import cn.yanstu.blog.entity.Link;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @author yanstu
 * Mapper 接口
 */
@Component
public interface BlogMapper extends BaseMapper<Blog> {
}
