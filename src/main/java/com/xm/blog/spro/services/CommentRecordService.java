package com.xm.blog.spro.services;

import com.xm.blog.spro.entity.CommentRecord;

import java.util.List;
import java.util.Map;

public interface CommentRecordService {

    //通过文章id分别获取三级评论
    public Map<String, List<CommentRecord>> getComment(Long articleid);
    //一级评论的发表
    public void insertOneComment(CommentRecord commentRecord);
    //发表二级评论
    public void insertTwoComment(CommentRecord commentRecord);
    //发表三级评论
    public void insertThreeComment(CommentRecord commentRecord);
    //分页查询最新评论
    public List<CommentRecord> commentB(int currentPage);
    //分页查询最新留言
    public List<CommentRecord> commentC(int currentPage);
}
