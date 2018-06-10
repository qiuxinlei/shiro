package com.quleou.authenticator.strategy;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.realm.Realm;

import java.util.Collection;

public class OnlyOneAuthenticatorStrategy extends AbstractAuthenticationStrategy {
    public OnlyOneAuthenticatorStrategy() {
        super();
    }

    // 在所有realm验证之前调用
    @Override
    public AuthenticationInfo beforeAllAttempts(Collection<? extends Realm> realms, AuthenticationToken token) throws AuthenticationException {
        return super.beforeAllAttempts(realms, token);
    }
    // 在每个realm验证之前调用
    @Override
    public AuthenticationInfo beforeAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo aggregate) throws AuthenticationException {
        return super.beforeAttempt(realm, token, aggregate);
    }

    // 在每个realm验证之后调用
    @Override
    public AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo singleRealmInfo, AuthenticationInfo aggregateInfo, Throwable t) throws AuthenticationException {
        return super.afterAttempt(realm, token, singleRealmInfo, aggregateInfo, t);
    }
    @Override
    protected AuthenticationInfo merge(AuthenticationInfo info, AuthenticationInfo aggregate) {
        return super.merge(info, aggregate);
    }
    // 在所有realm验证之后调用
    @Override
    public AuthenticationInfo afterAllAttempts(AuthenticationToken token, AuthenticationInfo aggregate) throws AuthenticationException {
        return super.afterAllAttempts(token, aggregate);
    }
}
