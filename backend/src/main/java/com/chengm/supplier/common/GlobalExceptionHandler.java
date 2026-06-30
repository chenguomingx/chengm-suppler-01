package com.chengm.supplier.common;

import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Result<Void> handleRuntimeException(RuntimeException e) {
        return Result.error(e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public Result<Void> handleValidationException(Exception e) {
        String message = "参数校验失败";
        if (e instanceof MethodArgumentNotValidException) {
            message = ((MethodArgumentNotValidException) e).getBindingResult()
                    .getFieldError() != null ? ((MethodArgumentNotValidException) e).getBindingResult()
                    .getFieldError().getDefaultMessage() : message;
        } else if (e instanceof BindException) {
            message = ((BindException) e).getBindingResult()
                    .getFieldError() != null ? ((BindException) e).getBindingResult()
                    .getFieldError().getDefaultMessage() : message;
        }
        return Result.error(400, message);
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        return Result.error("系统异常：" + e.getMessage());
    }
}
