package com.admin.common.exception;

import com.admin.common.code.ExceptionCodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommonException extends RuntimeException {
    private Integer code;
    private String msg;
    private HttpStatus httpStatus;

    public CommonException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMsg());
        this.code = exceptionCodeEnum.getCode();
        this.msg = exceptionCodeEnum.getMsg();
        this.httpStatus = exceptionCodeEnum.getHttpStatus();
    }
}

