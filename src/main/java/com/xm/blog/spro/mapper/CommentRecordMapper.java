package com.xm.blog.spro.mapper;

import com.xm.blog.spro.entity.CommentRecord;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CommentRecordMapper {

    //通过文章id获取一级评论
    public List<CommentRecord> getOneComment(Long articleid);
    //通过文章id和评论id来获取二级评论
    public List<CommentRecord> getTwoComment(Long articleid);
    //通过文章id、评论id、被评论者id来获取三级评论
    public List<CommentRecord> getThreeComment(Long articleid);
    //发表一级评论
    public void insertOneComment(CommentRecord commentRecord);
    //发表二级评论
    public void insertTwoComment(CommentRecord commentRecord);
    //发表三级评论
    public void insertThreeComment(CommentRecord commentRecord);

    //删除与articleid关联的评论
    public void deleteComment(Long articleid);

    //分页查询评论
    public List<CommentRecord> commentB(RowBounds rowBounds);

    //分页查询留言
    public List<CommentRecord> commentC(RowBounds rowBounds);
}