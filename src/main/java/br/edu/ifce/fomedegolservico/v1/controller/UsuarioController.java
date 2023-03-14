package br.edu.ifce.fomedegolservico.v1.controller;


import br.edu.ifce.fomedegolservico.core.model.Usuario;
import br.edu.ifce.fomedegolservico.core.service.IUsuarioService;
import br.edu.ifce.fomedegolservico.v1.assembler.UsuarioModelAssembler;
import br.edu.ifce.fomedegolservico.v1.dto.UsuarioDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/usuarios")
@Api(tags = "Usuario Controller")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private PagedResourcesAssembler pagedResourcesAssembler;

    @GetMapping
    @ApiOperation(value="Retorna o resultado paginado de todos os usuarios", response = Boolean.class)
    public ResponseEntity<PagedModel> findAll(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "20") int size,
                                              @RequestParam(defaultValue = "sequencial") String sortBy,
                                              @RequestParam(defaultValue = "true") Boolean ascending,
                                              @RequestParam(defaultValue = "") String nome)  {

        PageRequest pageable = PageRequest.of(page, size, Sort.by(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));

        Page<Usuario> entityPage = usuarioService.findByNomeContainingIgnoreCase(pageable, nome);

        return ResponseEntity
                .ok()
                .contentType(MediaTypes.HAL_JSON)
                .body(pagedResourcesAssembler.toModel(entityPage, usuarioModelAssembler));

    }


    @PostMapping
    @ApiOperation(value="Cria um novo usuário e o retorna", response = Boolean.class)
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO usuarioDTO)  {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaTypes.HAL_JSON)
                .body(usuarioModelAssembler.toModel(usuarioService.save(modelMapper.map(usuarioDTO, Usuario.class))));

    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable("id") Long id)  {

        return ResponseEntity
                .ok()
                .contentType(MediaTypes.HAL_JSON)
                .body(usuarioModelAssembler.toModel(usuarioService.findById(id)));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id)  {

        usuarioService.delete(id);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable("id") Long id, @Valid @RequestBody UsuarioDTO usuarioDTO)  {

        return ResponseEntity
                .ok()
                .contentType(MediaTypes.HAL_JSON)
                .body(usuarioModelAssembler.toModel(usuarioService.update(id, modelMapper.map(usuarioDTO, Usuario.class))));

    }


}