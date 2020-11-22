package cn.yanstu.blog.service.impl;

import cn.yanstu.blog.entity.Link;
import cn.yanstu.blog.mapper.LinkMapper;
import cn.yanstu.blog.service.LinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yanstu
 * @since 2020-11-21
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

}
