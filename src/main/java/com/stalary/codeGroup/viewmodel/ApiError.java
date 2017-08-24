package com.stalary.codeGroup.viewmodel;

/**
 * @author Peter on 2017-03-02.
 */
public class ApiError extends ApiResult {

    /**
     * 未登陆
     */
    public static int UN_LOGIN = -1;

    /**
     * 不存在的用户名
     */
    public static int NOLoginId = -2;

    /**
     * 密码错误
     */
    public static int ErrorPassword = -3;

    /**
     * 重复注册
     */
    public static int MultipleRegistration = -4;

    /**
     * 权限验证不通过
     */
    public static int NoPermission = -5;

    /**
     * 业务逻辑处理发生异常
     */
    public static int BusinessLogicError = -6;

    /**
     * 阿里支付返回或者通知验证签名失败
     */
    public static int FalseToVerifyAliPaySign = -7;

    /**
     * 参数异常
     */
    public static int IllegalArgument = -8;

    public ApiError(String message) {
        super(0, message);
    }

    public ApiError(int status, String message) {
        super(status, message);
    }

    /**
     * 未登陆
     *
     * @return ApiError
     */
    public static ApiError unLogin() {
        return new ApiError(UN_LOGIN, "未登陆");
    }

    /**
     * 不存在的用户名
     *
     * @return ApiError
     */
    public static ApiError loginIdNotFound() {
        return new ApiError(NOLoginId, "不存在的用户名");
    }

    public static ApiError errorPassword() {
        return new ApiError(ErrorPassword, "密码错误");
    }

    public static ApiError multipleRegistration() {
        return new ApiError(MultipleRegistration, "电话号码重复注册");
    }

    /**
     * 权限验证
     */
    public static ApiError noPermission(){
        return new ApiError(NoPermission,"您无权进行操作");
    }

    /**
     * 页面不需要处理具体逻辑的时候选择返回业务逻辑处理发生异常
     */
    public static ApiError businessLogicError(){
        return new ApiError(BusinessLogicError,"业务逻辑处理发生异常");
    }

    /**
     * 阿里支付返回或者通知验证签名失败
     */
    public static ApiError falseToVerifyAliPaySign(){
        return new ApiError(FalseToVerifyAliPaySign,"验证签名失败");
    }

    /**
     * 阿里支付返回或者通知验证签名失败
     */
    public static ApiError IllegalArgument(){
        return new ApiError(IllegalArgument,"参数提交异常！");
    }


}
