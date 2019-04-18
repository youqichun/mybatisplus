package com.springboot.mybatisplus.util;

import java.util.HashMap;
import java.util.Map;

/**
 *  Ajax请求响应对象的类
 */
public class AjaxResult extends HashMap<String,Object>{
    public static final String code = "code";//返回码
    public static final String msg = "msg";//返回消息
    public static final String data = "data";//返回对象
    public static final int code_succ = 1;//返回成功值：1
    public static final int code_error = 0;//返回失败值：0
    public static final String msg_succ = "success";//返回成功信息
    public static final String msg_error = "error";//返回失败信息

    //code:'',msg:''
    public AjaxResult(int code, String msg) {
        put(this.code, code);
        put(this.msg, msg);
    }

    //-----------------------返回失败-----------------------------------------
    //code:0,msg:'error'
    public static AjaxResult error() {
        return error(code_error, msg_error);
    }
    //code:0,msg:''
    public static AjaxResult error(String msg) {
        return error(code_error, msg);
    }
    //code:'',msg:''
    public static AjaxResult error(int code, String msg) {
        return new AjaxResult(code,msg);
    }
    //-----------------------返回成功-----------------------------------------
    //code:0,msg:'success'
    public static AjaxResult success() {
        return success(code_succ, msg_succ);
    }
    //code:1,msg:''
    public static AjaxResult success(String msg) {
        return success(code_succ, msg);
    }
    //code:'',msg:''
    public static AjaxResult success(int code, String msg) {
        return new AjaxResult(code,msg);
    }

    //-----------------------返回成功-带对象-----------------------------------------
    public static AjaxResult successData(Object dataobject){
        AjaxResult ajaxResult=new AjaxResult(code_succ, msg_succ);
        ajaxResult.put(data, dataobject);
        return ajaxResult;
    }
    public static AjaxResult successMap(Map<String, Object> map) {
        AjaxResult ajaxResult = new AjaxResult(code_succ, msg_succ);
        ajaxResult.putAll(map);
        return ajaxResult;
    }

    @Override
    public AjaxResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
