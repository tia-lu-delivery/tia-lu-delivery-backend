package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.dto.ListaPedidosResponseDTO;
import br.com.fooddelivery.tialudeliveryback.dto.PedidoResumoDTO;
import br.com.fooddelivery.tialudeliveryback.entity.Pedido;
import br.com.fooddelivery.tialudeliveryback.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public ListaPedidosResponseDTO listarPedidosUsuario(Long userId, int page, int size) {

        // Ajuste para evitar página 0 (o Spring Data JPA usa base 0)
        int pageNumber = (page < 1) ? 0 : page - 1;

        // CA-003: Ordenação Decrescente por dataAbertura
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by("dataAbertura").descending());

        // CA-001/CA-002: Busca no banco pelo ID do usuário
        Page<Pedido> paginaPedidos = pedidoRepository.findByUsuarioId(userId, pageable);

        // CA-004: Converte a Entidade para o DTO formatado
        List<PedidoResumoDTO> listaFormatada = paginaPedidos.getContent().stream()
                .map(p -> new PedidoResumoDTO(
                        p.getNumeroPedido(),
                        // O tipo já é OffsetDateTime
                        p.getDataAbertura(),
                        p.getStatusPedido(),
                        p.getNomeRestaurante(),
                        // ATUALIZADO: Agora Entidade é BigDecimal, conversão mais limpa
                        p.getValorTotal() != null ?
                                p.getValorTotal().setScale(2, RoundingMode.HALF_UP) :
                                BigDecimal.ZERO
                ))
                .collect(Collectors.toList());

        // CA-007: Retorna a estrutura de paginação (funciona com array vazio se totalPedidos for 0)
        return new ListaPedidosResponseDTO(page, size, paginaPedidos.getTotalElements(), listaFormatada);
    }
}