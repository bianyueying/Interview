package bml.controller;

import bml.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 边月白
 * Date 2020/5/16 11:27
 */
@Controller
public class PageController {

    @GetMapping({"/","index"})
    public String hello() {
        return "login";
    }

    /**
     * 后台页面跳转模块
     */
    @GetMapping("admin/index")
    public String adminPages() {
        return "admin/index";
    }

    @GetMapping("admin/console")
    public String console(Model model) {
        Subject subject = SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        model.addAttribute("user",user);
        return "admin/console";
    }

    @GetMapping("admin/myNote")
    public String note(Model model) {
        Subject subject = SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        model.addAttribute("user",user);
        return "admin/note";
    }

    @GetMapping("admin/examine")
    public String examine(Model model) {
        Subject subject = SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        model.addAttribute("user",user);
        return "admin/examine";
    }

}
