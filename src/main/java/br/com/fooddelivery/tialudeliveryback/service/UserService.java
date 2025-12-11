package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.entity.UserEntity;
import br.com.fooddelivery.tialudeliveryback.repository.UserRepository;
import br.com.fooddelivery.tialudeliveryback.service.dto.EnderecoDTO;
import br.com.fooddelivery.tialudeliveryback.service.dto.UserRequestDTO;
import br.com.fooddelivery.tialudeliveryback.service.dto.UserResponseDTO;
import br.com.fooddelivery.tialudeliveryback.service.exception.EmailJaCadastradoException;
import br.com.fooddelivery.tialudeliveryback.service.exception.ValidacaoException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Base64;
import java.util.UUID;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Fluxo principal de registro do usuário, conforme critérios de aceite do professor.
     */
    public UserResponseDTO registrarUsuario(UserRequestDTO req) {

        // CA 1.3 - Unicidade de E-mail
        userRepository.findByEmail(req.getEmail())
                .ifPresent(u -> { throw new EmailJaCadastradoException(req.getEmail()); });

        // CA 1.5 - Idade mínima
        validarIdadeMinima(req.getDataNascimento());

        // CA 1.4 - Força da senha
        validarForcaSenha(req.getSenha());

        // CA 1.8 - Criptografia da senha
        String senhaCriptografada = passwordEncoder.encode(req.getSenha());

        // Montar entidade para salvar
        UserEntity entity = montarEntity(req, senhaCriptografada);

        // Salvar no banco
        UserEntity salvo = userRepository.save(entity);

        // CA 1.7 - Geração de token de acesso (placeholder simples)
        String token = gerarTokenSimples(salvo.getIdUsuario());
        long expiracaoToken = 3600L; // 1 hora em segundos

        // CA 1.1 - Sucesso na criação (montar resposta)
        return new UserResponseDTO(
                salvo.getIdUsuario(),
                "Registro concluído com sucesso. Login efetuado automaticamente.",
                token,
                expiracaoToken
        );
    }

    // ------------ Métodos auxiliares -----------------

    private static void validarIdadeMinima(LocalDate dataNascimento) {
        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();
        if (idade < 18) {
            throw new ValidacaoException("dataNascimento",
                    "A idade mínima para registro é de 18 anos.");
        }
    }

    private static void validarForcaSenha(String senha) {
        boolean tamanho = senha.length() >= 8;
        boolean maiuscula = senha.matches(".*[A-Z].*");
        boolean minuscula = senha.matches(".*[a-z].*");
        boolean numero = senha.matches(".*\\d.*");

        if (!(tamanho && maiuscula && minuscula && numero)) {
            throw new ValidacaoException(
                    "senha",
                    "A senha deve conter no mínimo 8 caracteres, uma letra maiúscula, uma minúscula e um número."
            );
        }
    }

    private static UserEntity montarEntity(UserRequestDTO req, String senhaCriptografada) {
        UserEntity entity = new UserEntity();
        entity.setNomeCompleto(req.getNomeCompleto());
        entity.setDataNascimento(req.getDataNascimento());
        entity.setEmail(req.getEmail());
        entity.setSenha(senhaCriptografada);

        EnderecoDTO end = req.getEndereco();
        entity.setCep(end.getCep());
        entity.setLogradouro(end.getLogradouro());
        entity.setNumero(end.getNumero());
        entity.setComplemento(end.getComplemento());
        entity.setBairro(end.getBairro());
        entity.setCidade(end.getCidade());
        entity.setEstado(end.getEstado());

        return entity;
    }

    /** Geração simples de token (pode virar JWT depois, se o professor pedir). */
    private static String gerarTokenSimples(String subject) {
        String payload = subject + ":" + UUID.randomUUID();
        return Base64.getEncoder().encodeToString(payload.getBytes());
    }
}
}
