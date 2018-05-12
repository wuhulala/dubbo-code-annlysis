package com.wuhulala.dubbo.common.dto.req;

import java.io.Serializable;

/**
 * @author wuhulala
 */
public class BaseReq<T> implements Serializable {

    protected static final long serialVersionUID = 1L;

    public BaseReq() {
    }

    public BaseReq(T reqItem) {
        this.query = reqItem;
    }

    private T query;

    private ReqHdr header;

    /**
     * 用于定时任务传递的参数
     */
    private String jobArgs;

    /**
     * 返回请求头
     * @return
     */
    public ReqHdr getHeader() {
        if(header == null){
            this.header = new ReqHdr();
        }
        return header;
    }

    public BaseReq<T> setHeader(ReqHdr header) {
        if (header == null) {
            header = new ReqHdr();
        }
        this.header = header;
        return this;
    }


    public String getJobArgs() {
        return jobArgs;
    }

    public BaseReq<T> setJobArgs(String jobArgs) {
        this.jobArgs = jobArgs;
        return this;
    }

    public T getQuery() {
        return query;
    }

    public BaseReq<T> setQuery(T query) {
        this.query = query;
        return this;
    }
}
