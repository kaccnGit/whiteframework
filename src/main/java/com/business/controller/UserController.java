package com.business.controller;

import com.business.model.User;
import com.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: kaccn
 * @description: 用户增删改查页面操作
 * @create: 2020-08-20 10:26
 **/
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("users", userService.list());
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/userAdd";
    }

    @RequestMapping("/add")
    public String add(User user) {
        userService.add(user);
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, int id) {
        model.addAttribute("user", userService.getUser(id));
        return "user/userEdit";
    }

    @RequestMapping(value = "/edit")
    public String edit(User user) {
        userService.edit(user);
        return "redirect:/list";
    }

    @RequestMapping("/delete")
    public String delete(int id) {
        userService.delete(id);
        return "redirect:/list";
    }
}