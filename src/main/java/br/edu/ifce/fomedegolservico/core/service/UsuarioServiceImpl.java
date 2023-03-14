package br.edu.ifce.fomedegolservico.core.service;

import br.edu.ifce.fomedegolservico.core.enums.ConstraintsBaseDados;
import br.edu.ifce.fomedegolservico.core.enums.SqlState;
import br.edu.ifce.fomedegolservico.core.exception.CPFInvalidoException;
import br.edu.ifce.fomedegolservico.core.exception.CampoExistenteException;
import br.edu.ifce.fomedegolservico.core.exception.ErroNaoMapeadoException;
import br.edu.ifce.fomedegolservico.core.exception.NenhumRegistroEncontradoException;
import br.edu.ifce.fomedegolservico.core.model.Usuario;
import br.edu.ifce.fomedegolservico.core.repository.UsuarioRepository;
import br.edu.ifce.fomedegolservico.core.util.PatternUtil;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private Logger LOGGER = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Page<Usuario> findAll(Pageable pageable)  {
        LOGGER.debug("Iniciando busca por usuários...");

        Page<Usuario> page = usuarioRepository.findAll(pageable);

        if(!page.isEmpty()){
            return page;
        }else{
            throw new NenhumRegistroEncontradoException();
        }
    }

    @Override
    public Usuario findById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            return usuario.get();
        }else{
            throw new NenhumRegistroEncontradoException();
        }
    }

    @Override
    public Usuario save(Usuario usuario) {

        LOGGER.debug("Salvando novo usuário...");
        return usuarioRepository.save(usuario);

    }

    @Override
    public Usuario update(Long id, Usuario usuario) {

        usuario.setSequencial(id);
        return usuarioRepository.save(usuario);

    }

    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Page<Usuario> findByNomeContainingIgnoreCase(Pageable pageable, String nome) {

        Page<Usuario> page = usuarioRepository.findByNomeContainingIgnoreCase(pageable, nome);

        if(!page.isEmpty()){
            return page;
        }else{
            throw new NenhumRegistroEncontradoException();
        }
    }


}
