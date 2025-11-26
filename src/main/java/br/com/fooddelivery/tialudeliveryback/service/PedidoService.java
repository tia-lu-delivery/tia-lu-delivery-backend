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

        int pageNumber = (page < 1) ? 0 : page - 1;

        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by("dataAbertura").descending());

        Page<Pedido> paginaPedidos = pedidoRepository.findByUsuarioId(userId, pageable);

        List<PedidoResumoDTO> listaFormatada = paginaPedidos.getContent().stream()
                .map(p -> new PedidoResumoDTO(
                        p.getNumeroPedido(),
                        p.getDataAbertura(),
                        p.getStatusPedido(),
                        p.getNomeRestaurante(),
                        p.getValorTotal() != null ?
                                p.getValorTotal().setScale(2, RoundingMode.HALF_UP) :
                                BigDecimal.ZERO
                ))
                .collect(Collectors.toList());

        return new ListaPedidosResponseDTO(page, size, paginaPedidos.getTotalElements(), listaFormatada);
    }
}