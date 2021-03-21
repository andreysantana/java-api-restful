API RESTful
==============================

Este exemplo demonstra uma aplicação que possibilita buscar o produtor com maior intervalo entre dois prêmios consecutivos, e o que
obteve dois prêmios mais rápido; 


Configurando a aplicação
------------------------


Exemplo de Resposta
---------------

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

Executando a aplicação por linha de comando
-------------------------------------------

Rodar testes de integração:

>     mvn clean compile exec:java

Rodar aplicação:

>     mvn clean compile exec:java

Obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que obteve dois prêmios mais rápido:

>     curl -i http://localhost:8080/texoit/producers

Executando a aplicação pela IDE
-------------------------------------------

>     Selecionar o projeto --> Menu Run --> Run As --> Java Application
>     Selecionar o projeto --> Menu Run --> Run As --> JUnit Test


Os recursos podem ser acessados via

-   <http://localhost:8080/texoit/producers>