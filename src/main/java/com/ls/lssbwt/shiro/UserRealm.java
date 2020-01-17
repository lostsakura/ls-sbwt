package com.ls.lssbwt.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ls.lssbwt.domain.User;
import com.ls.lssbwt.repository.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 自定义认证和授权的业务逻辑
 * @author lostsakura
 * @create 2020-01-08 14:56
 */
public class UserRealm extends AuthorizingRealm {


    private static Logger LOGGER = LoggerFactory.getLogger(UserRealm.class);

    @Resource
    private UserMapper userMapper;

    /**
     * 执行授权的逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        LOGGER.info("执行了[{}]授权方法！", this.getClass().getName());
        // 授权体
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        // 在数据库中查询授权信息
        User result = userMapper.selectById(user.getId());

        // 将授权信息传递给授权体
        info.addStringPermission(result.getPerms());
        return info;
    }

    /**
     * 执行认证的逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        LOGGER.info("执行了[{}]认证方法！", this.getClass().getName());

        // 获取传递过来的登录信息（Token），并强转为UsernamePasswordToken
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        // 根据用户名查询用户
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", token.getUsername()));
        // 判断用户是否存在（即判断提交的用户名是否注册）
        if (user == null) return null;
        String realmName = getName();


        // 根据用户名对铭文密码加盐处理
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, realmName);

        // Shiro处理用户密码是否正确
        return info;
    }
}
