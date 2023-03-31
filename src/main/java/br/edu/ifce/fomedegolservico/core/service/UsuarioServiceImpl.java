package br.edu.ifce.fomedegolservico.core.service;

import br.edu.ifce.fomedegolservico.core.exception.NenhumRegistroEncontradoException;
import br.edu.ifce.fomedegolservico.core.model.CartaoCredito;
import br.edu.ifce.fomedegolservico.core.model.Usuario;
import br.edu.ifce.fomedegolservico.core.repository.CartaoCreditoRepository;
import br.edu.ifce.fomedegolservico.core.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ICartaoCreditoService cartaoCreditoService;

    @Override
    public Page<Usuario> findAll(Pageable pageable)  {
        logger.info("Iniciando busca por usuários...");

        Page<Usuario> page = usuarioRepository.findAll(pageable);

        if(!page.isEmpty()){
            return page;
        }else{
            logger.info("Nenhum registro encontrado...");
            throw new NenhumRegistroEncontradoException();
        }
    }

    @Override
    public Usuario findById(Long id) {
        logger.info("Iniciando busca de usuario por id...");
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            return usuario.get();
        }else{
            logger.info("Nenhum registro encontrado...");
            throw new NenhumRegistroEncontradoException();
        }
    }

    @Override
    public Usuario save(Usuario usuario) {

        logger.info("Salvando novo usuário...");
        return usuarioRepository.save(usuario);

    }

    @Override
    public Usuario update(Long id, Usuario usuario) {

        logger.info("Editando usuário {}...", id);
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

    @Override
    public Page<CartaoCredito> buscaCartaoCreditoPorUsuarioId(Pageable pageable, Long id){
        return cartaoCreditoService.buscaCartoesPorUsuarioId(pageable, id);
    }


}
