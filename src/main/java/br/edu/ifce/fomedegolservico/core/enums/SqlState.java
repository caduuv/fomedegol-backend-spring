package br.edu.ifce.fomedegolservico.core.enums;

public enum SqlState {
    // Constraint violation
    UNIQUE_VIOLATION("23505"),

    // Successful completion
    SUCCESS("00000"),

    // Warning
    NO_DATA("02000"),
    STRING_DATA_RIGHT_TRUNCATION("01004"),

    // Connection exceptions
    CONNECTION_DOES_NOT_EXIST("08003"),

    // Data exception
    NUMERIC_VALUE_OUT_OF_RANGE("22003"),

    // Authorization and access control exceptions
    INSUFFICIENT_PRIVILEGE("42501");

    private String code;

    SqlState(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}