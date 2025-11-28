package br.com.fooddelivery.tialudeliveryback.user.controller;

import br.com.fooddelivery.tialudeliveryback.user.dto.ForgetPasswordRequestDto;
import br.com.fooddelivery.tialudeliveryback.user.dto.ForgetPasswordResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @PostMapping("/forget-password")
    public ResponseEntity<ForgetPasswordResponseDto> forgetPassword(
            @Valid @RequestBody ForgetPasswordRequestDto request) {

        ForgetPasswordResponseDto response = new ForgetPasswordResponseDto(
                "sucesso",
                "XyHHuuP12#4"
        );

        return ResponseEntity.ok(response);
    }
}
