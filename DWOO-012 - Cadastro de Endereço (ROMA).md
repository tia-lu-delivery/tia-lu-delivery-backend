Como usuário eu quero cadastrar os meus endereços de entrega, informando o cep, o tipo de logradouro, o logradouro, o bairro, a cidade, o estado, além de um complemento e a informação se aquele endereço é residencial ou comercial e se é o endereço padrão de entrega.

Critérios de Aceite 

ID

Descrição

CA-001

O usuário deve ser capaz de adicionar um novo endereço de entrega.

CA-007

Em caso de sucesso, a API deve retornar o código de status HTTP 201 (Created) e OBRIGATORIAMENTE incluir o ID único do novo endereço criado no corpo da resposta.

CA-008

Em caso de falha de validação dos dados, a API deve retornar HTTP 400 (Bad Request) com detalhes do erro.

Exemplo de Rota do Endpoint

Método

Endpoint (Rota)

Descrição

POST

/api/v1/users/address

Cria um novo endereço de entrega para o usuário autenticado.

Payload 

{
  "cep": "01001-000",
  "tipo_logradouro": "Rua",
  "logradouro": "Direita",
  "numero": "100",
  "bairro": "Sé",
  "cidade": "São Paulo",
  "estado": "SP",
  "complemento": "Bloco A, Sala 3",
  "tipo": "Comercial",
  "padrao_entrega": false
}


Respostas da API (Responses)

Resposta de Sucesso (HTTP 201 Created)

JSON

{
  "id_endereco": 12345,
  "status": "criado",
  "link_para_consulta": "/api/v1/usuarios/enderecos/12345"
}


Nota: O campo id_endereco é o identificador único criado pelo sistema e é essencial para que o usuário ou cliente saibam qual recurso foi criado e possam manipulá-lo posteriormente (atualizar, excluir, ou consultar).

Resposta de Erro: Dados Inválidos (HTTP 400 Bad Request)

(O formato de erro permanece o mesmo)

JSON

{
  "erro": {
    "codigo": "ERRO_VALIDACAO",
    "detalhe": "Os dados fornecidos são inválidos.",
    "campos_com_erro": [
      {
        "campo": "cep",
        "mensagem": "Formato de CEP inválido. Use o padrão 00000-000 ou 00000000."
      }
    ]
  }
}


Features:

#61 Criar Controller para o Endereço
#62 Criar Services para o Endereço
#63 Criar a tabela e a entity para mapear a tabela do endereço
#64 Criar o repository para o endereço
#65 Criar DTOs de Request e Response