package br.edu.ifce.fomedegolservico.core.service;

import br.edu.ifce.fomedegolservico.core.model.CartaoCredito;
import br.edu.ifce.fomedegolservico.core.repository.CartaoCreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CartaoCreditoServiceImpl implements ICartaoCreditoService{

    @Autowired
    private CartaoCreditoRepository cartaoCreditoRepository;

    @Override
    public Page<CartaoCredito> buscaCartoesPorUsuarioId(Pageable pageable, Long idUsuario) {
        return cartaoCreditoRepository.findBySequencialUsuario(pageable, idUsuario);
    }
}
