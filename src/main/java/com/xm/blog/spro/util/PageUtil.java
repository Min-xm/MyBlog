package com.xm.blog.spro.util;

import com.xm.blog.spro.entity.Page;
import org.springframework.stereotype.Component;

@Component
public class PageUtil {

    //获取总页数
    public int getPages(int count, int sum){
        //count参数为一页几条数据，sum为总数据数
        int s = sum%count;
        if (s == 0){
            return sum/count;
        }
        return sum/count+1;
    }

    //获取对应页的数据起始索引
    public Page getData(int currentPage){
        Page page = new Page();
        page.setStart((currentPage-1)*4);
        return page;
    }


}
