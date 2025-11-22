package br.com.fooddelivery.tialudeliveryback.dto.request;

public class ForgotPasswordRequest {

  private String email;
  private String dataNascimento;

  public ForgotPasswordRequest() {
  }

  public ForgotPasswordRequest(String email, String dataNascimento) {
    this.email = email;
    this.dataNascimento = dataNascimento;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(String dataNascimento) {
    this.dataNascimento = dataNascimento;
  }
}