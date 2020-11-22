package cn.yanstu.blog.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yanstu
 */
@Data
public class AccountProfile implements Serializable {

    private Long id;

    private String username;

    private String nickname;

    private String avatar;

    private String email;

}
