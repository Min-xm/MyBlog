package com.xm.blog.spro.mapper;

import com.xm.blog.spro.entity.Article;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ArticleMapper {
    public List<Article> getAll(RowBounds rowBounds);
    //插入一条博文
    public void insertBlog(Article article);
    //查询博文ArticleId是否已经存在
    public Article selectArticleId(Long articleid);
    //修改博文
    public void updateArticle(Article article);

    //删除文章
    public void deleteArticle(Long articleid);
}