package br.com.fooddelivery.tialudeliveryback.simulation; // Ou qualquer pacote principal

import br.com.fooddelivery.tialudeliveryback.model.AddressModel;
import br.com.fooddelivery.tialudeliveryback.services.AddressService;
import br.com.fooddelivery.tialudeliveryback.services.ValidacaoException;

import java.util.Map;

/**
 * Classe simples com um método main para testar o AddressService.
 */
public class TestRunner {

    public static void main(String[] args) {

        AddressService service = new AddressService();
        System.out.println("### Iniciando Testes do AddressService ###");

        // -----------------------------------------------------------------
        // CASO 1: SUCESSO (Payload Válido)
        // -----------------------------------------------------------------
        System.out.println("\n--- TESTE 1: SUCESSO ---");
        AddressModel enderecoValido = new AddressModel(
                "01001-000",        // cep
                "Rua",              // tipoLogradouro
                "Rua Direita",      // logradouro
                "100",              // numero
                "Sé",               // bairro
                "São Paulo",        // cidade
                "SP",               // estado
                "Bloco A, Sala 3",  // complemento
                "Comercial",        // tipo
                false               // padraoEntrega
        );

        try {
            Map<String, Object> respostaSucesso = service.criarEndereco(enderecoValido);
            System.out.println("Resultado (Sucesso 201): " + respostaSucesso);

            // Testa o segundo endereço para ver o ID incrementar
            Map<String, Object> respostaSucesso2 = service.criarEndereco(enderecoValido);
            System.out.println("Resultado (Sucesso 201, ID 2): " + respostaSucesso2);

        } catch (ValidacaoException e) {
            System.out.println("ERRO INESPERADO no Teste de Sucesso: " + e.getErrorResponse());
        }


        // -----------------------------------------------------------------
        // CASO 2: ERRO (CEP Inválido)
        // -----------------------------------------------------------------
        System.out.println("\n--- TESTE 2: ERRO (CEP Inválido) ---");
        AddressModel enderecoCepInvalido = new AddressModel(
                "123",              // cep (INVÁLIDO)
                "Rua",              // tipoLogradouro
                "Rua Falsa",        // logradouro
                "100", "Bairro", "Cidade", "UF", null, "Casa", false
        );

        try {
            service.criarEndereco(enderecoCepInvalido);
            System.out.println("Resultado: FALHOU (O serviço deveria ter dado erro)");
        } catch (ValidacaoException e) {
            // Este é o caminho esperado
            System.out.println("Resultado (Erro 400 Esperado): " + e.getErrorResponse());
        }

        // -----------------------------------------------------------------
        // CASO 3: ERRO (Logradouro Vazio)
        // -----------------------------------------------------------------
        System.out.println("\n--- TESTE 3: ERRO (Logradouro Vazio) ---");
        AddressModel enderecoLogradouroVazio = new AddressModel(
                "01001-000",        // cep (VÁLIDO)
                "Rua",              // tipoLogradouro
                "   ",              // logradouro (INVÁLIDO)
                "100", "Bairro", "Cidade", "UF", null, "Casa", false
        );

        try {
            service.criarEndereco(enderecoLogradouroVazio);
            System.out.println("Resultado: FALHOU (O serviço deveria ter dado erro)");
        } catch (ValidacaoException e) {
            System.out.println("Resultado (Erro 400 Esperado): " + e.getErrorResponse());
        }
    }
}