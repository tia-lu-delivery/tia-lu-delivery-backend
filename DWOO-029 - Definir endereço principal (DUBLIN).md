Como usuário da plataforma eu quero tornar um endereço com o principal dos meus endereços

📝 Critérios de Aceite

ID

Descrição

CA-001

O usuário deve ser capaz de marcar um endereço específico já cadastrado como seu endereço principal (padrão de entrega).

CA-002

O sistema deve validar a autenticação do usuário e garantir que o endereço a ser marcado como padrão pertença à sua conta.

CA-003

A operação deve usar o método PUT ou PATCH na rota do endereço específico.

CA-004

A ação de marcar um endereço como padrão (padrao_entrega: true) deve, automaticamente, desmarcar (padrao_entrega: false) qualquer outro endereço que estivesse com esse status na conta do usuário.

CA-005

Em caso de sucesso, a API deve retornar o código de status HTTP 200 (OK) com a confirmação e o novo status do endereço.

CA-006

Se o endereço a ser marcado como padrão não for encontrado ou não pertencer ao usuário, a API deve retornar o código HTTP 404 (Not Found).

Exemplo de Rota (PATCH)

Esta rota foca na ação de "marcar como principal" usando o ID do endereço na URL.

Método

Endpoint (Rota)

Descrição

PATCH

/api/v1/users/address/{id_endereco}/set-principal

Define o endereço especificado como o endereço principal de entrega do usuário.

Exemplo de chamada: PATCH /api/v1/users/address/12345/set-principal

➡️ Respostas da API (Responses)

✅ Resposta de Sucesso (HTTP 200 OK)

JSON

{
  "status": "sucesso",
  "detalhe": "O endereço 12345 foi definido como principal.",
  "id_endereco_principal": 12345
}


❌ Resposta de Erro: Não Encontrado (HTTP 404 Not Found)

JSON

{
  "erro": {
    "codigo": "ENDERECO_NAO_ENCONTRADO",
    "detalhe": "O endereço especificado não existe ou não pertence ao usuário."
  }
}


Features:

#160 Criar método set-principal no AddressController
#161 Criar Repository para Endereço
#162 Criar DTOs de Response e Exception
#163 Criar queries customizadas no AddressRepository para buscar e atualizar endereço principal
#164 Criar DTOs de resposta (SetPrincipalAddressResponseDTO e AddressErrorResponseDTO) e exception (AddressNotFoundException)