// só pra relembrar que o Service é onde fica a nossa regra de negócio do nosso sistema - não esquecer
package br.com.fooddelivery.tialudeliveryback.service;


import br.com.fooddelivery.tialudeliveryback.dto.MerchantRequestDTO;
import br.com.fooddelivery.tialudeliveryback.exception.CnpjConflictException;

import br.com.fooddelivery.tialudeliveryback.model.Endereco;
import br.com.fooddelivery.tialudeliveryback.model.Merchant;
import br.com.fooddelivery.tialudeliveryback.repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // Injeta o MerchantRepository automaticamente (Injeção de Dependência)
public class MerchantService {

    // Dependência final para o "arquivo" (banco de dados)
    private final MerchantRepository merchantRepository;

    /**
     * Método principal para registrar um novo estabelecimento.
     *
     * @param requestDTO O DTO (formulário) vindo do Controller.
     * @return A Entidade Merchant salva (com o ID gerado).
     */
    public Merchant registerMerchant(MerchantRequestDTO requestDTO) {


        // aqui vai verificar se existe o CNPJ antes de qualquer coisa
        if (merchantRepository.existsByCnpj(requestDTO.getCnpj())) {
            // se ele realmente existir, vamos dar um aviso dizendo que já existe
            throw new CnpjConflictException(
                    "O CNPJ '" + requestDTO.getCnpj() + "' já está cadastrado em nossa base de dados."
            );
        }

        // AC 1.6 - Validação da Inscrição Estadual (IE)
        if (requestDTO.getIe() != null && !requestDTO.getIe().isBlank()) {
            // Se o campo foi preenchido, validamos ele e aqui variámos a verificação dele, como o código ficaria muito extenso por conta dos 26 estados 1 DF, analisar isso aqui com o Caos
        }

        // se ele passou por tudo, vamos salvar
        Merchant merchantToSave = mapDtoToEntity(requestDTO);

        // salva a entidade no banco de dados e retorna a entidade salva (agora com ID)
        return merchantRepository.save(merchantToSave);
    }

    /**
     * método auxiliar privado para converter o DTO (Requisição) em Entidade (Modelo).
     * isso mantém o método principal (registerMerchant) mais limpo.
     *
     * @param dto O DTO de entrada.
     * @return
     */
    private Merchant mapDtoToEntity(MerchantRequestDTO dto) {
        Merchant merchant = new Merchant();

        // mapeia os dados do "Merchant"
        merchant.setCnpj(dto.getCnpj());
        merchant.setRazaoSocial(dto.getRazaoSocial());
        merchant.setNomeFantasia(dto.getNomeFantasia());
        merchant.setIe(dto.getIe()); // Pode ser nulo (opcional)

        // mapeia os dados do "Endereço" (Embeddable)
        Endereco endereco = new Endereco();
        endereco.setCep(dto.getEndereco().getCep());
        endereco.setLogradouro(dto.getEndereco().getLogradouro());
        endereco.setNumero(dto.getEndereco().getNumero());
        endereco.setComplemento(dto.getEndereco().getComplemento()); // pode ser nulo
        endereco.setBairro(dto.getEndereco().getBairro());
        endereco.setCidade(dto.getEndereco().getCidade());
        endereco.setEstado(dto.getEndereco().getEstado());

        // associa o endereço ao merchant
        merchant.setEndereco(endereco);

        return merchant;
    }
}