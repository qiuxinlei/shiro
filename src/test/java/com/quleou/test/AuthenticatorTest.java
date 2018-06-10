package com.quleou.test;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class AuthenticatorTest {

    private void login(String configFile){
        // 1、获得SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory(configFile);
        // 2、得到SecurityManager实例，并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        // 3、得到Subject及创建用户名/密码身份验证Token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        // 登陆验证
        subject.login(token);

    }
    @Test
    public void testAllSuccessfulStrategyWithSuccess(){
        login("classpath:shiro-authenticator-all-success.ini");
        Subject subject = SecurityUtils.getSubject();
        // 得到一个身份集合，其包含了Realm验证成功的身份信息
        PrincipalCollection principals = subject.getPrincipals();
        System.out.println(principals.asList().size());
        Assert.assertEquals(2, principals.asList().size());
    }
    @Test(expected = UnknownAccountException.class)
    public void testAllSuccessfulStrategyWithFail() {
        login("classpath:shiro-authenticator-all-fail.ini");
        Subject subject = SecurityUtils.getSubject();
    }

}
