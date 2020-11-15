package bml.controller;

import bml.others.BmlResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 边月白
 * Date 2020/5/16 14:51
 */
@RestController
public class LoginController {

    @RequestMapping("/login")
    public BmlResult<Object> login(String username, String password) {
        //获取实体
        Subject subject = SecurityUtils.getSubject();
        //创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            return new BmlResult<>(200,"登录成功");
        } catch (IncorrectCredentialsException e) {
            return new BmlResult<>(201,"密码错误");
        } catch (UnknownAccountException e) {
            return new BmlResult<> (202,"账号不存在");
        } catch (AuthenticationException e) {
            // 上面异常的总异常
            return new BmlResult<>(202,"登录失败");
        }
    }
}
