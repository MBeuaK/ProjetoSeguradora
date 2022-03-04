# Escopo e objetivo
Projeto é destino para aprimorar e amadurecer meu conhecimento em api e Java

## Como executar...
Este aplicativo é empacotado como um war que possui o Tomcat 8 incorporado. Nenhuma instalação do Tomcat ou JBoss é necessária. 

Você o executa usando o java -jarcomando.
- Verifique se você está usando o JDK 1.8 e o Maven 3.x
- Clonar este repositório com o comando:
> git clone https://github.com/MBeuaK/Seguradora.git
- Rodar o script dump.sql para criar a estrutura do banco de dados e fazer um carga inicial:
> Atenção: O script se encontra na pasta: src -> main -> resource -> dump.sql
- Execute o comando:
> java -jar equiplano-0.0.1-SNAPSHOT.war

## Utilização da API...
- Para qualquer requisição, tem que chamar o endpoint para obter o token e passar o mesmo no header.

> POST - http://localhost:8080/token

> Body:
{
"username": "equiplano",
"password": "equiplano@2022"
}

## Tecnologias...
- Java 8;
- Spring Boot;
- Banco de dados MySQL;
- Arquitetura de microserviço;
- API RESTFull;
- Swagger API V2;
- Teste unitário - JUnit 5;
