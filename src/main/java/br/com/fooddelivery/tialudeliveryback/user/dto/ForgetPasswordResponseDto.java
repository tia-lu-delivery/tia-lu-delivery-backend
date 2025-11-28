package br.com.fooddelivery.tialudeliveryback.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgetPasswordResponseDto {
    private String status;
    private String password;
}
