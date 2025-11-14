package br.com.fooddelivery.tialudeliveryback.services;

import br.com.fooddelivery.tialudeliveryback.model.AddressModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Serviço responsável pela lógica de negócio de Endereços.
 */
@Service()
public class AddressService {

    // Regex para validar o CEP "00000-000" ou "00000000"
    private static final Pattern CEP_PATTERN = Pattern.compile("^(\\d{5}-\\d{3}|\\d{8})$");

    // Simulação de um repositório ou contador de ID
    private long idCounter = 12344;

    public Map<String, Object> criarEndereco(AddressModel address) {

        // --- 1. Lógica de Validação (para a Resposta de Erro 400) ---

        // Validação de CEP, conforme o exemplo de erro
        if (address.getCep() == null || !CEP_PATTERN.matcher(address.getCep()).matches()) {
            // Lança a exceção com os dados exatos do erro
            throw new ValidacaoException("cep", "Formato de CEP inválido. Use o padrão 00000-000 ou 00000000.");
        }

        if (address.getLogradouro() == null || address.getLogradouro().trim().isEmpty()) {
            throw new ValidacaoException("logradouro", "Logradouro não pode ser vazio.");
        }


        // --- 2. Lógica de "Salvar" (Simulação) ---

        // Simulando a geração de um novo ID pelo banco de dados
        long novoId = ++idCounter; // Ex: 12345


        // --- 3. Preparar Resposta de Sucesso (para 201 Created) ---

        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("id_endereco", novoId);
        successResponse.put("status", "criado");
        successResponse.put("link_para_consulta", "/api/v1/usuarios/enderecos/" + novoId);

        return successResponse;
    }

}