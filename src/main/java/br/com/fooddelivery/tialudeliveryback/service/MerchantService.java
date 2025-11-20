package br.com.fooddelivery.tialudeliveryback.service;

public package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.dto.MerchantRequestDTO;
import br.com.fooddelivery.tialudeliveryback.dto.MerchantResponseDTO;
import br.com.fooddelivery.tialudeliveryback.entity.Merchant;
import br.com.fooddelivery.tialudeliveryback.repository.MerchantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public MerchantService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    @Transactional
    public MerchantResponseDTO createMerchant(MerchantRequestDTO request) {
        Merchant merchant = new Merchant();
        merchant.setName(request.name());
        merchant.setCnpj(request.cnpj());
        merchant.setAddress(request.address());
        merchant.setActive(request.active() != null ? request.active() : true);

        Merchant savedMerchant = merchantRepository.save(merchant);

        return new MerchantResponseDTO(
            savedMerchant.getId(),
            savedMerchant.getName(),
            savedMerchant.getCnpj(),
            savedMerchant.getAddress(),
            savedMerchant.getActive()
        );
    }

    @Transactional(readOnly = true)
    public List<MerchantResponseDTO> findAll() {
        return merchantRepository.findAll().stream()
            .map(m -> new MerchantResponseDTO(m.getId(), m.getName(), m.getCnpj(), m.getAddress(), m.getActive()))
            .collect(Collectors.toList());
    }
}
 {
    
}
