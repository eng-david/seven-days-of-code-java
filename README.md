# 7 Days Of Code - Java

<p align='center'><img src='assets/logo.svg' width=240 alt='logo'/></p>

![Development Status Badge](https://img.shields.io/badge/Status-Em%20Desenvolvimento-orange)

## Resumo do projeto:
Usando apenas código Java, consumir uma API para pesquisar os top 250 filmes da lista do ~~IMDB~~ TMDB e criar um HTML com os resultados JSON devolvidos.


## Stack:
- `Linguagem Java`
- `Maven`
- `JSON`
- `TMDB API`

# Dia 1:
- [X] Criar uma conta no ~~IMDB~~ TMDB para ter a chave de acesso ao serviço (apiKey).
- [X] Criar o código Java que executará uma requisição HTTP do tipo GET.
- [X] Executar a requisição e pegar a resposta (o JSON)
- [X] Imprimir o corpo da resposta no console

<p align='center'><img src='assets/print-dia1.png' alt='print dia 1'/></p>

# Dia 2:
- [x] Extrair o título do filme e a URL da imagem a partir da resposta JSON utilizando apenas as bibliotecas da JRE e Regular Expressions.
- [x] Imprimir esses atributos no console.

<p align='center'><img src='assets/print-dia2.png' alt='print dia 1'/></p>

# Dia 3:
- [x] Iniciar uma modelagem melhor do código através de uma classe Movie.
- [X] A classe Movie terá os atributos: titulo, url de imagem, nota e ano de lançamento.