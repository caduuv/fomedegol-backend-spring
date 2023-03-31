package br.edu.ifce.fomedegolservico.core.service;

import br.edu.ifce.fomedegolservico.core.model.CartaoCredito;
import br.edu.ifce.fomedegolservico.core.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUsuarioService {

    Page<Usuario> findAll(Pageable pageable);
    Usuario findById(Long id);
    Usuario save(Usuario usuario);
    Usuario update(Long id, Usuario usuario);
    void delete(Long id);
    Page<Usuario> findByNomeContainingIgnoreCase(Pageable pageable, String nome);

    public Page<CartaoCredito> buscaCartaoCreditoPorUsuarioId(Pageable pageable, Long id);
}
