package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.entity.Usuario;
import br.com.fooddelivery.tialudeliveryback.exception.RegraNegocioException;
import br.com.fooddelivery.tialudeliveryback.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void inativarConta(Long idUsuarioAutenticado) {

        Usuario usuario = usuarioRepository.findById(idUsuarioAutenticado)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado."));

        if (!usuario.isAtivo()) {
            throw new RegraNegocioException("A conta já está inativa.");
        }

        usuarioRepository.inativarUsuario(usuario.getId());
    }
}
