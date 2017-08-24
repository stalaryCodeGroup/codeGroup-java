package com.stalary.codeGroup.viewmodel;

import java.util.List;

/**
 * @author Peter on 2017-03-14.
 */
public class PageModel<T> {


    public PageModel() {
        this.pageTotal = 0;
    }

    public PageModel(List<T> list, Integer pageTotal) {
        this.list = list;
        this.pageTotal = pageTotal;
    }


    private List<T> list;

    private int totalElements;

    private int pageTotal;


    /**
     * 当前list中第一个元素在总结果中的序号
     */
    private Integer index;


    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }
}
