package com.langxy.house.commons;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

@Data
public class Page<T> {

    private int pages;

    private int currentPageSize;

    private int pageSize;

    private long total;

    private List<T> list;

    public Page(int pages, int currentPageSize, int pageSize, long total, List<T> list) {
        this.pages = pages;
        this.currentPageSize = currentPageSize;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
    }

    public static <T> Page<T> generatePage (PageInfo<T> pageInfo) {
        return new Page<T>(
                pageInfo.getPages(),
                pageInfo.getPageNum(),
                pageInfo.getPageSize(),
                pageInfo.getTotal(),
                pageInfo.getList()
        );
    }

}
