package com.mmall.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

//包含.不空
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//保证序列化json的时候，如果是null的对象，key也会消失
public class ServerResponse<T>  implements Serializable {

    private int status;
    private String  msg;
    //可以指定返回的类型，也可以不指定返回的类型
    private T   data;

    private ServerResponse(int  status){
        this.status=status;
    }

    private ServerResponse(int  status,T    data){
        this.status=status;
        this.data=data;
    }

    private ServerResponse(int  status,String   msg,T    data){
        this.status=status;
        this.msg=msg;
        this.data=data;
    }

    private ServerResponse(int  status,String   msg){
        this.status=status;
        this.msg=msg;
    }

    @JsonIgnore//json忽视，不会显示在json里面
    public boolean  isSuccess(){
        return  this.status==ResponseCode.SUCCESS.getCode();
    }

    public  int    getStatus(){
        return  status;
    }

    public T    getData(){
        return  data;
    }

    public String   getMsg(){
        return msg;
    }

    //创建这个对象，通过一个成功的（成功的话，就创建这个对象）
    public static   <T> ServerResponse<T>   createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    //成功的话还得返回一个文本（msg），供前端提示的作用
    public static   <T> ServerResponse<T>   createBySuccessMessage(String   msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    //返回一个正确的数据
    public static   <T> ServerResponse<T>   createBySuccess(T   data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    //消息和数据一起传过去
    public static   <T> ServerResponse<T>   createBySuccess(String  msg,T   data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    public  static  <T> ServerResponse<T>   createByError(){
        return  new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    public  static  <T> ServerResponse<T>   createByErrorMessage(String errorMessage){
        return  new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }

    public static   <T> ServerResponse<T>   createByErrorCodeMessage(int    errorCode,String    errorMessage){
        return  new ServerResponse<T>(errorCode,errorMessage);
    }

}
