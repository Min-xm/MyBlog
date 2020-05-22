package com.xm.blog.spro.services;

import com.xm.blog.spro.entity.Article;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ArticleService {
    //获取所有博文
    public List<Article> getArticleAll(RowBounds rowBounds);

    //发表一篇博文
    public boolean insertBlog(Article article);

    //修改博文
    public void saveBlog(Article article);

    //删除文章
    public void deleteArticle(Long articleid);

    //通过articleid获取文章信息
    public Article getArticle(Long articleid);
}
