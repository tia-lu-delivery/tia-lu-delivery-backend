package br.com.fooddelivery.tialudeliveryback.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerPartnerResponseDTO {
    private String idSocio;
    private String mensagem;
    public static OwnerPartnerResponseDTO success(String idSocio){
        return OwnerPartnerResponseDTO.builder()
        .idSocio(idSocio)
        .mensagem("Sócio proprietário cadastrado e vinculado ao estabelecimento com sucesso. Cadastro finalizado!")
        .build();
    }

}