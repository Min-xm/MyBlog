package com.xm.blog.spro.controller;


import com.xm.blog.spro.entity.CommentRecord;
import com.xm.blog.spro.services.ArticleServiceImp;
import com.xm.blog.spro.services.CommentRecordServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class articleController {

    @Resource
    CommentRecordServiceImp commentRecordServiceImp;
    @Resource
    ArticleServiceImp articleServiceImp;


    //向前台传送JSON数据（一到三级的评论）
    @RequestMapping(value = "/getComment")
    @ResponseBody
    public Map<String, List<CommentRecord>> sendComment(Long articleId, HttpSession session){
        //设置评论的articleId，用于返回相应article页面的用户评论数据
        Long id = (Long) session.getAttribute("articleID");
        return commentRecordServiceImp.getComment(id);
    }

    //发表一级评论
    @PostMapping("/insertOneComment")
    @ResponseBody
    public Map<String, Object> insertOneComment(CommentRecord commentRecord){
        Map<String, Object> map = new HashMap<>();
        commentRecordServiceImp.insertOneComment(commentRecord);
        return map;
    }
    //发表二级评论
    @PostMapping("/insertTwoComment")
    @ResponseBody
    public Map<String, Object> insertTwoComment(CommentRecord commentRecord){
        Map<String, Object> map = new HashMap<>();
        commentRecordServiceImp.insertTwoComment(commentRecord);
        return map;
    }
    //发表三级评论
    @PostMapping("/insertThreeComment")
    @ResponseBody
    public Map<String, Object> insertThreeComment(CommentRecord commentRecord){
        Map<String, Object> map = new HashMap<>();
        commentRecordServiceImp.insertThreeComment(commentRecord);
        return map;
    }

    //先用模板进行数据渲染
    @RequestMapping("/article/{articleId}")
    public String goArticle(@PathVariable(value = "articleId") Long articleId, HttpSession session, Model model){
        model.addAttribute("article",articleServiceImp.getArticle(articleId));
        session.setAttribute("articleID",articleId);
        return "article";
    }

}
