# Projeção Populacional
## Descrição
Aplicação SpringBoot que calcula a projeção da população brasileira para um determinado momento.

## Configurações

- Clonar o projeto
- Editar o arquivo application.properties que esta na pasta src/main/resources, alterar a opção tt.api.log.path, com o caminho e nome do arquivo de logs que será gerado.
- No terminal na raiz do projeto executar o comando para compilar
```sh
mvn clean package install
```
- Para executar, na pasta target executar o comando
```sh
java -jar projecaopopulacional-0.0.1-SNAPSHOT.jar
```
- O projeto vai estar rodando na porta 8080.


## Endpoints

A aplicação oferece dois endpoints para consulta, o primeiro deles.
```sh
http://localhost:8080/tt/projecaopopulacional/projecoes?dataProjecao={data}
```

> Nota: `data` é necessária para a consulta e deve ser superior a data do atual, seguindo o padrão ISO. Como por exemplo 2022-07-14T14:06:03

Resposta da API

|  | Retorno |
| ------ | ------ |
| dataPopulacaoAtual | Data do valor da população atual |
| dataProjecao | Data da projeção |
| populacaoAtual | A população atual |
| populacaoProjetada | A população projetada. |
| incrementoPopulacional | A diferença entre as duas populações |

O segundo endpoint é responsavel por prover as consultas realizadas na API.
```sh
http://localhost:8080/tt/projecaopopulacional/projecoes/log
```

|  | README |
| ------ | ------ |
| horaAcesso | Data Hora da chamada |
| dataProjecao | Data Hora solicitação para projeção |
| populacaoAntesProjecao | A população estimada no momento |
| populacaoProjetada | A população projetada para a data solicitada |

