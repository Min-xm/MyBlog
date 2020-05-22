package com.xm.blog.spro.services;

import com.xm.blog.spro.entity.CommentRecord;
import com.xm.blog.spro.mapper.CommentRecordMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentRecordServiceImp implements CommentRecordService{

    @Resource
    CommentRecordMapper commentRecordMapper;

    @Override
    public Map<String, List<CommentRecord>> getComment(Long articleid) {
        Map<String, List<CommentRecord>> map = new HashMap<>();
        //获取一级评论
        List<CommentRecord> oneComment = commentRecordMapper.getOneComment(articleid);
        //获取二级评论
        List<CommentRecord> twoComment = commentRecordMapper.getTwoComment(articleid);
        //获取三级评论
        List<CommentRecord> threeComment = commentRecordMapper.getThreeComment(articleid);

        //将三级评论存储到HashMap
        map.put("oneComment", oneComment);
        map.put("twoComment", twoComment);
        map.put("threeComment", threeComment);

        return map;
    }

    //发表一级评论
    @Override
    public void insertOneComment(CommentRecord commentRecord) {
        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //设置日期格式
        commentRecord.setCommentdate(df.format(new Date()));
        commentRecord.setLikes(0);
        commentRecord.setAnswereid(0);
        commentRecord.setRespondentid(0);
        commentRecordMapper.insertOneComment(commentRecord);
    }
    //发表二级评论
    @Override
    public void insertTwoComment(CommentRecord commentRecord) {
        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //设置日期格式
        commentRecord.setCommentdate(df.format(new Date()));
        commentRecord.setLikes(0);
        commentRecord.setRespondentid(0);
        commentRecordMapper.insertTwoComment(commentRecord);
    }

    //发表三级评论
    @Override
    public void insertThreeComment(CommentRecord commentRecord) {
        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //设置日期格式
        commentRecord.setCommentdate(df.format(new Date()));
        commentRecord.setLikes(0);
        commentRecordMapper.insertThreeComment(commentRecord);
    }

    @Override
    public List<CommentRecord> commentB(int currentPage) {
        int sum = commentRecordMapper.commentB(new RowBounds()).size();    //结束
        int start = sum-4;  //开始
        List<CommentRecord> commentRecords;
        if(currentPage==1){
            commentRecords = commentRecordMapper.commentB(new RowBounds(start, 4));
        }else{
            start -= 4*(currentPage-1);
            if(start<0){
                commentRecords = commentRecordMapper.commentB(new RowBounds(0, sum%4));
            }else{
                commentRecords = commentRecordMapper.commentB(new RowBounds(start, 4));
            }
        }
        return commentRecords;
    }

    @Override
    public List<CommentRecord> commentC(int currentPage) {
        int sum = commentRecordMapper.commentC(new RowBounds()).size();    //结束
        int start = sum-4;  //开始
        List<CommentRecord> commentRecords;
        if(currentPage==1){
            commentRecords = commentRecordMapper.commentC(new RowBounds(start, 4));
        }else{
            start -= 4*(currentPage-1);
            if(start < 0){
                commentRecords = commentRecordMapper.commentC(new RowBounds(0, sum%4));
            }else{
                commentRecords = commentRecordMapper.commentC(new RowBounds(start, 4));
            }
        }
        return commentRecords;
    }

}
