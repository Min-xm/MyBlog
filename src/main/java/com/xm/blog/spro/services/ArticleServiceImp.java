package com.xm.blog.spro.services;

import com.xm.blog.spro.entity.Article;
import com.xm.blog.spro.mapper.ArticleMapper;
import com.xm.blog.spro.mapper.CommentRecordMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImp implements ArticleService{

    @Resource
    ArticleMapper articleMapper;
    @Resource
    CommentRecordMapper commentRecordMapper;


    @Override
    public List<Article> getArticleAll(RowBounds rowBounds) {
        return articleMapper.getAll(rowBounds);
    }

    @Override
    public boolean insertBlog(Article article) {
        Long l = article.getArticleid();
        //先判断这条记录是否存在，通多ArticleId进行判断
        Article isA = articleMapper.selectArticleId(l);
        //当articleId不存在时插入数据
        if (isA == null){
            articleMapper.insertBlog(article);
            return true;
        }
        return false;
    }

    @Override
    public void saveBlog(Article article) {
        articleMapper.updateArticle(article);
    }

    @Override
    public void deleteArticle(Long articleid) {
        articleMapper.deleteArticle(articleid);
        commentRecordMapper.deleteComment(articleid);
    }

    @Override
    public Article getArticle(Long articleid) {
        return articleMapper.selectArticleId(articleid);
    }


}
