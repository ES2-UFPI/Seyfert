package ufpi.engsoft2.seyfert.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BussinesRuleException extends RuntimeException{
    public BussinesRuleException(String mensagem){
        super(mensagem);
    }
}
