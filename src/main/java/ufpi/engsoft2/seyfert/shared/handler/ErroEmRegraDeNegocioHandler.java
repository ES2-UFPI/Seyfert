package ufpi.engsoft2.seyfert.shared.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import ufpi.engsoft2.seyfert.shared.exception.BussinesRuleException;
import ufpi.engsoft2.seyfert.shared.exception.dto.ExceptionDefaultDTO;

@RestControllerAdvice
public class ErroEmRegraDeNegocioHandler {
    
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BussinesRuleException.class)
    public ExceptionDefaultDTO handler(BussinesRuleException businessRuleException, HttpServletRequest request){
        return new ExceptionDefaultDTO(
            businessRuleException.getMessage(), 
            HttpStatus.BAD_REQUEST.value(), 
            request.getRequestURI()
        );
    }
}
