package com.business.controller;

import com.whiteBox.core.element.ActElement;
import com.whiteBox.core.element.WhiteMethod;
import com.whiteBox.core.factory.MethodsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: kaccn
 * @description: 界面化操作白盒测试方法
 * @create: 2020-08-20 10:26
 **/
@Controller
@RequestMapping("white")
public class WhiteController {

    @Autowired
    private MethodsFactory methodsFactory;

    private List<WhiteMethod> methods = methodsFactory.whiteMethods.values().stream()
            .collect(Collectors.toList());

    /**
     * 方法列表，列出所有标注的方法
     *
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("methods", methods);

        return "white/whiteList";
    }


    /**
     * 跳转至方法详情
     *
     * @param model
     * @param index
     * @return
     */
    @RequestMapping("/toExecute")
    public String toExecute(Model model, int index) {
        WhiteMethod whiteMethod = methods.get(index);
        model.addAttribute("index", index);
        model.addAttribute("whiteMethod", whiteMethod);
        return "white/doExecute";
    }

    /**
     * 执行测试方法
     *
     * @param index
     * @param times
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping("/execute")
    public String execute(int index, int times, String params) throws Exception {
        WhiteMethod whiteMethod = methods.get(index);
        ActElement element = new ActElement(times, params);
        whiteMethod.doWhiteMethod(element);
        return "redirect:/white/list";
    }

}