package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.entity.Pagamento;
import br.com.fooddelivery.tialudeliveryback.entity.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, UUID> {

    Optional<Pagamento> findByIdPedido(String idPedido);
    boolean existsByIdPedido(String idPedido);

    List<Pagamento> findByStatusPagamento(StatusPagamento statusPagamento); // CORREÇÃO: Usar Enum

    Optional<Pagamento> findByIdTransacao(String idTransacao);

    @Query("SELECT p FROM Pagamento p WHERE p.erroEnvioEstabelecimento = true")
    List<Pagamento> findComErroEnvio();
}
