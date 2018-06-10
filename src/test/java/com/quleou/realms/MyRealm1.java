package com.quleou.realms;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm {

    private static String realmName = "myRealm1";


    @Override
    public String getName() {
        return realmName;
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        // 仅支持UsernamePasswordToken类型的token
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        // 获得用户名
        String username = (String) authenticationToken.getPrincipal();
        // 获得密码
        String password = new String((char[]) authenticationToken.getCredentials());
        // 验证用户名
        if (!"zhang".equals(username)){
            // 测试
            System.out.println("用户名不正确");

            throw new UnknownAccountException();
        }
        // 验证密码
        if (!"123".equals(password)){
            // 测试
            System.out.println("密码不正确");

            throw new IncorrectCredentialsException();
        }
        // 测试
        System.out.println("验证成功");
        // 如果身份认证验证成功，返回一个AuthenticationInfo
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
