//package com.example.demo.api;
//
//import com.example.demo.enumdef.HttpStatus;
//import com.example.demo.exception.ErrorResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.support.DefaultMessageSourceResolvable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@ControllerAdvice
//public class CustomExceptionHandler {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        List<String> errors = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                .collect(Collectors.toList());
//        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation Failed", errors);
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
//        LOGGER.error("Unhandled exception: ", ex);
//        ErrorResponse response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                "Something went wrong. Please try again later.", Collections.emptyList());
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//}
