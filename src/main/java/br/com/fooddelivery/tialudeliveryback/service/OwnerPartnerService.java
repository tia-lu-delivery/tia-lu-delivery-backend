package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.dto.OwnerPartnerRequestDTO;
import br.com.fooddelivery.tialudeliveryback.dto.OwnerPartnerResponseDTO;
import br.com.fooddelivery.tialudeliveryback.exception.BusinessException;
import br.com.fooddelivery.tialudeliveryback.exception.ResourceNotFoundException;
import br.com.fooddelivery.tialudeliveryback.models.OwnerPartner;
import br.com.fooddelivery.tialudeliveryback.repository.OwnerPartnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerPartnerService {

    private final OwnerPartnerRepository ownerPartnerRepository;
    private final MerchantService merchantService;

    @Transactional
    public OwnerPartnerResponseDTO createOwnerPartner(String idEstabelecimento, OwnerPartnerRequestDTO request) {
        log.info("Iniciando cadastro de sócio proprietário para estabelecimento: {}", idEstabelecimento);

        validateMerchantExists(idEstabelecimento);

        if (ownerPartnerRepository.existsByCpf(request.getCpf())) {
            log.warn("Tentativa de cadastro com CPF já existente: {}", request.getCpf());
            throw new BusinessException("CPF já cadastrado no sistema");
        }

        OwnerPartner entity = OwnerPartner.builder()
                .idEstabelecimento(idEstabelecimento)
                .cpf(request.getCpf())
                .rg(request.getRg())
                .orgaoEmissorRg(request.getOrgaoEmissorRg())
                .nomeCompleto(request.getNomeCompleto())
                .email(request.getEmail().toLowerCase())
                .telefone(request.getTelefone())
                .build();

        OwnerPartner savedEntity = ownerPartnerRepository.save(entity);

        log.info("Sócio proprietário cadastrado com sucesso. ID: {}", savedEntity.getIdSocio());

        return OwnerPartnerResponseDTO.success(savedEntity.getIdSocio().toString());
    }

    private void validateMerchantExists(String idEstabelecimento) {
        if (!merchantService.existsById(idEstabelecimento)) {
            log.error("Estabelecimento não encontrado: {}", idEstabelecimento);
            throw new ResourceNotFoundException(
                    "O estabelecimento com ID '" + idEstabelecimento + "' não foi encontrado."
            );
        }
    }
}
