# Guia de Testes - DWOO-029-162 Definir Endereço Principal

## 📋 Descrição

Esta coleção do Postman contém testes completos para todos os cenários descritos no documento DWOO-029, incluindo casos de sucesso, fluxos e tratamento de erros.

## 🚀 Como Importar a Coleção

1. Abra o Postman
2. Clique em **Import** no canto superior esquerdo
3. Selecione o arquivo `DWOO-029-162 - Definir Endereço Principal (Completo).postman_collection.json`
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
- Feature DWOO-012 ou AddressServiceMock implementada

## 📂 Estrutura da Coleção

### 1. **Cenários de Sucesso** (1 teste)

Testa os critérios de aceite CA-001 e CA-005:

- ✅ **CA-001 e CA-005 - Definir endereço como principal**
  - Valida criação com status 200 OK
  - Verifica estrutura de resposta completa
  - Confirma status "sucesso"
  - Valida presença de `id_endereco_principal`
  - Verifica mensagem de confirmação em `detalhe`

### 2. **Cenário de Fluxo - CA-004** (3 testes)

Testa o critério CA-004 (mudança de endereço principal com desmarcação automática):

- ✅ **CA-004 Passo 1 - Listar endereços antes da mudança**
  - Valida status 200 OK
  - Confirma resposta é um array de endereços
  - Identifica endereço principal atual
  - Salva ID do endereço principal em variável de ambiente

- ✅ **CA-004 Passo 2 - Definir novo endereço como principal**
  - Valida status 200 OK
  - Confirma novo endereço foi definido como principal
  - Verifica resposta contém `id_endereco_principal` correto

- ✅ **CA-004 Passo 3 - Validar estado após mudança**
  - Valida status 200 OK
  - Confirma novo endereço (12345) agora tem `padrao_entrega: true`
  - Verifica endereço anterior foi automaticamente desmarcado
  - Garante apenas um endereço é principal

### 3. **Cenários de Erro** (1 teste)

Testa o critério CA-006 (endereço não encontrado):

- ❌ **CA-006 - Endereço não encontrado**
  - Valida status 404 Not Found
  - Verifica estrutura de erro com campo `erro`
  - Confirma código `ENDERECO_NAO_ENCONTRADO`
  - Valida mensagem de erro descritiva

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
   - **Delay**: 200ms (para permitir processamento entre requisições)
4. Clique em **Run DWOO-029...**
5. Visualize o relatório completo de testes

### Execução de Grupos Específicos

**Apenas Cenários de Sucesso:**
1. Selecione a pasta "Cenários de Sucesso"
2. Clique em **Run** ou **Send**

**Apenas Fluxo CA-004:**
1. Selecione a pasta "Cenário de Fluxo - CA-004"
2. Clique em **Run** para executar os 3 passos em sequência
3. **Importante**: Execute em ordem pois os passos são dependentes

**Apenas Cenários de Erro:**
1. Selecione a pasta "Cenários de Erro"
2. Clique em **Run**

## ✅ Testes Automatizados

Cada requisição possui scripts de teste automatizados que validam:

- Status HTTP correto (200, 404)
- Estrutura da resposta JSON
- Campos obrigatórios presentes
- Valores esperados dos campos
- Mensagens de erro apropriadas
- Códigos de erro corretos
- Transição de estado entre requisições
- Apenas um endereço marcado como principal

### Exemplo de Teste Automatizado (CA-001 & CA-005)

```javascript
pm.test("Status code é 200 OK", function () {
    pm.response.to.have.status(200);
});

var jsonData = pm.response.json();

pm.test("Resposta contém status sucesso", function () {
    pm.expect(jsonData.status).to.eql('sucesso');
});

pm.test("Resposta contém id_endereco_principal", function () {
    pm.expect(jsonData.id_endereco_principal).to.equal(12345);
});

pm.test("Resposta contém detalhe descritivo", function () {
    pm.expect(jsonData.detalhe).to.be.a('string');
    pm.expect(jsonData.detalhe.length).to.be.greaterThan(0);
});
```

### Exemplo de Teste Automatizado (CA-004 Passo 3)

```javascript
pm.test("Novo endereço 12345 agora tem padrao_entrega true", function () {
    var endereco = jsonData.find(e => e.id === 12345);
    pm.expect(endereco.padrao_entrega).to.be.true;
});

pm.test("Apenas um endereço é principal", function () {
    var count = jsonData.filter(e => e.padrao_entrega === true).length;
    pm.expect(count).to.equal(1);
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

### Erro - Não Encontrado (Status 404)

```json
{
  "erro": {
    "codigo": "ENDERECO_NAO_ENCONTRADO",
    "detalhe": "O endereço especificado não existe ou não pertence ao usuário."
  }
}
```

### Resposta de Listagem (Status 200)

```json
[
  {
    "id": 12345,
    "cep": "01001-000",
    "logradouro": "Rua Direita",
    "padrao_entrega": true
  },
  {
    "id": 67890,
    "cep": "01234-567",
    "logradouro": "Av. Paulista",
    "padrao_entrega": false
  }
]
```

## 🎯 Cobertura de Testes

Total de requisições: **5**

- ✅ Cenários de Sucesso: 1
- ✅ Fluxo CA-004: 3
- ❌ Erros: 1

### Critérios de Aceite Cobertos

- ✅ CA-001 - Marcar endereço como principal
- ✅ CA-004 - Desmarcação automática de endereço anterior
- ✅ CA-005 - Resposta 200 OK com confirmação
- ✅ CA-006 - Resposta 404 para endereço não encontrado

## 📊 Fluxo de Testes Recomendado

Para validação completa da feature, execute na seguinte ordem:

```text
1. CA-001 e CA-005 (Sucesso básico)
   ↓
2. CA-004 Passo 1 (Listar antes)
   ↓
3. CA-004 Passo 2 (Mudar para novo)
   ↓
4. CA-004 Passo 3 (Validar transição)
   ↓
5. CA-006 (Erro - não encontrado)
```

## 🐛 Troubleshooting

### Problema: Connection refused

**Solução**: Verifique se a aplicação Spring Boot está rodando na porta 8080.

```bash
# Verifique a porta 8080
netstat -ano | findstr :8080
```

### Problema: Erro 404 em todos os testes

**Solução**: Confirme que o endpoint está implementado:
- Verifique se `AddressController` tem o método `setPrincipalAddress`
- Confirme se a rota é `/api/v1/users/address/{id_endereco}/set-principal`

### Problema: Testes de CA-004 falhando

**Solução**: Execute os 3 passos em sequência, não individualmente:
- Passo 1 salva o ID do endereço principal em variável de ambiente
- Passo 2 depende dessa variável
- Passo 3 valida o resultado do Passo 2
- Não execute em paralelo

### Problema: Status 500 Internal Server Error

**Solução**: Verifique logs da aplicação:
- Confirme que `AddressServiceMock` está implementado
- Valide se `AddressNotFoundException` existe
- Verifique se DTOs estão corretos

### Problema: Campo `erro` não aparece na resposta 404

**Solução**: Confirme o tratamento de exceção no `AddressController`:
- Valide se está capturando `AddressNotFoundException`
- Confirme se retorna `Map.of("erro", errorDTO)`

### Problema: Variáveis de ambiente não funcionam

**Solução**: Redefina as variáveis:
1. Clique na coleção
2. Aba **Variables**
3. Limpe e redefina `baseUrl` e `userId`
4. Salve a coleção

## 📝 Notas Importantes

### Dados de Teste

Os dados usados nos testes são fictícios e servem apenas para validação:
- **ID 12345**: Endereço disponível para testes de sucesso
- **ID 67890**: Endereço pré-marcado como principal (usado em CA-004)
- **ID 9999999**: Endereço inexistente (usado para testar erro)

### Dependência com DWOO-012

Esta feature depende de `DWOO-012 - Cadastro de Endereço`:
- Em produção, requer a implementação completa do `AddressService`
- Atualmente usa `AddressServiceMock` para facilitar testes
- O mock será removido após DWOO-012 estar pronto

### Headers Obrigatórios

- `Content-Type: application/json` (já configurado nas requisições)
- `UserId`: Deve ser passado como header (configurável nas variáveis)

## ✅ Checklist de Testes Bem-Sucedidos

- [x] CA-001 e CA-005 retornam status 200 com estrutura correta
- [x] CA-004 Passo 1 lista endereços corretamente
- [x] CA-004 Passo 2 muda o endereço principal
- [x] CA-004 Passo 3 valida apenas um endereço como principal
- [x] CA-006 retorna 404 para endereço inexistente
- [x] Todas as asserções JavaScript passam
- [x] Mensagens de erro são apropriadas
- [x] Variáveis de ambiente funcionam

## 📞 Suporte

Para dúvidas ou problemas:

1. Verifique os logs da aplicação Spring Boot
2. Confirme se o banco H2 está rodando
3. Valide a estrutura do JSON retornado
4. Teste com cURL para isolar problemas do Postman

