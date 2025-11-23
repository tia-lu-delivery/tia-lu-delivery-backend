# Guia de Testes - DWOO-008 Cadastro de Meios de Pagamento

## 📋 Descrição

Esta coleção do Postman contém testes completos para todos os cenários descritos no documento DWOO-008, incluindo casos de sucesso, validações e erros.

## 🚀 Como Importar a Coleção

1. Abra o Postman
2. Clique em **Import** no canto superior esquerdo
3. Selecione o arquivo `DWOO-008 - Cadastro de Meios de Pagamento.postman_collection.json`
4. A coleção será importada com todas as requisições e testes automatizados

## ⚙️ Configuração

### Variáveis de Ambiente

A coleção já vem com variáveis configuradas:

- **baseUrl**: `http://localhost:8080` (URL base da API)
- **userId**: `user9001` (ID do usuário para testes)

Para alterar estas variáveis:
1. Clique na coleção importada
2. Vá até a aba **Variables**
3. Altere os valores conforme necessário

### Pré-requisitos

- Aplicação Spring Boot rodando na porta 8080
- Banco de dados H2 configurado (já está no application.properties)

## 📂 Estrutura da Coleção

### 1. **Cenários de Sucesso** (4 testes)

Testa os critérios de aceite CA 1.1 a CA 1.8:

- ✅ **CA 1.1 - Cadastro de Cartão de Crédito Visa**
  - Valida criação com status 201
  - Verifica estrutura de resposta completa
  - Confirma bandeira VISA e tipo CREDITO

- ✅ **CA 1.1 - Cadastro de Cartão de Débito Mastercard**
  - Testa tipo DEBITO
  - Valida bandeira MASTERCARD

- ✅ **CA 1.1 - Cadastro de Vale Refeição Alelo**
  - Testa tipo VALE_REFEICAO
  - Valida bandeira ALELO

- ✅ **CA 1.1 - Cadastro com CVV de 4 dígitos (American Express)**
  - Testa CVV de 4 dígitos (padrão American Express)
  - Valida bandeira AMEX

### 2. **Cenários de Erro - Validação de Campos** (16 testes)

Testa os critérios CA 1.2, CA 1.4, CA 1.5, CA 1.7:

- ❌ **CA 1.2 - numeroCartao ausente**
- ❌ **CA 1.2 - Múltiplos campos obrigatórios ausentes**
- ❌ **CA 1.4 - Número de cartão inválido (Luhn)**
- ❌ **CA 1.4 - Número com menos de 13 dígitos**
- ❌ **CA 1.4 - Número com mais de 16 dígitos**
- ❌ **CA 1.4 - validadeMes inválido (0)**
- ❌ **CA 1.4 - validadeMes inválido (13)**
- ❌ **CA 1.5 - Data de validade retroativa**
- ❌ **CA 1.4 - CVV com menos de 3 dígitos**
- ❌ **CA 1.4 - CVV com mais de 4 dígitos**
- ❌ **CA 1.7 - CPF inválido (formato)**
- ❌ **CA 1.7 - CPF vazio**
- ❌ **CA 1.2 - tipoCartao ausente**
- ❌ **Erro - tipoCartao inválido**
- ❌ **Erro - Nome do titular vazio**

### 3. **Cenários de Erro - Cartão Duplicado** (1 teste)

Testa o critério CA 1.8 (409 Conflict):

- ❌ **CA 1.8 - Erro - Cartão já cadastrado**
  - Valida status 409 Conflict
  - Verifica código DUPLICATE_CARD
  - Confirma mensagem e ação sugerida

### 4. **Testes de Diferentes Bandeiras** (4 testes)

Testa o critério CA 1.6 (inferência de bandeira):

- ✅ **CA 1.6 - Bandeira Visa** (começa com 4)
- ✅ **CA 1.6 - Bandeira Mastercard** (começa com 2221-2720 ou 51-55)
- ✅ **CA 1.6 - Bandeira Alelo** (começa com 506, 636, 627)
- ✅ **CA 1.6 - Bandeira Amex** (começa com 34 ou 37)

### 5. **Testes Edge Cases** (2 testes)

Testa casos limítrofes:

- ✅ **Edge Case - Data de validade no mês atual**
- ✅ **Edge Case - Nome com caracteres especiais**

## 🎯 Como Executar os Testes

### Execução Manual

1. Certifique-se de que a aplicação está rodando
2. No Postman, abra a coleção "DWOO-008 - Cadastro de Meios de Pagamento"
3. Execute requisições individuais clicando em **Send**
4. Ou execute toda a coleção clicando no botão **Run** na coleção

### Execução Completa com Collection Runner

1. Clique com botão direito na coleção
2. Selecione **Run collection**
3. Configure:
   - **Iterations**: 1
   - **Delay**: 100ms (para evitar sobrecarga)
4. Clique em **Run DWOO-008...**
5. Visualize o relatório completo de testes

## ✅ Testes Automatizados

Cada requisição possui scripts de teste automatizados que validam:

- Status HTTP correto
- Estrutura da resposta JSON
- Campos obrigatórios presentes
- Valores esperados dos campos
- Mensagens de erro apropriadas
- Códigos de erro corretos
- Validação de bandeiras dos cartões
- Formato dos últimos 4 dígitos
- Máscara de exibição do cartão

### Exemplo de Teste Automatizado

```javascript
pm.test("Status code é 201 Created", function () {
    pm.response.to.have.status(201);
});

pm.test("Resposta contém idMeioPagamento", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property('idMeioPagamento');
});

pm.test(`Bandeira do cartão é ${jsonData.detalhesCartao?.bandeira || 'N/A'}`, function () {
    pm.expect(jsonData.detalhesCartao.bandeira).to.be.a('string');
});
```

## 🔍 Interpretando os Resultados

### Sucesso (Status 201)

```json
{
  "idMeioPagamento": "mpid123456789",
  "mensagem": "Cartão cadastrado com sucesso. Dados armazenados.",
  "detalhesCartao": {
    "tipo": "CREDITO",
    "bandeira": "VISA",
    "ultimosDigitos": "4444",
    "nomeExibicao": "VISA ************4444"
  }
}
```

### Erro de Validação (Status 400)

```json
{
  "codigoErro": "VALIDATION_ERROR",
  "mensagem": "Um ou mais dados do cartão estão inválidos ou ausentes.",
  "detalhes": [
    {
      "campo": "numeroCartao",
      "erro": "O número do cartão informado é inválido."
    }
  ]
}
```

### Cartão Duplicado (Status 409)

```json
{
  "codigoErro": "DUPLICATE_CARD",
  "mensagem": "Este cartão já está cadastrado em sua carteira.",
  "acaoSugerida": "O cartão já está pronto para uso. Utilize o 'mpid123456789'."
}
```

## 🎯 Cobertura de Testes

Total de requisições: **27**

- ✅ Cenários de Sucesso: 4
- ❌ Erros de Validação: 16
- ❌ Cartão Duplicado: 1
- ✅ Diferentes Bandeiras: 4
- ✅ Edge Cases: 2

### Critérios de Aceite Cobertos

- ✅ CA 1.1 - Sucesso na Criação
- ✅ CA 1.2 - Campos Obrigatórios
- ✅ CA 1.3 - Vínculo de Usuário
- ✅ CA 1.4 - Validação de Formato
- ✅ CA 1.5 - Data de Validade
- ✅ CA 1.6 - Tipagem e Bandeira
- ✅ CA 1.7 - Validação de CPF
- ✅ CA 1.8 - Máscara de Exibição e Cartão Duplicado

## 🐛 Troubleshooting

### Problema: Connection refused

**Solução**: Verifique se a aplicação Spring Boot está rodando na porta 8080.

### Problema: Testes de duplicação falhando

**Solução**: Execute primeiro um teste de sucesso para cadastrar um cartão, depois execute o teste de duplicação com os mesmos dados. O banco H2 é in-memory mas mantém dados durante a sessão da aplicação.

### Problema: Erros de validação não aparecem

**Solução**: Verifique se a aplicação está implementando corretamente todas as validações. Os testes esperam mensagens específicas de erro.

### Problema: Bandeira não identificada corretamente  

**Solução**: Confirme que a lógica de identificação de bandeiras está implementada conforme os prefixos esperados (Visa: 4, Mastercard: 5xxx/2xxx, Amex: 34/37, Alelo: 506xxx).