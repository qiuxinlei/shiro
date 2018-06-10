package com.quleou.test;

import static org.junit.Assert.assertTrue;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class LoginLogoutTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testHelloWorld(){
        // 1、获得securityManager工厂，此处使用Ini配置文件初始化
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        // 2、获得SecurityManager实例，并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        // 3、得到Subject及创建用户名、密码省份验证Token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            // 4、登陆，身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            // 5、省份验证失败
            e.printStackTrace();
        }
        Assert.assertEquals(true,subject.isAuthenticated()); //断言用户已经登陆u
        // 6、退出
        subject.logout();

    }

    @Test
    public void testJDBCRealm() {
        // 1、获得securityManager工厂，此处使用Ini配置文件初始化
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
        // 2、获得SecurityManager实例，并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        // 3、得到Subject及创建用户名、密码省份验证Token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            // 4、登陆，身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            // 5、省份验证失败
            e.printStackTrace();
        }
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登陆u
        // 6、退出
        subject.logout();
    }

    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
    }
    
}
