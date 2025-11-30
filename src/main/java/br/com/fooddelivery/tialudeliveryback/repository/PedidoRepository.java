package br.com.fooddelivery.tialudeliveryback.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository {

    // Esse método será implementado quando houver banco e entidade Pedido
    Object buscarPedidoCompleto(String numeroPedido);
}