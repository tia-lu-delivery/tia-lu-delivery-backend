package com.seuprojeto.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressDeleteRequestDTO {

    @NotNull(message = "O ID do usuário que solicitou a exclusão é obrigatório.")
    private Long idUsuarioExecutor;

    @NotBlank(message = "O motivo da exclusão é obrigatório.")
    private String motivo;
}
