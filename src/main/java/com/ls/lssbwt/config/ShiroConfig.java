package com.ls.lssbwt.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.ls.lssbwt.shiro.UserRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Shiro基础配置类
 * @author lostsakura
 * @create 2020-01-08 14:53
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建UserRealm
     */
    @Bean(name = "userRealm")
    public UserRealm userRealm(@Qualifier("credentialsMatcherMD5") CredentialsMatcher credentialsMatcherMD5) {
        UserRealm userRealm = new UserRealm();

       // 设置加密凭证器
        userRealm.setCredentialsMatcher(credentialsMatcherMD5);
        return userRealm;
    }


    /**
     * 创建DefaultWebSecurityManager
     * 安全管理器
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联Realm
        List<Realm> realmList = new ArrayList<>();
        realmList.add(userRealm);
        securityManager.setRealms(realmList);
        /**
         * 设置认证策略
         * FirstSuccessfulStrategy：有一个匹配即可，返回第一个匹配的账户信息
         * AtLeastOneSuccessfulStrategy：至少有一个匹配的，返回所有匹配的信息
         * AllSuccessfulStrategy：所有Realm的认证策略必须都要通过
         */
//        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
//        authenticator.setAuthenticationStrategy(new AllSuccessfulStrategy());
//        securityManager.setAuthenticator(authenticator);

        return securityManager;
    }

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 添加Shiro内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         * 常用过滤器有：
         * anon：无需认证（登陆）可以访问
         * authc：必须认证才可以访问
         * user：如果使用rememberMe功能可以直接访问
         * perms：必须授予资源权限才可以访问
         * role：该资源必须得到角色权限才可以访问
         *
         *filterMap是有顺序的，需要使用LinkedHashMap声明
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        /**
         * 拦截路径的匹配支持Ant风格
         * /admin? 支持匹配admin1这种一个后缀字符的
         * /admin* 支持admin后多个字符的，但不支持下层目录，如：/admin/123
         * /admin/** 支持admin的当前目录以及admin/../../..后的目录
         *
         * 支持优先匹配，排除掉的过滤页面应当放在前面！
         */
        filterMap.put("/", "anon");
        filterMap.put("/test", "anon");
        filterMap.put("/success", "anon");
        filterMap.put("/admin/unauthorized", "anon");
        filterMap.put("/admin/addUser", "perms[user:add]");
        filterMap.put("/admin/updateUser", "perms[user:update]");
        filterMap.put("/admin/**", "authc");
        filterMap.put("/logout", "authc");

        // 修改拦截后跳转的页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 设置未授权页面的跳转页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/admin/unauthorized");
        // 添加到过滤链中
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 配置ShiroDialect，用于配置thymeleaf和Shiro标签
     */
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }


    /**
     * 配置密码凭证器 - MD5
     * 对密码进行加密
     */
    @Bean(name = "credentialsMatcherMD5")
    public CredentialsMatcher getCredentialsMatcherMD5() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 设置算法名称
        credentialsMatcher.setHashAlgorithmName("md5");
        // 设置散列次数
        credentialsMatcher.setHashIterations(777);
        return credentialsMatcher;
    }

    /**
     * 配置密码凭证器 - SHA-1
     */
    @Bean(name = "credentialsMatcherSHA1")
    public CredentialsMatcher getCredentialsMatcherSHA1() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 设置算法名称
        credentialsMatcher.setHashAlgorithmName("SHA1");
        // 设置散列次数
        credentialsMatcher.setHashIterations(777);
        return credentialsMatcher;
    }


}
