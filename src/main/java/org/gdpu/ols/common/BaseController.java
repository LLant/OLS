package org.gdpu.ols.common;

import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {

    protected static final String SUCCESS="SUCCESS";
    protected static final String ERROR="ERROR";
    private static final String EXCEPTION_TYPE="exception";
    private int EXCEPTION_MESSAGE_SIZE=800;

    public ResponseBean buildResponseBean(String resultCode,String resultMessage,Object date){
        ResponseBean responseBean=new ResponseBean();
        responseBean.setData(date);
        responseBean.setResultCode(resultCode);
        responseBean.setResultMessage(resultMessage);

        return responseBean;
    }

    @ExceptionHandler
    public void exp(HttpServletRequest request, HttpServletResponse response,Exception ex) throws Exception {
        response.setHeader("Error-TYPE",EXCEPTION_TYPE);
        response.setHeader("Error-MESSAGE",
                "system internal error,please contact admin!");
        response.setHeader("Error-STACK", getExceptionAllinformation(ex));
        response.setHeader("Error-CODE", "99999");
        throw ex;
    }

    //获取异常所有信息
    public String getExceptionAllinformation(Throwable ex){
        StringBuilder stringBuilder=new StringBuilder();
        StackTraceElement[] trace=ex.getStackTrace();
        for(StackTraceElement s:trace){
            stringBuilder.append("\n\tat ").append(s);
        }
        stringBuilder.append("\r\n");
        if(stringBuilder.length()!=0){
            if(stringBuilder.length()>EXCEPTION_MESSAGE_SIZE){
                return stringBuilder.substring(0,EXCEPTION_MESSAGE_SIZE);
            }
        }
        return stringBuilder.toString();
    }
}
