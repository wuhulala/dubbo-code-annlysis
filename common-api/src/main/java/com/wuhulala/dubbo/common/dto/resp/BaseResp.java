package com.wuhulala.dubbo.common.dto.resp;


import com.wuhulala.dubbo.common.constants.BaseConstants;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author wuhulala
 */
public class BaseResp<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String resultCode;

    protected String resultMsg;

    protected String cause;

    /**
     * 业务标识号
     **/
    protected String bizNo;

    protected PageInfo pageInfo;

    private T item;

    /*列表查询*/
    private List<T> list;

    /**
     * 灵活的数据查询
     */
    private Map<String, Object> map;

    public BaseResp() {
        this.resultCode = BaseConstants.RESULT_CODE_SUCCESS;
        this.resultMsg = BaseConstants.RESULT_MESSAGE_SUCCESS;
    }

    public BaseResp(String bizNo) {
        this.bizNo = bizNo;
        this.resultCode = BaseConstants.RESULT_CODE_SUCCESS;
    }

    public BaseResp(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public BaseResp<T> setResultCode(String resultCode) {
        this.resultCode = resultCode;
        //this.setResultMsg(SpringContext.getErrorInfo(resultCode,null));
        return this;
    }

    public BaseResp<T> setResultCode(String resultCode, Object... params) {
        this.resultCode = resultCode;
        //this.setResultMsg(SpringContext.getErrorInfo(resultCode,params));
        return this;
    }

    public BaseResp<T> setResultInfo(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        return this;
    }

    public BaseResp<T> setResultInfo(String resultCode, String resultMsg, String cause) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.cause = cause;
        return this;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public BaseResp<T> setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
        return this;
    }

    public String getBizNo() {
        return bizNo;
    }

    public BaseResp<T> setBizNo(String bizNo) {
        this.bizNo = bizNo;
        return this;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public boolean isError() {
        return !this.resultCode.equals(BaseConstants.RESULT_CODE_SUCCESS);
    }

    public boolean isSuccess() {
        return this.resultCode.equals(BaseConstants.RESULT_CODE_SUCCESS);
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public BaseResp<T> setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
        return this;
    }

    public T getItem() {
        return item;
    }

    public BaseResp<T> setItem(T item) {
        this.item = item;
        return this;
    }

    public List<T> getList() {
        return list;
    }

    public BaseResp<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public BaseResp<T> setMap(Map<String, Object> v) {
        this.map = map;
        return this;
    }
}
