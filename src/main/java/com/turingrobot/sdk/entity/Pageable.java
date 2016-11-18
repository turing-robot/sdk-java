package com.turingrobot.sdk.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 分页信息
 *
 * @author TURING Team
 * @version 4.0
 */
public class Pageable implements Serializable {

    /**
     * 页码
     */
    private int pageNumber = 1;

    /**
     * 每页记录数
     */
    private int pageSize = 20;

    /**
     * 搜索值
     */
    @JsonProperty("searchBy")
    private String searchValue;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
}