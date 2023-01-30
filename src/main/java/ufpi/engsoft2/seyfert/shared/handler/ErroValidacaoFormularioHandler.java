package ufpi.engsoft2.seyfert.shared.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import ufpi.engsoft2.seyfert.shared.exception.dto.ExceptionCamposInvalidosDTO;
import ufpi.engsoft2.seyfert.shared.exception.dto.ExceptionDefaultValidacaoFormDTO;

@RestControllerAdvice
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ErroValidacaoFormularioHandler {

    private MessageSource messsageSource;

    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionDefaultValidacaoFormDTO handle(MethodArgumentNotValidException exception, HttpServletRequest request){
        List<ExceptionCamposInvalidosDTO> errosDto = new ArrayList<>();
        List<FieldError> camposComErros = exception.getBindingResult().getFieldErrors();

        camposComErros.forEach(erro -> {
            String mensagemErroValidacao = messsageSource.getMessage(erro, LocaleContextHolder.getLocale());
            ExceptionCamposInvalidosDTO erroValidacao = new ExceptionCamposInvalidosDTO(erro.getField(), mensagemErroValidacao);

            errosDto.add(erroValidacao);
        });

        ExceptionDefaultValidacaoFormDTO exceptionDTO = new ExceptionDefaultValidacaoFormDTO(
            "Campos inválidos",
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            request.getRequestURI(),
            errosDto
        );

        return exceptionDTO;
    }
    
}
