package br.com.fooddelivery.tialudeliveryback.repositories;

import br.com.fooddelivery.tialudeliveryback.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentMethod, Long> {

    /**
     * Busca um método de pagamento pelo ID do usuário e número do cartão criptografado.
     * Usado para verificar se o cartão já está cadastrado na carteira do usuário.
     *
     * @param idUsuario ID do usuário
     * @param numeroCartaoCriptografado Número do cartão criptografado
     * @return Optional contendo o método de pagamento se encontrado
     */
    Optional<PaymentMethod> findByIdUsuarioAndNumeroCartaoCriptografado(String idUsuario, String numeroCartaoCriptografado);

    /**
     * Busca todos os métodos de pagamento de um usuário.
     *
     * @param idUsuario ID do usuário
     * @return Lista de métodos de pagamento do usuário
     */
    List<PaymentMethod> findByIdUsuario(String idUsuario);

    /**
     * Busca um método de pagamento pelo ID interno do meio de pagamento.
     *
     * @param idMeioPagamento ID interno do meio de pagamento
     * @return Optional contendo o método de pagamento se encontrado
     */
    Optional<PaymentMethod> findByIdMeioPagamento(String idMeioPagamento);

    /**
     * Verifica se existe algum método de pagamento para um usuário específico.
     *
     * @param idUsuario ID do usuário
     * @return true se o usuário possui métodos de pagamento cadastrados
     */
    boolean existsByIdUsuario(String idUsuario);

    /**
     * Conta quantos métodos de pagamento um usuário possui.
     *
     * @param idUsuario ID do usuário
     * @return Número de métodos de pagamento do usuário
     */
    @Query("SELECT COUNT(p) FROM PaymentMethod p WHERE p.idUsuario = :idUsuario")
    long countByIdUsuario(@Param("idUsuario") String idUsuario);
}