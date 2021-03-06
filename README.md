# API RESTful
## Descrição do Projeto

API RESTful para possibilitar a leitura da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards.

Tabela de conteúdos
===================

<!--ts-->
   * [Tabela de conteúdos](#tabela-de-conteúdos)
   * [Pré-requisitos](#pré-requisitos)
   * [Clonando a aplicação](#clonando-a-aplicação)
   * [Conteúdo](#conteúdo)
   * [Exemplo de resposta](#exemplo-de-resposta)
   * [Uso](#uso)
      * [Executando a aplicação por linha de comando](#executando-a-aplicação-por-linha-de-comando)
      * [Executando a aplicação pela IDE](#executando-a-aplicação-pela-ide)
      * [Recursos](#recursos)
  * [Funcionalidades](#funcionalidades)
<!--te-->

Pré-requisitos
------------------------

* Java 1.8+
* Maven
* Git

Clonando a aplicação
--------------------
>     git clone git@github.com:andreysantana/java-api-restful.git

Conteúdo
--------

Mapeamento dos recursos disponíveis são apresentados na tabela a seguir:

URI path                         | Resource class           | HTTP method   | 
-------------------------------- | ------------------------ | ------------- | 
/producers                       | ProducerResource         | GET          | 

Exemplo de resposta
-------------------

A aplicação deve retornar:

```javascript
{
    "min": [
        {
            "name": "JOEL SILVER",
            "interval": 1,
            "previousWin": 1990,
            "followWin": 1991
        }
    ],
    "max": [
        {
            "name": "MATTHEW VAUGHN",
            "interval": 13,
            "previousWin": 2002,
            "followWin": 2015
        }
    ]
}
```
Uso
====

Executando a aplicação por linha de comando
-------------------------------------------

Rodar testes de integração:

>     mvn clean test

Rodar aplicação:

>     mvn clean compile exec:java

Obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que obteve dois prêmios mais rápido:

>     curl -i http://localhost:8080/texoit/producers

Executando a aplicação pela IDE
-------------------------------------------

>     Selecionar o projeto --> Menu Run --> Run As --> Java Application
>     Selecionar o projeto --> Menu Run --> Run As --> JUnit Test

Recursos
-------------------------------------------
Os recursos podem ser acessados via

-   <http://localhost:8080/texoit/producers>

### Funcionalidades

- [x] Obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que obteve dois prêmios mais rápido;

