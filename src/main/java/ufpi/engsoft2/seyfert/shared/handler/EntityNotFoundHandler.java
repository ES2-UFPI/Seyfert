package ufpi.engsoft2.seyfert.shared.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import ufpi.engsoft2.seyfert.shared.exception.EntityNotFoundException;
import ufpi.engsoft2.seyfert.shared.exception.dto.ExceptionDefaultDTO;

@RestControllerAdvice
public class EntityNotFoundHandler {
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Entity not found")
    @ExceptionHandler(EntityNotFoundException.class)
    public Object handler(EntityNotFoundException ex, HttpServletRequest request) {
        return ExceptionDefaultDTO.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND.value())
                .path(request.getRequestURI()).build();
    }
}
