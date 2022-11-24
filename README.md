# 7 Days Of Code - Java

<p align='center'><img src='assets/logo.svg' width=240 alt='logo'/></p>

![Development Status Badge](https://img.shields.io/badge/Status-Em%20Desenvolvimento-orange)

## Resumo do projeto:
Usando apenas código Java, consumir uma API para pesquisar os top 250 filmes da lista do ~~IMDB~~ TMDB e criar um HTML com os resultados JSON devolvidos.


## Stack:
- `Linguagem Java`
- `JSON`
- `TMDB API`

# Dia 1:
- [X] Criar uma conta no ~~IMDB~~ TMDB para ter a chave de acesso ao serviço (apiKey).
- [X] Criar o código Java que executará uma requisição HTTP do tipo GET.
- [X] Executar a requisição e pegar a resposta (o JSON)
- [X] Imprimir o corpo da resposta no console

Utilizando apenas as bibliotecas do pacote `java.net.http` foi feita a requisição a API do TMDB e recuperado o JSON da lista dos melhores filmes:

<p align='center'><img src='assets/print-dia1.png' alt='print dia 1'/></p>

# Dia 2:
- [x] Extrair o título do filme e a URL da imagem a partir da resposta JSON utilizando apenas as bibliotecas da JRE e Regular Expressions.
- [x] Imprimir esses atributos no console.

<p align='center'><img src='assets/print-dia2.png' alt='print dia 1'/></p>

# Dia 3:
- [x] Iniciar uma modelagem melhor do código através de uma classe Movie.
- [X] A classe Movie terá os atributos: titulo, url de imagem, nota e ano de lançamento.

Para encapsular os dados de cada filme foi criada uma classe do tipo Record.

# Dia 4:
- [X] Gerar uma página HTML a partir da lista de objetos (filmes) existentes, onde será possível ver as informações sobre o filme, incluindo o pôster.

Foi criada a classe HTMLGenerator que recebe no construtor um objeto Writer e um método chamado generate que recebe a lista de filmes e através do Writer gera o arquivo HTML com os dados de todos os filmes.

<p align='center'><img src='assets/print-dia4.png' alt='print dia 1'/></p>

# Dia 5:
- [X] Encapsular a chamada da API dentro de uma nova classe.
- [X] Criar uma nova classe para fazer o parseamento do Json.

Foi criada uma nova classe chamada `TmdbApiClient` que recebe no seu construtor um String da chave da API, e contém o método getBody() que faz a requisição HTTP e devolve o JSON.<br>
Também foi criada a classe `TmdbJsonParser` que recebe no seu construtor o JSON e contém o método parse() que devolve o List&lt;Movie&gt; relativo a esse JSON.<br>
Dessa forma utilizando o conceito de encapsulamento foi possível reduzir a complexidade da classe principal e tornar o código mais legível.

# Dia 6:
- [X] Criar uma nova interface que irá definir o comportamento comum de um conteúdo.
- [X] Criar uma interface para Json Parser.
- [X] Criar uma interface chamada APIClient com o método getBody().
- [X] Desenvolver um novo cliente de API e Json Parser com base nas interfaces criadas e consumir a API da Marvel.

Foram criadas as classes `MarvelApiClient` e `MarvelJsonParser` semelhantes as existentes para o TMDB. Dessa forma em posse das devidas chaves de API foi possível gerar a pagina HTML a partir do conteúdo consumido da API da Marvel.

<p align='center'><img src='assets/print-dia5.png' alt='print dia 1'/></p>