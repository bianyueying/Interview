package bml.shiro;

import bml.entity.User;
import bml.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 边月白
 * Date: 2019/12/27 12:48
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //给资源授权
        return new SimpleAuthorizationInfo();
    }

    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //此处编写判断逻辑 用于判断账号密码是否正确
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //根据登录的名字查询出用户的所有信息
        User user = userService.getOne(new QueryWrapper<User>().eq("username",token.getUsername()));
        //用户为空，则表示用户名不存在 返回null 底层会自动返回未知账号异常
        if (user == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }

}
