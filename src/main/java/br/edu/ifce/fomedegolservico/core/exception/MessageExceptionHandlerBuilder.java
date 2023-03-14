package br.edu.ifce.fomedegolservico.core.exception;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class MessageExceptionHandlerBuilder {
    private Date timestamp;
    private Integer status;
    private String mensagem;

    public MessageExceptionHandlerBuilder withTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public MessageExceptionHandlerBuilder withCurrentTimestamp() {
        this.timestamp = new Date();
        return this;
    }

    public MessageExceptionHandlerBuilder withStatus(HttpStatus status) {
        this.status = status.value();
        return this;
    }

    public MessageExceptionHandlerBuilder withMensagem(String mensagem) {
        this.mensagem = mensagem;
        return this;
    }

    public MessageExceptionHandler build() {
        return new MessageExceptionHandler(timestamp, status, mensagem);
    }
}
