package br.edu.ifce.fomedegolservico.v1.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Relation(collectionRelation = "cartoes", itemRelation = "cartao")
public class CartaoCreditoDTO extends RepresentationModel<CartaoCreditoDTO> implements Serializable {

    private Long sequencialCartao;

    @NotNull
    private Long sequencialUsuario;

    @NotNull
    @DecimalMin(value = "0", message = "O número do cartão deve ser maior que 0")
    @DecimalMax(value = "9999999999999999", message = "O número do cartão deve ter no máximo 16 dígitos")
    private BigDecimal numeroCartao;

    @NotNull
    @DecimalMin(value = "0", message = "O CVV deve ser maior que 0")
    @DecimalMax(value = "999", message = "O CVV deve ter no máximo 3 dígitos")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private BigDecimal cvv;

    @NotBlank(message = "O nome do titular é obrigatório")
    private String nomeTitular;

    @NotNull
    @DecimalMin(value = "1", message = "O mês de validade deve estar entre 1 e 12")
    @DecimalMax(value = "12", message = "O mês de validade deve estar entre 1 e 12")
    private BigDecimal mesValidade;

    @NotNull
    @DecimalMin(value = "2022", message = "O ano de validade deve ser igual ou maior que o ano atual")
    private BigDecimal anoValidade;

    private String apelido;



}
