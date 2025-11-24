package br.com.fooddelivery.tialudeliveryback.dto;

public class PaymentMethodResponseDTO {

    private String idMeioPagamento;
    private String status;
    private String detalhe;

    private DadosExibicaoDTO dadosExibicao;

    public PaymentMethodResponseDTO() {}

    public static class DadosExibicaoDTO {
        private String bandeira;
        private String ultimosDigitos;
        private String tipoCartao;
        private String validade;

        public DadosExibicaoDTO() {}

        public DadosExibicaoDTO(String bandeira, String ultimosDigitos, String tipoCartao, String validade) {
            this.bandeira = bandeira;
            this.ultimosDigitos = ultimosDigitos;
            this.tipoCartao = tipoCartao;
            this.validade = validade;
        }

        public String getBandeira() { return bandeira; }
        public void setBandeira(String bandeira) { this.bandeira = bandeira; }

        public String getUltimosDigitos() { return ultimosDigitos; }
        public void setUltimosDigitos(String ultimosDigitos) { this.ultimosDigitos = ultimosDigitos; }

        public String getTipoCartao() { return tipoCartao; }
        public void setTipoCartao(String tipoCartao) { this.tipoCartao = tipoCartao; }

        public String getValidade() { return validade; }
        public void setValidade(String validade) { this.validade = validade; }
    }
// Getters e Setters
    public String getIdMeioPagamento() { return idMeioPagamento; }
    public void setIdMeioPagamento(String idMeioPagamento) { this.idMeioPagamento = idMeioPagamento; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDetalhe() { return detalhe; }
    public void setDetalhe(String detalhe) { this.detalhe = detalhe; }

    public DadosExibicaoDTO getDadosExibicao() { return dadosExibicao; }
    public void setDadosExibicao(DadosExibicaoDTO dadosExibicao) { this.dadosExibicao = dadosExibicao; }
}
