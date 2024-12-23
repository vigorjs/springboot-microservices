package com.virgo.product.config.advisers;

import com.virgo.product.config.advisers.exception.NotFoundException;
import com.virgo.product.config.advisers.exception.ValidateException;
import com.virgo.product.utils.responseWrapper.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Arrays;

@ControllerAdvice
@CrossOrigin
public class AppWideExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        return Response.renderError(e.getMessage(), HttpStatus.NOT_FOUND, Arrays.asList(e.getStackTrace()));
    }

    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<?> handleValidationException(ValidateException e) {
        return Response.renderError(e.getMessage(), HttpStatus.BAD_REQUEST, Arrays.asList(e.getStackTrace()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return Response.renderError("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, Arrays.asList(e.getStackTrace()));
    }
}
