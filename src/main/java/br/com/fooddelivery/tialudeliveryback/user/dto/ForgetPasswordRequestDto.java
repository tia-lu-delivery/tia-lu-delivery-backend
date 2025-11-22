package br.com.fooddelivery.tialudeliveryback.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgetPasswordRequestDto {

    @NotNull(message = "O campo email é obrigatório")
    @Email(message = "O formato do email é inválido")
    private String email;

    @NotNull(message = "O campo dataNascimento é obrigatório")
    private LocalDate dataNascimento;
}
