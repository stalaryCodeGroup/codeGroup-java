package com.stalary.codeGroup.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @author Peter on 2017-03-19.
 */
public class PageUtils {


    public static Pageable pageable(Integer page, Integer pageSize) {

        if (page == null) {
            page = 1;
        }

        if (page < 1) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = 20;
        }

        return new PageRequest(page - 1, pageSize);
    }

    public static Integer getStart(Integer page, Integer pageSize){
        return (page-1) * pageSize;
    }
    public static Integer getTotalPage(Integer totalData, Integer pageSize){
        Integer totalPage;
        if (totalData%pageSize != 0){
            totalPage = totalData/pageSize + 1;
        }else {
            totalPage = totalData/pageSize;
        }
        return totalPage;
    }

}
