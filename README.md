# Desafio Globoplay

Um aplicativo sobre filmes, usando a API do TheMovieDB.

* Acesse o video demonstração no [YouTube](https://youtu.be/yxAuCCXpqo4)

## Passos para implementação

* Primeiro, clone o projeto `https://github.com/reis756/globoplay-desafio-mobile.git`
* Acesse [`API TheMovieDB`](https://www.themoviedb.org/settings/api)
* Crie uma chave de API.
* Crie uma conta na plataforma e depois acesse [`API TheMovieDB`](https://developer.themoviedb.org/reference/account-details) para acessar sua account_id.
* Cole a chave de API e a account_id no arquivo `local.properties` seguindo o padrão abaixo:

```
API_ACCESS_TOKEN=Bearer <chave_gerada>
ACCOUNT_ID=<account_id>
```

## Arquitetura

Arquitetura pensada visando a facilidade na manutenção futura e escalabilidade. App modularizado, evitando a hiper dependência das camadas do aplicativo. Seguindo o padrão Clean Architecture + MVVM, recomendado por [`Developer Android`](https://developer.android.com/jetpack/guide?hl=pt-br). Segue abaixo o esquema de pastas:

![`Esquema de pastas`](https://github.com/reis756/globoplay-desafio-mobile/blob/master/app/path_schemme.png)

## Bibliotecas e APIs

* ***Linguagem Kotlin***
* ***Material Design 3***
* ***Koin*** - para injeção de dependência
* ***Retrofit 2*** - para requisições a API
* ***Core Splashscreen*** - para melhor funcionamento da Splashscreen em todas as versões do android 
* ***Paging 3*** - para paginação de resultados
* ***Glide*** - para carregamento de imagens
* ***Shimmer Layout*** - para animação de loading
* ***Youtube Player*** - para player de vídeo do Youtube
* ***JUnit*** - para testes unitários
* ***Turbine*** - para testes em flows
* ***Expresso*** - para testes instrumentados

## Requisitos Essenciais

* Tela de splash `OK`;
* Listagem dos filmes `OK`;
* Minha lista `OK`;
* Detalhes do filme `OK`;
* Favoritar/Desfavoritar filmes `OK`;
* Layout estruturado `OK`;
* Tratamento de erro `OK`;

## Requisitos Plus

* Filtros `NOT OK - por falta de entendimento da API`;
* Busca `OK`;
* Paginação `OK`;
* Animações `OK`;
* Testes unitários `OK - porém necessário aumentar a cobertura de testes`;
* Testes instrumentados `NOT OK - pelo tempo`;
* Testes funcionais `NOT OK`;
* Pipeline Automatizado `NOT OK`;
* Player de vídeo `OK`;

## ToDo's

* Aumentar a cobertura de testes unitários e testes instrumentados
* Implementar lint
* Implementar detekt
* Implementar Firebase (Crashlytics, Analytics, Big Query)
* Implementar CI/CD com Fastlane, Github Actions