# 📥 Pull Request

## 1. O que está sendo entregue?

Implementação completa da **Task #162 - DWOO-029**: Criação de DTOs de Response e Exception para o endpoint de definição de endereço principal.

### Artefatos Entregues

**Código Java:**

- ✅ `SetPrincipalAddressResponseDTO` - DTO com campos: `status` (sucesso), `detalhe` (mensagem), `id_endereco_principal` (Long)
- ✅ `AddressErrorResponseDTO` - DTO com campos: `codigo` (ENDERECO_NAO_ENCONTRADO), `detalhe` (mensagem de erro)
- ✅ `AddressNotFoundException` - Exception customizada estendendo RuntimeException
- ✅ Integração no `AddressController` - endpoint PATCH `/api/v1/users/address/{id_endereco}/set-principal` com tratamento de exceções e retorno HTTP 404
- ✅ Atualização do `AddressServiceMock` - lança `AddressNotFoundException` ao invés de exceção genérica

**Documentação & Testes:**

- ✅ Coleção Postman padronizada com 5 requisições cobrindo todos os cenários
- ✅ Testes automatizados em JavaScript para validação de respostas
- ✅ Variáveis de ambiente pré-configuradas

---

## 2. Por que estamos fazendo essa entrega?

Esta entrega faz parte da **Feature DWOO-029 - Definir Endereço Principal**, que permite usuários marcarem um endereço específico como seu padrão de entrega.

**Contexto:**

- Task #162 é um pré-requisito para as tasks #163 e #164
- Implementa os critérios de aceite CA-001, CA-004, CA-005 e CA-006 da especificação DWOO-029
- Permite que o sistema valide e trate corretamente cenários de sucesso e erro na definição de endereço principal
- Segue o padrão de qualidade estabelecido pelo DWOO-008 (Cadastro de Meios de Pagamento)

---

## 3. Quais são as vantagens e as desvantagens dessa solução?

### ✅ Vantagens

1. **Estrutura clara de resposta** - DTOs bem definidas com `@JsonProperty` para serialização correta
2. **Tratamento robusto de erros** - Exception customizada permite diferenciar erros específicos
3. **Padrão consistente** - Segue o mesmo padrão de resposta do DWOO-008
4. **Testabilidade completa** - Coleção Postman com testes automatizados em JavaScript
5. **Documentação integrada** - Testes funcionam como documentação viva da API
6. **Integração com mock** - Permite testes imediatos sem dependência do DWOO-012
7. **Separação de responsabilidades** - Controller, Service e DTOs bem definidos

### ⚠️ Desvantagens

1. **Dependência do DWOO-012** - Requer a tabela/entity de endereços para funcionar em produção
2. **AddressServiceMock é temporário** - Será removido após DWOO-012 estar pronto
3. **Sem autenticação real** - UserId vem de header, sem validação de segurança
4. **Respostas em envelope** - Resposta de erro usa `{"erro": {...}}` que adiciona uma camada extra

---

## 4. Os testes unitários foram realizados?

- [x] Sim
- [ ] Não

### Cobertura de Testes

**Cenários testados na Coleção Postman:**

| Cenário | Critério | Status |
|---------|----------|--------|
| CA-001 & CA-005 | Sucesso - Marcar endereço como principal | ✅ Testado |
| CA-004 Passo 1 | Listar endereços antes da mudança | ✅ Testado |
| CA-004 Passo 2 | Mudar endereço principal | ✅ Testado |
| CA-004 Passo 3 | Validar estado após mudança | ✅ Testado |
| CA-006 | Erro - Endereço não encontrado (404) | ✅ Testado |

**Testes Automatizados incluem:**

- ✅ Validação de status HTTP (200, 404)
- ✅ Validação de estrutura JSON da resposta
- ✅ Validação de campos obrigatórios
- ✅ Validação de tipos de dados
- ✅ Validação de transição de estado (fluxo CA-004)
- ✅ Validação de mensagens de erro específicas

---

## 5. Como eu posso testar essa sua alteração?

### Pré-requisitos

- Java 11+ instalado
- Spring Boot rodando na porta 8080
- Postman instalado

### Passos para testar

#### Opção 1: Via Postman (Recomendado)

1. Abra o Postman
2. Importe a coleção: `docs/postman/DWOO-029-162 - Definir Endereço Principal (Completo).postman_collection.json`
3. Configure as variáveis de ambiente:
   - `baseUrl`: `http://localhost:8080`
   - `userId`: `user9001`
4. Execute as requisições na ordem:
   - **Cenários de Sucesso**: CA-001 e CA-005 - Definir endereço como principal
   - **Cenário de Fluxo**: CA-004 Passo 1, 2 e 3 (antes → mudança → validação)
   - **Cenários de Erro**: CA-006 - Endereço não encontrado

#### Opção 2: Via cURL

```bash
# Teste de sucesso (CA-001 & CA-005)
curl -X PATCH http://localhost:8080/api/v1/users/address/12345/set-principal \
  -H "Content-Type: application/json" \
  -H "UserId: user9001"

# Teste de erro - endereço não encontrado (CA-006)
curl -X PATCH http://localhost:8080/api/v1/users/address/9999999/set-principal \
  -H "Content-Type: application/json" \
  -H "UserId: user9001"
```

#### Opção 3: Via IntelliJ IDEA (HTTP Client)

Criar arquivo `test.http` e executar as requisições:

```http
### Sucesso - CA-001 & CA-005
PATCH http://localhost:8080/api/v1/users/address/12345/set-principal
Content-Type: application/json
UserId: user9001

###

### Erro - CA-006
PATCH http://localhost:8080/api/v1/users/address/9999999/set-principal
Content-Type: application/json
UserId: user9001
```

### Respostas Esperadas

**Sucesso (HTTP 200):**

```json
{
  "status": "sucesso",
  "detalhe": "O endereço 12345 foi definido como principal.",
  "id_endereco_principal": 12345
}
```

**Erro (HTTP 404):**

```json
{
  "erro": {
    "codigo": "ENDERECO_NAO_ENCONTRADO",
    "detalhe": "O endereço especificado não existe ou não pertence ao usuário."
  }
}
```

---

## 6. Evidências de que sua alteração funciona

### ✅ Implementação Verificada

**Arquivos criados/modificados:**

- ✅ `SetPrincipalAddressResponseDTO.java` - Criado com @Data e @JsonProperty
- ✅ `AddressErrorResponseDTO.java` - Criado com estrutura de erro
- ✅ `AddressNotFoundException.java` - Criado como RuntimeException
- ✅ `AddressController.java` - Modificado com try-catch e tratamento de 404
- ✅ `AddressServiceMock.java` - Modificado para lançar AddressNotFoundException
- ✅ `DWOO-029-162 - Definir Endereço Principal (Completo).postman_collection.json` - Coleção validada

**Commits gerados:**

```text
b6ce9bf - feat(postman): adiciona coleção completa para testar cenários de definição de endereço principal
7220065 - fix(address): substitui exceção genérica por AddressNotFoundException ao buscar endereço
607188c - feat(exceptions): cria a exceção AddressNotFoundException para tratamento de erros de endereço não encontrado
ef45c11 - feat(address): cria DTO para resposta de erro de endereço com código e detalhe
4a537a3 - fix(address): melhora tratamento de exceção para endereço não encontrado na definição de endereço principal
```

**Testes na Coleção Postman:**

- 5 requisições com cenários completos
- 28+ asserções JavaScript automatizadas
- Cobertura de CA-001, CA-004, CA-005 e CA-006
- Variáveis de ambiente reutilizáveis

**Padrão de Qualidade:**

- Código segue padrões Spring Boot
- DTOs com Lombok (@Data)
- Nomenclatura snake_case para JSON (via @JsonProperty)
- Exception handling robusto
- Respostas com envelope de erro consistente

---

## ✅ Checklist de Entrega

- [x] Código implementado conforme especificação DWOO-029
- [x] Testes automatizados criados e validados
- [x] DTOs com serialização correta (@JsonProperty)
- [x] Exception customizada criada
- [x] Controller atualizado com tratamento de erros
- [x] Coleção Postman padronizada
- [x] Variáveis de ambiente pré-configuradas
- [x] Commits com mensagens claras
- [x] Compatível com DWOO-012 (quando estiver pronto)
- [x] 100% dos critérios de aceite implementados
<img width="1915" height="1032" alt="teste parte 1" src="https://github.com/user-attachments/assets/320cb5f1-f991-461d-b05f-3723c99f4202" />
<img width="1896" height="1032" alt="teste parte 2" src="https://github.com/user-attachments/assets/77294bd1-24fa-4a26-8c4a-60933ce18baa" />
