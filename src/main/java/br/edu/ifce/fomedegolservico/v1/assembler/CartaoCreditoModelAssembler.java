package br.edu.ifce.fomedegolservico.v1.assembler;

import br.edu.ifce.fomedegolservico.core.model.CartaoCredito;
import br.edu.ifce.fomedegolservico.v1.dto.CartaoCreditoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CartaoCreditoModelAssembler implements RepresentationModelAssembler<CartaoCredito, CartaoCreditoDTO>{

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CartaoCreditoDTO toModel(CartaoCredito entity) {

        CartaoCreditoDTO dto = modelMapper.map(entity, CartaoCreditoDTO.class);
        dto.setNumeroCartao(BigDecimal.valueOf(Long.parseLong(dto.getNumeroCartao().toString().substring(0, 12))));
        return dto;
    }

    @Override
    public CollectionModel<CartaoCreditoDTO> toCollectionModel(Iterable<? extends CartaoCredito> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }



}
