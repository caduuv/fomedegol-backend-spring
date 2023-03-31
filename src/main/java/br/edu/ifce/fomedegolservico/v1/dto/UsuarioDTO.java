package br.edu.ifce.fomedegolservico.v1.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Relation(collectionRelation = "usuarios", itemRelation = "usuario")
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long sequencial;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    @Pattern(regexp = "^[a-z ,.'-]+$",
            flags = {Pattern.Flag.CASE_INSENSITIVE},
            message = "Salvar nome sem acentuacões ou caracteres especiais")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @Size(max = 150, message = "O e-mail deve ter no máximo 150 caracteres")
    private String email;

    @NotNull(message = "O CPF é obrigatório")
    @CPF(message = "Insira um CPF válido")
    private String cpf;

    @NotNull(message = "A data de criação é obrigatória")
    private Date dataCriacao;

    @NotNull(message = "O telefone é obrigatório")
    private Long telefone;

    @NotNull(message = "O ID do Firebase é obrigatório")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String uIDFirebase;

}
