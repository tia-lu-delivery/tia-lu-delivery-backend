package com.fooddelivery.domain.pagamento.entity;

public enum StatusPagamento {
    APROVADO,   // Se o pagamento for bem-sucedido (CA 1.1)
    REJEITADO,  // Se o pagamento for recusado pelo banco (CA 1.5)
    ERRO        // Para falhas internas, como erro de envio (CA 1.6)
}