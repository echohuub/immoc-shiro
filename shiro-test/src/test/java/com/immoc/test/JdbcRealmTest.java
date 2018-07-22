package com.immoc.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class JdbcRealmTest {

    private DruidDataSource dataSource = new DruidDataSource();

    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/shiro_demo");
        dataSource.setUsername("root");
        dataSource.setPassword("heqingbao");
    }

    @Test
    public void testAuthentication() {

        JdbcRealm realm = new JdbcRealm();
        realm.setDataSource(dataSource);
        realm.setPermissionsLookupEnabled(true);

        // 1.构建SecurityManager环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(realm);

        // 2.主体提交认证请求
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("Mark", "123456");
        subject.login(token);

        System.out.println("isAuthenticated: " + subject.isAuthenticated());

        subject.checkRole("admin");
        subject.checkRoles("admin", "user");

        // 检测权限前一定要开启：realm.setPermissionsLookupEnabled(true);
        subject.checkPermission("user:select");
    }
}
