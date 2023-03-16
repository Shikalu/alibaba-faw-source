package com.faw.usertestall.domain.common;

import java.io.Serializable;

/**
 * @author 鹿胜宝
 * @date 2023/03/15
 */
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = -4211759381240463820L;

    /**
     * 总计
     */
    private Long total;

    /**
     * 每页行数
     */
    private Integer pageSize;

    /**
     * 当前页号
     */
    private Integer pageNo;

    /**
     * 总页数
     */
    private Long pageNum;

    /**
     * 数据
     */
    private T data;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
