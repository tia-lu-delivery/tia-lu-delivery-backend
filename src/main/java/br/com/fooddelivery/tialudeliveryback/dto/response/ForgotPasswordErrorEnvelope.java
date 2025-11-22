package br.com.fooddelivery.tialudeliveryback.dto.response;

import java.util.List;

public class ForgotPasswordErrorEnvelope {

  private Error erro;

  public ForgotPasswordErrorEnvelope() {
  }

  public ForgotPasswordErrorEnvelope(Error erro) {
    this.erro = erro;
  }

  public Error getErro() {
    return erro;
  }

  public void setErro(Error erro) {
    this.erro = erro;
  }

  public static class Error {
    private String codigo;
    private String detalhe;
    private List<CampoErro> campos_com_erro;

    public Error() {
    }

    public Error(String codigo, String detalhe, List<CampoErro> campos_com_erro) {
      this.codigo = codigo;
      this.detalhe = detalhe;
      this.campos_com_erro = campos_com_erro;
    }

    public String getCodigo() {
      return codigo;
    }

    public String getDetalhe() {
      return detalhe;
    }

    public List<CampoErro> getCampos_com_erro() {
      return campos_com_erro;
    }
  }

  public static class CampoErro {
    private String campo;
    private String mensagem;

    public CampoErro() {
    }

    public CampoErro(String campo, String mensagem) {
      this.campo = campo;
      this.mensagem = mensagem;
    }

    public String getCampo() {
      return campo;
    }

    public String getMensagem() {
      return mensagem;
    }
  }

  public static ForgotPasswordErrorEnvelope formatoInvalidoData() {
    var campoErro = new CampoErro("dataNascimento", "Formato de data inválido. Use YYYY-MM-DD.");
    var error = new Error("ERRO_FORMATO_DADOS", "A requisição contém erros de formato.", List.of(campoErro));
    return new ForgotPasswordErrorEnvelope(error);
  }
}