package br.edu.ifce.fomedegolservico.core.service;

import br.edu.ifce.fomedegolservico.core.model.CartaoCredito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICartaoCreditoService {

    public Page<CartaoCredito> buscaCartoesPorUsuarioId(Pageable pageable, Long idUsuario);

}
