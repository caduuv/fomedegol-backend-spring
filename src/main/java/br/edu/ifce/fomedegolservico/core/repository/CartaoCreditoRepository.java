package br.edu.ifce.fomedegolservico.core.repository;

import br.edu.ifce.fomedegolservico.core.model.CartaoCredito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoCreditoRepository extends PagingAndSortingRepository<CartaoCredito, Long> {

    public Page<CartaoCredito> findBySequencialUsuario(Pageable pageable, Long id);

}
