package br.edu.ifce.fomedegolservico.v1.assembler;

import br.edu.ifce.fomedegolservico.core.model.Usuario;
import br.edu.ifce.fomedegolservico.v1.controller.UsuarioController;
import br.edu.ifce.fomedegolservico.v1.dto.UsuarioDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, UsuarioDTO>{

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UsuarioDTO toModel(Usuario entity) {
        UsuarioDTO dto = modelMapper.map(entity, UsuarioDTO.class);

        dto.add(linkTo(methodOn(UsuarioController.class).findById(entity.getSequencial())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(UsuarioController.class).update(entity.getSequencial(), dto)).withSelfRel().withType("PUT"));
        dto.add(linkTo(methodOn(UsuarioController.class).delete(entity.getSequencial())).withSelfRel().withType("DELETE"));
        dto.add(linkTo(methodOn(UsuarioController.class).findAll(0, 20, "nome", true, "")).withRel(IanaLinkRelations.COLLECTION));

        dto.add(linkTo(methodOn(UsuarioController.class).buscaCartoesCredito(entity.getSequencial(),0, 20)).withRel("cartoes-credito"));

        return dto;
    }

    @Override
    public CollectionModel<UsuarioDTO> toCollectionModel(Iterable<? extends Usuario> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }

    public Usuario toEntity(UsuarioDTO dto){
        return modelMapper.map(dto, Usuario.class);
    }



}
