# Guia de Testes - DWOO-029 Definir Endereço Principal

## 📋 Descrição

Esta coleção do Postman contém testes completos para todos os cenários descritos no documento DWOO-029, incluindo casos de sucesso, validações e erros para a funcionalidade de definir endereço principal.

## 🚀 Como Importar a Coleção

1. Abra o Postman
2. Clique em **Import** no canto superior esquerdo
3. Selecione o arquivo `DWOO-029 - Definir Endereço Principal.postman_collection.json`
4. A coleção será importada com todas as requisições e testes automatizados

## ⚙️ Configuração

### Variáveis de Ambiente

A coleção já vem com variáveis configuradas:

- **baseUrl**: `http://localhost:8080` (URL base da API)
- **userId**: `1` (ID do usuário para testes)

Para alterar estas variáveis:
1. Clique na coleção importada
2. Vá até a aba **Variables**
3. Altere os valores conforme necessário

### Pré-requisitos

- Aplicação Spring Boot rodando na porta 8080
- Banco de dados H2 configurado (já está no application.properties)
- Dados mock de endereços inicializados (IDs: 12345, 67890)

## 📂 Estrutura da Coleção

### 1. **Cenários de Sucesso** (4 testes)

Testa os critérios de aceite CA-001, CA-004 e CA-005:

#### **Fluxo de Validação - Comparação (Antes/Depois)**

- ✅ **Passo 1 - Listar Estado Inicial**
  - Valida estado inicial do banco mock
  - Verifica que endereço 67890 está marcado como principal

- ✅ **Passo 2 - Alterar Principal para 12345**
  - Executa a mudança de endereço principal
  - Valida resposta de sucesso

- ✅ **Passo 3 - Validar Troca (Final)**
  - Confirma que endereço 12345 agora é o principal (true)
  - Confirma que endereço 67890 foi desmarcado (false)
  - Valida CA-004: apenas um endereço principal por usuário

#### **CA-001 - Definir Endereço como Principal**

- ✅ **Teste de sucesso básico**
  - Valida status 200 OK
  - Verifica estrutura de resposta completa
  - Confirma status "sucesso" e id_endereco_principal correto

### 2. **Cenários de Erro - Regras de Negócio** (1 teste)

Testa o critério CA-006:

- ❌ **CA-006 - Erro - Endereço Não Encontrado**
  - Valida status 404 Not Found
  - Verifica código de erro ENDERECO_NAO_ENCONTRADO
  - Confirma mensagem explicativa apropriada

### 3. **Testes Edge Cases** (1 teste)

Testa casos limítrofes:

- ✅ **Edge Case - Troca Recíproca (Voltar para o anterior)**
  - Verifica se o sistema permite múltiplas mudanças
  - Testa volta do endereço principal para o estado anterior

## 🎯 Como Executar os Testes

### Execução Manual

1. Certifique-se de que a aplicação está rodando
2. No Postman, abra a coleção "DWOO-029 - Definir Endereço Principal"
3. Execute requisições individuais clicando em **Send**
4. Ou execute toda a coleção clicando no botão **Run** na coleção

### Execução Completa com Collection Runner

1. Clique com botão direito na coleção
2. Selecione **Run collection**
3. Configure:
   - **Iterations**: 1
   - **Delay**: 100ms (para evitar conflitos de dados)
4. Clique em **Run DWOO-029...**
5. Visualize o relatório completo de testes

## ✅ Testes Automatizados

Cada requisição possui scripts de teste automatizados que validam:

- Status HTTP correto (200, 404)
- Estrutura da resposta JSON
- Campos obrigatórios presentes
- Valores esperados dos campos
- Mensagens de erro apropriadas
- Códigos de erro corretos
- Validação da regra de negócio CA-004 (único principal)

### Exemplo de Teste Automatizado

```javascript
pm.test("Status code é 200 OK", function () {
    pm.response.to.have.status(200);
});

pm.test("Resposta contém status 'sucesso'", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property('status');
    pm.expect(jsonData.status).to.eql("sucesso");
});

pm.test("CA-004 - Endereço 12345 deve ser TRUE (Novo Principal)", function () {
    var novo = jsonData.find(e => e.id === 12345);
    pm.expect(novo.padrao_entrega).to.be.true;
});
```

## 🔍 Interpretando os Resultados

### Sucesso (Status 200)

```json
{
  "status": "sucesso",
  "detalhe": "O endereço 12345 foi definido como principal.",
  "id_endereco_principal": 12345
}
```

### Erro - Endereço Não Encontrado (Status 404)

```json
{
  "erro": {
    "codigo": "ENDERECO_NAO_ENCONTRADO",
    "detalhe": "O endereço especificado não existe."
  }
}
```

## 🎯 Cobertura de Testes

Total de requisições: **6**

- ✅ Cenários de Sucesso: 4
- ❌ Erros de Regra de Negócio: 1
- ✅ Edge Cases: 1

### Critérios de Aceite Cobertos

- ✅ **CA-001** - Marcar endereço específico como principal
- ✅ **CA-002** - Validação de autenticação (mock userId)
- ✅ **CA-003** - Uso do método PATCH
- ✅ **CA-004** - Regra de único endereço principal (desmarca outros)
- ✅ **CA-005** - Retorno HTTP 200 com confirmação
- ✅ **CA-006** - Retorno HTTP 404 para endereço inexistente

## 📊 Dados Mock Utilizados

A coleção trabalha com os seguintes endereços mock:

- **Endereço ID 12345**: Não principal inicialmente
- **Endereço ID 67890**: Principal inicial (padrao_entrega = true)
- **Endereço ID 9999**: Inexistente (para teste de erro)

## 🐛 Troubleshooting

### Problema: Connection refused

**Solução**: Verifique se a aplicação Spring Boot está rodando na porta 8080.

### Problema: Testes de regra CA-004 falhando

**Solução**: Execute os testes na ordem sequencial. O fluxo de validação precisa ser executado passo a passo para garantir que a regra de único principal funcione corretamente.