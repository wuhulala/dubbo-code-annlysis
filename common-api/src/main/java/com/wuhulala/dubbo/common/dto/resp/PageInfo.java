package com.wuhulala.dubbo.common.dto.resp;

import java.io.Serializable;

public class PageInfo implements Serializable {

    /**
     * 默认每页条数
     */
    public final static int DEFAULT_PAGE_SIZE = 20;
    private int start;

    private int limit;

    private int count;

    private int page = 1;

    private int totalPage;

    private boolean requireTotal = false;

    public PageInfo() {
    }

    public PageInfo(int page, int limit) {
        this.page = page > 0 ? page : 1;
        this.limit = limit > 0 ? limit : DEFAULT_PAGE_SIZE;
        this.start = (this.page - 1) * this.limit;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        this.totalPage = count / limit;
        if (count % limit > 0) {
            this.totalPage++;
        }
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isRequireTotal() {
        return requireTotal;
    }

    public void setRequireTotal(boolean requireTotal) {
        this.requireTotal = requireTotal;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Boolean hasNext() {
        return totalPage > page;
    }

    public Boolean hasPre() {
        return page > 1;
    }

}
