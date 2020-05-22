package com.xm.blog.spro;

import com.xm.blog.spro.entity.Article;
import com.xm.blog.spro.entity.CommentRecord;
import com.xm.blog.spro.entity.User;
import com.xm.blog.spro.mapper.ArticleMapper;
import com.xm.blog.spro.mapper.CommentRecordMapper;
import com.xm.blog.spro.mapper.UserMapper;
import com.xm.blog.spro.services.CommentRecordServiceImp;
import com.xm.blog.spro.util.PageUtil;
import org.apache.ibatis.session.RowBounds;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class MyBlogApplicationTests {

    @Resource
    ArticleMapper articleMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    PageUtil pageUtil;
    @Resource
    CommentRecordMapper commentRecordMapper;
    @Resource
    CommentRecordServiceImp commentRecordServiceImp;

    @Test
    void contextLoads() {
//        Article article = new Article();
//        article.setAuthor("作者");
//        article.setOriginalauthor("原作者");
//        article.setArticleid(12L);
//        article.setArticlecategories("文章分类");
//        article.setArticletabloid("文章摘要");
//        article.setArticletags("文章标签");
//        article.setArticletitle("文章标题");
//        article.setArticletype("文章类型");
//        article.setArticleurl("文章url");
//        article.setPublishdate("发布文章日期");
//        article.setUpdatedate("更新文章日期");
//        article.setArticlecontent("文章内容");
//        articleMapper.insertBlog(article);

        //获取当前时间
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //设置日期格式
//        System.out.println(df.format(new Date()));

        //先判断博文内容是否超过100，如果超过100则进行截取
//        System.out.println("afafsdfsdfs".substring(0, 100));

        //用于判断博文是否存在
//        Article article = articleMapper.selectArticleId(12L);
//        if (article != null){
//            System.out.println("不为空");
//        }else if(article == null){
//            System.out.println("为空");
//        }
        //设置随机数，赋予新插入的博文的ArticleId
//        Random random = new Random();
//        System.out.println(random.nextLong());

//        List<Article> all = articleMapper.getAll();
//        System.out.println(all);

//        String s = "A、B";
//        String[] split = s.split("、");
//        for (String a: split){
//            System.out.println(a);
//        }

//        System.out.println(pageUtil.getPages(4, 21));

        //一级评论
//        List<CommentRecord> oneComment1 = commentRecordMapper.getOneComment(1);
        //二级评论
//        List<CommentRecord> oneComment2 = commentRecordMapper.getTwoComment(1);
        //三级评论
//        List<CommentRecord> oneComment3 = commentRecordMapper.getThreeComment(1);
//        Map<String, List<CommentRecord>> comment = commentRecordServiceImp.getComment(1);

        //发表一级评论
//        CommentRecord record = new CommentRecord();
//        record.setPid(5L);
//        record.setArticleid(1L);
//        record.setOriginalauthor("方先生");
//        record.setCommentdate("2020-04-01");
//        record.setCommentcontent("方先生的另一条内容");
//        commentRecordMapper.insertOneComment(record);

        //修改用户信息
//        User user = new User();
//        user.setId(1);
//        user.setUsername("天边一朵云");
//        userMapper.updatePerson(user);
        //修改文章信息
//        Article article = new Article();
//        article.setArticleid(1L);
//        article.setAuthor("叶小明");
//        article.setArticlecontent("内容");
//        articleMapper.updateArticle(article);

//        Article article = articleMapper.selectArticleId(1L);
//        System.out.println(article);


//        先查询最新的4条评论
//        int sum = commentRecordMapper.commentB(new RowBounds()).size();
//        int start = sum-4;
//        List<CommentRecord> commentRecords = commentRecordMapper.commentB(new RowBounds(start, sum));

        List<CommentRecord> commentRecords1 = commentRecordServiceImp.commentB(1);
        List<CommentRecord> commentRecords2 = commentRecordServiceImp.commentB(2);
        List<CommentRecord> commentRecords3 = commentRecordServiceImp.commentB(3);
        System.out.println("aaaa");
    }



}
