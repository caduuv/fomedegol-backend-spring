package br.edu.ifce.fomedegolservico.core.exception;

public class CampoExistenteException extends RuntimeException{

    private String message;
    public CampoExistenteException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
