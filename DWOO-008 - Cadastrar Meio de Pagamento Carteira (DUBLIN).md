Como usuário, eu quero cadastrar um meio de pagamento (cartão de crédito, cartão de débito ou cartão de vale refeição) na minha carteira, informando o número do cartão, a data de validade, os digitos verificadores, o nome descrito no cartão e o cpf do comprador. 
 

Critérios de Aceite - Cadastro de Meio de Pagamento (Versão Simples/Sem Tokenização)

Critério

Descrição

CA 1.1 - Sucesso na Criação

O sistema deve armazenar os dados sensíveis do cartão de forma criptografada (assumindo que há um mecanismo de criptografia robusto no backend) e retornar um ID interno (idMeioPagamento) para o usuário autenticado.

CA 1.2 - Campos Obrigatórios

Os campos numeroCartao, validadeMes, validadeAno, cvv, nomeTitular, cpfTitular e tipoCartao são obrigatórios.

CA 1.3 - Vínculo de Usuário

O sistema deve identificar o usuário (idUsuario ou via autenticação) para vincular o cartão à carteira correta.

CA 1.4 - Validação de Formato

numeroCartao deve ter 13 a 16 dígitos e passar pela validação de Luhn. validadeMes (1-12) e validadeAno (ano futuro) devem ser válidos. cvv deve ter 3 ou 4 dígitos.

CA 1.5 - Data de Validade

A data de validade (validadeMes/validadeAno) deve ser futura em relação à data atual do sistema.

CA 1.6 - Tipagem e Bandeira

O sistema deve inferir ou validar a bandeira (Visa, Master, etc.) com base no numeroCartao e armazenar o tipoCartao (CREDITO, DEBITO ou VALE_REFEICAO).

CA 1.7 - Validação de CPF

O cpfTitular deve ser validado (formato e dígito verificador).

CA 1.8 - Máscara de Exibição

A resposta de sucesso deve retornar apenas dados não sensíveis (Ex: últimos 4 dígitos) e a bandeira, para exibição na interface do usuário.

Rota do Endpoint (Exemplo)

Detalhe

Valor Sugerido

Método HTTP

POST

Rota

/api/v1/user/{idUsuario}/payment-methods

Função

Adiciona um novo cartão à carteira do usuário (com armazenamento sensível).

Exemplo de Rota: POST /api/v1/user/user9001/payment-methods

Payload (Corpo da Requisição JSON)

A requisição incluirá todos os dados do cartão, que serão armazenados no seu backend.

Método: POST

Rota: /api/v1/user/{idUsuario}/payment-methods

JSON

{
  "numeroCartao": "4111222233334444", // Dados SENSÍVEIS (Alto Risco!)
  "validadeMes": 12,
  "validadeAno": 2028,
  "cvv": "123", // Dados SENSÍVEIS (Alto Risco!)
  "nomeTitular": "JOAO DA SILVA",
  "cpfTitular": "12345678900",
  "tipoCartao": "CREDITO" // Enum: "CREDITO", "DEBITO", "VALE_REFEICAO"
}


Respostas da API

1. Cenário de Sucesso (Status HTTP: 201 Created)

Confirmação do armazenamento do cartão.

Resposta:

JSON

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


2. Cenário de Erro - Falha de Validação (Status HTTP: 400 Bad Request)

Ocorre quando dados sensíveis estão inválidos.

Resposta:

JSON

{
  "codigoErro": "VALIDATION_ERROR",
  "mensagem": "Um ou mais dados do cartão estão inválidos ou ausentes.",
  "detalhes": [
    {
      "campo": "numeroCartao",
      "erro": "O número do cartão informado é inválido."
    },
    {
      "campo": "validadeMes",
      "erro": "A data de validade é retroativa e inválida."
    }
  ]
}


3. Cenário de Erro - Cartão Já Cadastrado (Status HTTP: 409 Conflict)

Ocorre se o sistema identificar que o cartão já está na carteira do usuário.

Resposta:

JSON

{
  "codigoErro": "DUPLICATE_CARD",
  "mensagem": "Este cartão já está cadastrado em sua carteira.",
  "acaoSugerida": "O cartão já está pronto para uso. Utilize o 'mpid123456789'."
}


Features:

#40 Criar Controller para Usuário (Bruno)
#41 Criar um Service para meio de pagamento (Gabriel)
#42 Criar uma tabela para registrar o método de pagamento e a entity para mapeamento (Kéven)
#43 Criar o repositório para o meio de pagamento (Claudio)
#44 Criar DTOs de request e response (Bruno)