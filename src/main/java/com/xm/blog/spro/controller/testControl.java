package com.xm.blog.spro.controller;

import com.xm.blog.spro.entity.Article;
import com.xm.blog.spro.entity.CommentRecord;
import com.xm.blog.spro.mapper.ArticleMapper;
import com.xm.blog.spro.services.ArticleServiceImp;
import com.xm.blog.spro.services.CommentRecordServiceImp;
import com.xm.blog.spro.util.IVerifyCodeGen;
import com.xm.blog.spro.util.SimpleCharVerifyCodeGenImpl;
import com.xm.blog.spro.util.VerifyCode;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class testControl {

    @Resource
    private ArticleServiceImp articleServiceImp;
    @Resource
    private CommentRecordServiceImp commentRecordServiceImp;
    @Resource
    private ArticleMapper articleMapper;

    @RequestMapping("/loginb")
    public String goLogin(){
        return "login";
    }

    @RequestMapping("/blog")
    public String goBlog(){
        return "blog";
    }

    @RequestMapping("/class")
    public String goClass(HttpSession session){
        //articleID为零是为留言
        session.setAttribute("articleID",0L);
        return "class";
    }
    @RequestMapping("/file")
    public String goFile(HttpSession session){
        session.setAttribute("articleID",0L);
        return "file";
    }
    @RequestMapping("/Register")
    public String goRegister(){
        return "register";
    }

    //测试用article页面
    @RequestMapping("/article")
    public String goArticles(Model model){
        return "article";
    }



    @ResponseBody
    @PostMapping("/getArticleInfo")
    public Article getA(Article article){
        //设置随机数，赋予新插入的博文的ArticleId
        Random random = new Random();
        Long ArticleId = random.nextLong();
        article.setArticleid(ArticleId);
        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //设置日期格式
        String date = df.format(new Date());
        //设置发布文章日期
        article.setPublishdate(date);
        //更新文章日期
        article.setUpdatedate(date);
        //先判断博文内容是否超过100，如果超过100则进行截取(设置博文摘要)
        if (article.getArticlecontent().length() >= 100){
            //s为博文摘要
            String s = article.getArticlecontent().substring(0, 100);
            article.setArticletabloid(s);
        }else{
            article.setArticletabloid(article.getArticlecontent());
        }
        //设置文章url
        article.setArticleurl("blog/"+ArticleId);
        return article;
    }

    @ResponseBody
    @GetMapping("/testAjax")
    public List<Article> testAjax(Model model, int currentPage){
        List<Article> articleAll = articleServiceImp.getArticleAll(new RowBounds(0, currentPage));
        model.addAttribute("articleList", articleAll);
        return articleAll;
    }
    @ResponseBody
    @PostMapping("/startArticle")
    public List<Article> startArticle(){
        List<Article> articleAll = articleServiceImp.getArticleAll(new RowBounds());
        return articleAll;
    }

    @RequestMapping("/saveUpdate/{articleid}")
    public String saveUpdate(Model model, @PathVariable Long articleid){
        Article article = articleMapper.selectArticleId(articleid);
        model.addAttribute("article", article);
        return "upblog";
    }
    //保存文章修改信息
    @PostMapping("/saveArticle")
    public String saveArticle(Article article){
        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //设置日期格式
        String date = df.format(new Date());
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
        //保存修改
        articleServiceImp.saveBlog(article);
        return "redirect:admin.html";
    }

    //删除文章并删除相关评论
    @PostMapping("/deleteArticle")
    @ResponseBody
    public Map<String, Object> deleteArticle(Long articleid){
        Map<String, Object> map = new HashMap<>();
        articleServiceImp.deleteArticle(articleid);
        return map;
    }



    //文件上传
    @RequestMapping("/fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("fileName")MultipartFile file){
        if(file.isEmpty()){
            return "false";
        }
        String fileName = file.getOriginalFilename();
        int size = (int)file.getSize();
        System.out.println(fileName+"-->"+size);
        String path = "E:/images";
        File dest = new File(path+"/"+fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return "true";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }
    @RequestMapping("/upload")
    public String goUpload(){
        return "upload";
    }

    //全部分类初始化
    @PostMapping("/startClass")
    @ResponseBody
    public List<Article> startClass(){
        List<Article> articleAll = articleServiceImp.getArticleAll(new RowBounds());
        return articleAll;
    }

    //传递最新评论初始化数据
    @GetMapping("/startCommentB")
    @ResponseBody
    public List<CommentRecord> startCommentB(int currentPage){
        List<CommentRecord> commentRecords = commentRecordServiceImp.commentB(currentPage);
        return commentRecords;
    }

    //传递最新留言初始化数据
    @GetMapping("/startCommentC")
    @ResponseBody
    public List<CommentRecord> startCommentC(int currentPage){
        List<CommentRecord> commentRecords = commentRecordServiceImp.commentC(currentPage);
        return commentRecords;
    }



    //显示验证码
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
            //将VerifyCode绑定session
            request.getSession().setAttribute("VerifyCode", code);
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
