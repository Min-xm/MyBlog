package com.xm.blog.spro.controller;

import com.xm.blog.spro.entity.Article;
import com.xm.blog.spro.entity.User;
import com.xm.blog.spro.services.ArticleServiceImp;
import com.xm.blog.spro.services.UserServiceImp;
import com.xm.blog.spro.util.PageUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class userController {
    @Resource
    UserServiceImp userServiceImp;
    @Resource
    ArticleServiceImp articleServiceImp;
    @Resource
    PageUtil pageUtil;

    @PostMapping("/aaa")
    public String isLogin(@RequestParam("phone") String phone, @RequestParam("password") String password, Map<String, Object> msg, HttpSession session){
        Map<String, Boolean> map = userServiceImp.login(phone, password);
        //是否存在账号
        if(map.get("isPhone")){
            //是否是管理员
            if(map.get("isAdmin")){
                if(map.get("isPassword")){
                    User user = userServiceImp.getUser(phone, password);
                    msg.put("userInfo", user);
                    //保存用户信息
                    session.setAttribute("user", user);
                    return "redirect:admin.html";
                }else {
                    msg.put("loginInfo", "密码错误");
                }
            }else{
                if(map.get("isPassword")){
                    User user = userServiceImp.getUser(phone, password);
                    msg.put("userInfo", user);
                    //保存用户信息
                    session.setAttribute("user", user);
                    return "redirect:guest.html";
                }else {
                    msg.put("loginInfo", "密码错误");
                }
            }
        }else {
            msg.put("loginInfo", "账号不存在");
        }
        return "login";
    }
    //注销账号
    @RequestMapping("/cancel")
    public String cancelAccount(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session.invalidate();
        response.sendRedirect("/index");
        return "index";
    }
    //注册用户
    @PostMapping("/register")
    public String register(User user,Map<String, Object> msg,HttpSession session,String VerifyCode){
        System.out.println(session.getAttribute("VerifyCode"));
        boolean register = userServiceImp.register(user);
        //先判断验证码是否正确
        if(VerifyCode.equals(session.getAttribute("VerifyCode"))){
            System.out.println("验证码正确");
            if(register){
                msg.put("loginInfo", "注册成功请登录");
                return "login";
            }else{
                msg.put("loginInfo", "用户已存在");
            }
        }else {
            System.out.println("验证码错误");
            msg.put("loginInfo","验证码错误");
        }
        return "register";
    }

    @PostMapping("/admin/blog")
    public String isLogin(Article article){
        //设置随机数，赋予新插入的博文的ArticleId
        int ArticleId = (int)(Math.random()*1000000);
        article.setArticleid((long) ArticleId);
        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //设置日期格式
        String date = df.format(new Date());
        //设置发布文章日期
        article.setPublishdate(date);
        //更新文章日期
        article.setUpdatedate(date);
        //先判断博文内容是否超过170，如果超过170则进行截取(设置博文摘要)
        if (article.getArticlecontent().length() >= 220){
            //s为博文摘要
            String s = article.getArticlecontent().substring(0, 220);
            article.setArticletabloid(s);
        }else{
            article.setArticletabloid(article.getArticlecontent());
        }
        //设置文章url
        article.setArticleurl("/article/"+ArticleId);
        //发表博文
        if (articleServiceImp.insertBlog(article)){
            return "redirect:/index";
        }
        return "login";
    }

    @RequestMapping({"/index","/"})
    public String goIndex(Model model){
        //sum为总共的数据数
        int sum = articleServiceImp.getArticleAll(new RowBounds()).size();
        //先获取前4条数据
        List<Article> articleAll = articleServiceImp.getArticleAll(new RowBounds(0, 4));
        List<Integer> listPage = new ArrayList<Integer>();
        //通过循环获取总页数
        for(int i=1; i<=pageUtil.getPages(4,sum); i++){
            listPage.add(i);
        }
        model.addAttribute("articleList", articleAll);
        model.addAttribute("pages", listPage);
        return "index";
    }

    //用于分页跳转
    @RequestMapping("/index/{currentPage}")
    public String page(Model model, @PathVariable(value = "currentPage") int currentPage){
        //sum为总共的数据数
        int sum = articleServiceImp.getArticleAll(new RowBounds()).size();
        List<Article> articleAll = articleServiceImp.getArticleAll(new RowBounds(pageUtil.getData(currentPage).getStart(), 4));
        List<Integer> listPage = new ArrayList<Integer>();
        for(int i=1; i<=pageUtil.getPages(4,sum); i++){
            listPage.add(i);
        }
        model.addAttribute("articleList", articleAll);
        model.addAttribute("pages", listPage);
        return "index";
    }

    //保存修改的个人信息
    @RequestMapping(value = "/savePerson",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> savePerson(User user,HttpSession session){
        Map<String, Object> map = new HashMap<String, Object>();
        //保存用户信息
        session.setAttribute("user", user);
        userServiceImp.updatePerson(user);
        return map;
    }





}
