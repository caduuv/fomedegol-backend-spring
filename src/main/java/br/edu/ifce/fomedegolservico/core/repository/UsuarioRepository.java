package br.edu.ifce.fomedegolservico.core.repository;

import br.edu.ifce.fomedegolservico.core.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

    Page<Usuario> findAll(Pageable pageable);
    Optional<Usuario> findById(Long id);
    Usuario save(Usuario usuario);

    void deleteById(Long id);

    Page<Usuario> findByNomeContainingIgnoreCase(Pageable pageable, String nome);

}