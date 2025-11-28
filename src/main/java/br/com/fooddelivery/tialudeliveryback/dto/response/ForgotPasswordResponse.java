package br.com.fooddelivery.tialudeliveryback.dto.response;

public class ForgotPasswordResponse {

  private String status;
  private String password;

  public ForgotPasswordResponse() {
    this.status = "sucesso";
    this.password = "XyHHuuP12#4";
  }

  public ForgotPasswordResponse(String status, String password) {
    this.status = status;
    this.password = password;
  }

  public String getStatus() {
    return status;
  }

  public String getPassword() {
    return password;
  }
}