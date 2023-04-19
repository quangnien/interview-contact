package com.example.demo.exception;

import com.example.demo.enumdef.HttpStatus;
import lombok.Getter;

import java.util.Locale;

@Getter
public class BusinessException extends BaseException {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    public static final int STATUS = HttpStatus.BAD_REQUEST.value();

    public static final String MESSAGE = HttpStatus.BAD_REQUEST.name();

    public static final String TITLE = "Business Exception";

    public BusinessException(ExceptionCode exceptionCode) {
        super(STATUS, MESSAGE, TITLE, exceptionCode, null, null);
    }

    public BusinessException(String exceptionCode) {
        super(STATUS, MESSAGE, TITLE, exceptionCode, null, null);
    }

    public BusinessException(String exceptionCode, Object[] args, String defaultMessage, Locale locale) {
        super(STATUS, MESSAGE, TITLE, exceptionCode, args, defaultMessage);
    }

}
