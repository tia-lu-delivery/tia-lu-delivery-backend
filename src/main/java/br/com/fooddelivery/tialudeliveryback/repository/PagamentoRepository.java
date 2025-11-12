package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, String> {
    Optional<Pagamento> findByIdPedido(String idPedido);
    boolean existsByIdPedido(String idPedido);
    List<br.com.fooddelivery.tialudeliveryback.repository.Pagamento> findByStatusPedido(String statusPedido);
    Optional<Pagamento> findByIdTransacao(String idTransacao);

    @Query("SELECT p FROM Pagamento p WHERE p.statusPedido = 'PAGO_ERRO_ENVIO'")
    List<Pagamento> findComErroEnvio();
}