public class PedidoService {
public PedidoResponseDTO buscarDetalhesDoPedido(int numeroDoPedido) {

        // 1. Usa sua classe Restaurante como um repositório para buscar o pedido
        Pedido pedido = Restaurante.buscarPedidoPorNumero(numeroDoPedido);

        // 2. Trata o Critério de Aceite CA-006 (Pedido Não Encontrado)
        if (pedido == null) {
            // Lança uma exceção que será capturada pela camada da API
            // para gerar a resposta 404 Not Found.
            throw new PedidoNaoEncontradoException(
                    "O pedido com o número '" + numeroDoPedido + "' não existe para este estabelecimento."
                    );
        }
