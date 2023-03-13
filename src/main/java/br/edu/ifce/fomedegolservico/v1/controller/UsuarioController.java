package br.edu.ifce.fomedegolservico.v1.controller;

import br.edu.ifce.fomedegolservico.core.model.Usuario;
import br.edu.ifce.fomedegolservico.core.service.IUsuarioService;
import br.edu.ifce.fomedegolservico.v1.dto.UsuarioDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @GetMapping
    @ApiOperation(value="Retorna o resultado paginado de todos os usuarios", response = Boolean.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resultados paginados"),
            @ApiResponse(code = 200, message = "Nenhum registro foi encontrado")
    })
    public ResponseEntity<Page<UsuarioDTO>> findAll(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "20") int size,
                                                    @RequestParam(defaultValue = "sequencial") String sortBy,
                                                    @RequestParam(defaultValue = "true") Boolean ascending,
                                                    @RequestParam(defaultValue = "") String nome)  {

        PageRequest pageable = PageRequest.of(page, size, Sort.by(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));

        Page<Usuario> entityPage = usuarioService.findByNomeContainingIgnoreCase(pageable, nome);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityPage.map(element -> modelMapper.map(element, UsuarioDTO.class)));

    }


    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO usuarioDTO)  {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(modelMapper.map(usuarioService.save(modelMapper.map(usuarioDTO, Usuario.class)), UsuarioDTO.class));

    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable("id") Long id)  {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(modelMapper.map(usuarioService.findById(id), UsuarioDTO.class));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id)  {

        usuarioService.delete(id);
        return ResponseEntity.ok().build();

    }
}