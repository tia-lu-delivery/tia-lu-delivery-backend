package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.exception.ValidacaoPedidoException;
import br.com.fooddelivery.tialudeliveryback.model.Usuario;
import br.com.fooddelivery.tialudeliveryback.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    private final UsuarioRepository usuarioRepository;

    public ContaService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void inativarConta(Long idUsuarioAutenticado) {

        Usuario usuario = usuarioRepository.findById(idUsuarioAutenticado)
                .orElseThrow(() ->
                        new ValidacaoPedidoException("CA-003 - Usuário não encontrado para o id informado.")
                );

        if (!usuario.isAtivo()) {
            throw new ValidacaoPedidoException("CA-007 - A conta do usuário já está inativa.");
        }

        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
    }
}
