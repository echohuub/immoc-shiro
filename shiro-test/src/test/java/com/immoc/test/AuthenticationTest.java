package com.immoc.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class AuthenticationTest {

    private SimpleAccountRealm realm = new SimpleAccountRealm();

    @Before
    public void addUser() {
        realm.addAccount("Mark", "123456", "admin", "user");
    }

    @Test
    public void testAuthentication() {

        // 1.构建SecurityManager环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(realm);

        // 2.主体提交认证请求
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("Mark", "123456");
        subject.login(token);

        subject.checkRoles("admin", "user");
//        System.out.println("isAuthenticated: " + subject.isAuthenticated());
    }
}
