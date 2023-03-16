package com.faw.usertestall.domain.common;

import java.io.Serializable;

/**
 * @author 鹿胜宝
 * @date 2023/03/15
 */
public class PageQuery<T> implements Serializable {

    private static final long serialVersionUID = 8474064800776503397L;

    /**
     * 当前页
     */
    private Integer pageNo = 1;

    /**
     * 每页条数
     */
    private Integer pageSize = 10;

    /**
     * 查询条件
     */
    private T query;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public T getQuery() {
        return query;
    }

    public void setQuery(T query) {
        this.query = query;
    }
}
