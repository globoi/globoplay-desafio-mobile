
# Globoplay Recrutamento 

---
![JPG](https://lh3.googleusercontent.com/62viiAWxbh_4QQdbdZOeO53yDsq1jZu_KyQstDpbXgTryhTIjrPzW9nr0eFkMuoxs6wx=s180)


## Bem vindo

Obrigado por participar do desafio do Globoplay! Estamos muito contentes pelo seu primeiro passo para fazer parte de um time excepcional. Você deverá criar um aplicativo sobre filmes, usando a [API](https://developers.themoviedb.org/3/getting-started/introduction) do [TheMovieDB](https://www.themoviedb.org/?language=en).
Para termos um layout minimamente definido, anexamos alguns assets que vão te ajudar a desenvolver esse app:

- [ScreenShots](assets/screenshots).
- [Ícones](assets/icons)


Bem, sou Renata Cristovam e topei o desafio de implementar esse App! 
Observação: Os comentários relacioandos a minha implementação seguem sempre após   "------>""
Obrigada pela oportunidade! Amei o desafio. E planejo ainda continuar seguindo com mais implementações nesse projeto, mesmo após o teste.


Pense no desafio como uma oportunidade de mostrar todo o seu conhecimento. E faça com calma, você tem uma semana para entregar! Sua avaliação será baseada nos seguintes tópicos:

- Arquitetura     
----->  Padrão de arquitetura  MVVM (Model View ViewModel) junto com livedata.

- Consumo de APIs.  

-----> 1. Get List: /movie/{movie_id}/lists;  

-----> 2. Get Details: /movie/{movie_id};  

-----> 3. Get Popular: /movie/popular;  

-----> 4. Get Images: /movie/{movie_id}/images;   

-----> 5. Get Vídeos: /movie/{movie_id}/videos;   

-----> 6. /movie/{movie_id}/credits;  


- GIT 
-----> Realizado todo o processo de acordo com o pedido de entrega.

- Layout's 
-----> ConstraintLayout(em maior parte) e LinaerLayout. Uso do ViewFlipper que éuma subclasse de ViewAnimator que é usada para alternar entre visualizações. É um elemento de widget de transição que nos ajuda a adicionar transição nas visualizações (ou seja, mudar de uma visualização para outra). Eu usei para alternar a visualização da Lista da RecyclerView, do progressBar e do componente de erro que eu criei.

-----> Fluxo de navegação - Uso de BottomNavigator usando navigation, Navegação entre Fragments, navegação entre Activities e uso do ViewPager para navegar entre os Fragments. 

Não existe nenhum pré-requisito, portanto sinta-se a vontande para utilizar qualquer linguagem e usar ou não usar bibliotecas. 
----> Uso da linguagem ofical do Google: Kotlin.

Bibliotecas:  

---->  Glide - Para carregamento de imagem;  

---->  Retrofit - cliente HTTP de tipo seguro para Android e Java, usado para chamada as APIs. 

---->  Converter Gson - usei  como o conversor do retrofit para serialização de Kotlin para JSON e de JSON para kotlin. Com ele uma instância Gson padrão é criada ou  pode ser configurada e passada para a GsonConverterFactory para controlar ainda mais a serialização;  

---->  Okhttp3 - Para print de logs das chamadas aos serviços da API e dos retornos das chamadas aos serviços da API;  

---->  Lifecycle -  Componentes compatíveis com o ciclo de vida realizam ações em resposta a uma mudança no status do ciclo de vida de outro componente, como atividades e fragmentos. Esses componentes ajudam você a produzir códigos mais organizados e, normalmente, mais leves e mais fáceis de manter.  

---->  Room - “Faz parte do Android JetPack” e foi usada para auxiliar na abstração das camadas de banco de dados (SQLite). Usei para salvar dados necessários para serem usados na tela de minha lista do App, na qual são mostrados os filmes favoritos;  

---->  Coroutines - o uso delas é recomendada para programação assíncrona no Android. Os recursos notáveis incluem o seguinte: Leve, menos vazamento de memória, suporte a cancelamento integrado e integração com o Jetpack;  

---->  viewBinding - Usada para a vinculação de visualizações. É um recurso que facilita a programação de códigos que interagem com visualizações. Quando a vinculação de visualizações é ativada em um módulo, ela gera uma classe de vinculação para cada arquivo de layout XML presente nesse módulo. A instância de uma classe de vinculação contém referências diretas a todas as visualizações que têm um código no layout correspondente;  

----> Mockito - Para teste Unitário;  

----> JUnit - Para testes Unitário.  

Sabemos que testes não é um tópico que todos dominam ou tem conhecimento, por isso aceitamos desafios de todos os perfis e diferentes níveis de conhecimento técnico. Mas nos preocupamos com a qualidade, saúde e evolução do produto e por isso acreditamos bastante em testes automatizados.

# Features

------

### Essencial:

- Tela de splash;  
----> Feita!  

- Listagem dos filmes;   
----> Feita!  

- Minha lista;     
----> Feita!  

- Detalhes do filme;   
-----> Feito!   

- Favoritar/Desfavoritar filmes;   
----->  Feito!  

- Layout estruturado;      
----> Feito!  

- Tratamento de erro.     
----> Feito usando CoroutineExceptionHandlerPara lidar com exceções lançada por uma co-rotina.  

### Ganha mais pontos se tiver:

- Filtros;
- Busca;
- Paginação;  
 
- Animações;   
----> Feita na tela de Splash.  

- Testes unitários      
-----> Feitos: Usando JUnit e Mockito.  

- Testes instrumentados;
- Testes funcionais;
- Pipeline Automatizado.


### Iremos ficar encantados:  

- Play do vídeo.     
---> Feito. Fiz o redirecionamento do usuário para o Youtube. Eu poderia ter usado componente visual VideoView do android, mas como alguns retornos da api levam para apenas o trailer do filme, achei ideal o YouTube, pois lá o usuário pode pesquisar mais sobre o filme.  


# Exemplos e sugestões

---

Nossos designers elaboraram algumas sugestões de telas e fluxos para guiar você durante o desenvolvimento, portanto fique à vontade para modificar como você quiser.
Para facilitar o processo, existem assets, app icons, ícones e paleta de cores no repositório. Mas se o seu lado designer falar mais alto, pode nos surpreender!

- [Splash](assets/screenshots/splash.png)
- [Grid de filmes](assets/screenshots/home.jpg)
- [Detalhes](assets/screenshots/highlights-details.jpg)
- [Assista Também](assets/screenshots/highlights.jpg)
- [Adicionar aos favoritos](assets/screenshots/highlights-favorited.jpg)
- [Lista de Favoritos](assets/screenshots/my-list.jpg)  

----> Tentei seguir o máximo que consegui durante o tempo o Layout sugerido.

## **Processo de entrega**

---

Após finalizar a implementação do desafio, abra um pull request para este repositório seguindo os passos abaixo:

1. Faça um fork deste repositório, não clone este repositório diretamente;
2. Os commit's e as alterações devem ser feitas no **SEU** fork;
3. Envie um Pull Request;
4. Deixe o fork público para facilitar a inspeção do código.

--->   Feito com Sucesso.


### **ATENÇÃO**

Não faça push diretamente para este repositório!
