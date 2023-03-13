package br.edu.ifce.fomedegolservico.core.enums;

public enum ConstraintsBaseDados {

    EMAIL("usuario_email_key", "Email já está em uso"),
    UID_FIREBASE("usuario_uid_firebase_key", "Já existe um usuário com este exato UID do Firebase"),
    CPF("usuario_cpf_key",  "CPF já está em uso");

    private String constraint;
    private String message;

    ConstraintsBaseDados(String constraint, String message) {
            this.constraint = constraint;
            this.message = message;
    }

    public String getConstraint() {
        return constraint;
    }

    public String getMessage() {
        return message;
    }

    public static String getMessageByConstraint(String constraint) {
        for (ConstraintsBaseDados c : values()) {
            if (c.getConstraint().equals(constraint)) {
                return c.getMessage();
            }
        }
        return "Erro de Conflito na Base de Dados não Identificado";
    }

}
