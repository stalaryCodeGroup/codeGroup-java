package com.stalary.codeGroup.viewmodel;

/**
 * @author Peter on 2017-02-20.
 */
public class ApiResult {


    public ApiResult() {
        this.code = 1;
    }

    public ApiResult(Object data) {
        this.code = 1;
        this.data = data;
    }

    public ApiResult(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    /**
     * 返回API的状态码
     */
    private int code;


    /**
     * 返回的数据
     */
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ApiResult ok() {
        return new ApiResult();
    }

    public static ApiResult ok(Object data) {
        return new ApiResult(data);
    }

    public static ApiResult error() {
        return new ApiResult(0, null);
    }

    public static ApiResult error(Object data) {
        return new ApiResult(0, data);
    }
}
