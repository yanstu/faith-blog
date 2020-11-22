package cn.yanstu.blog.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author yanstu
 */
public class JwtToken implements AuthenticationToken {

    private final String token;

    public JwtToken(String jwt) {
        this.token = jwt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
