package br.edu.ifce.fomedegolservico.core.exception;

public class ErroNaoMapeadoException extends RuntimeException{

    private String message;
    public ErroNaoMapeadoException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
