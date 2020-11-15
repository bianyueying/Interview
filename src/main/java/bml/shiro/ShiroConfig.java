package bml.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : 边月白
 * Date: 2019/12/27 12:46
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager")SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //过滤集合
        Map<String,String> filterMap = new LinkedHashMap<>(100);
        //只允许以下请求无权限也可访问
        filterMap.put("/*","anon");

        // 此处的意思是 bml 请求只有权限为bml的用户才能访问成功
        filterMap.put("/bml", "perms[bml]");
        //后端所有请求都需要权限 测试环境下使用记住我功能
        filterMap.put("/admin/*","anon");

        //添加Shiro内置拦截器 把上面的自定义规则添加进来
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //修改调整后的登录界面 默认是login.jsp页面 修改为login.html
        shiroFilterFactoryBean.setLoginUrl("/login");

        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public SecurityManager defaultWebSecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm());
        return securityManager;
    }

    /**
     * 创建realm
     */
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }

    /**
     * 配置ShiroDialect 用于thymeleaf和Shiro标签使用
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }


}
