package cn.yanstu.blog.util;

import cn.yanstu.blog.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @author yanstu
 * Shiro常用工具类
 */
public class ShiroUtil {

    /**
     *  从Security中获取当前用户信息
     */
    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }

}
