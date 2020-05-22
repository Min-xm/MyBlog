package com.xm.blog.spro.mapper;

import com.xm.blog.spro.entity.Images;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ImagesMapper {

    //获取所有图片名称和地址
    public List<Images> getAll();

}