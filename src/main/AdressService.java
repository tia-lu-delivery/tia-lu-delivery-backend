package com.seuprojeto.api.service;

import com.seuprojeto.api.dto.request.AddressDeleteRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    public void deletarEndereco(Long idEndereco, AddressDeleteRequestDTO request) {


        System.out.printf(
                "Excluindo endereço ID %d - Executor: %d - Motivo: %s%n",
                idEndereco, request.getIdUsuarioExecutor(), request.getMotivo()
        );
    }
}
