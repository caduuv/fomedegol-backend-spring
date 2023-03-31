package br.edu.ifce.fomedegolservico.v1.advice;

import br.edu.ifce.fomedegolservico.core.enums.ConstraintsBaseDados;
import br.edu.ifce.fomedegolservico.core.exception.MessageExceptionHandler;
import br.edu.ifce.fomedegolservico.core.exception.MessageExceptionHandlerBuilder;
import br.edu.ifce.fomedegolservico.core.exception.NenhumRegistroEncontradoException;
import br.edu.ifce.fomedegolservico.v1.controller.UsuarioController;
import org.hibernate.exception.ConstraintViolationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@ControllerAdvice(basePackages = "br.edu.ifce.fomedegolservico", basePackageClasses = {UsuarioController.class})
public class UsuarioControllerAdvice {

    @Autowired
    private MessageExceptionHandlerBuilder exceptionHandlerBuilder;

    @ResponseBody
    @ExceptionHandler(NenhumRegistroEncontradoException.class)
    public ResponseEntity<MessageExceptionHandler> handleNenhumRegistroEncontrado(NenhumRegistroEncontradoException nenhumRegistroEncontrado){
        return ResponseEntity
                .ok()
                .body(exceptionHandlerBuilder
                        .withCurrentTimestamp()
                        .withMensagem("Nenhum Registro Encontrado")
                        .withStatus(HttpStatus.OK)
                        .build());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageExceptionHandler> handleValid(MethodArgumentNotValidException methodArgumentNotValidException){

        Optional<FieldError> optionalMessage = Optional.ofNullable(methodArgumentNotValidException.getFieldError());
        var message = "Erro inesperado na aplicação";

        if(optionalMessage.isPresent()){
            message = optionalMessage.get().getDefaultMessage();
        }

        return ResponseEntity
                .badRequest()
                .body(exceptionHandlerBuilder
                        .withMensagem(message)
                        .withCurrentTimestamp()
                        .withStatus(HttpStatus.BAD_REQUEST)
                        .build());
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<MessageExceptionHandler> handleUniqueKey(@NotNull ConstraintViolationException constraintViolationException){

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptionHandlerBuilder
                        .withCurrentTimestamp()
                        .withMensagem(ConstraintsBaseDados.getMessageByConstraint(constraintViolationException.getConstraintName()))
                        .withStatus(HttpStatus.BAD_REQUEST)
                        .build());


    }

}
