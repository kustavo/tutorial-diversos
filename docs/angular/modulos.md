# Módulos

!!! warning "Página em construção"

    Página em construção!

No contexto do Angular um módulo é a forma de agrupar componentes, diretivas, pipes e serviços que são relacionados. Conjuntos de módulos são usados para formar a aplicação. Um módulo pode esconder ou exportar um componente, diretiva, serviço ou pipe. Componentes exportados podem ser usados por outros módulos, já aqueles que são escondidos pelo módulo só podem ser usados por ele mesmo.

Os módulos no Angular são classes marcadas pelo decorador `@NgModule` que configura o injetor e o compilador e ajuda a organizar coisas relacionadas. Este decorator usa um objeto de metadados com propriedades que definem o módulo. As principais propriedades são:

`imports`
: Importa outros **módulos** que possuem componentes, diretivas e *pipes* necessários por componentes declarados nesse módulo.

`declarations`
: Declara os **componentes**, **diretivas** e ***pipes*** que fazem parte do módulo.

`exports`
: Define quais **componentes**, **diretivas** e ***pipes*** ficarão disponíveis (públicos) para outros módulos.

`providers`
: Tornam os **serviços** conhecidos pelo container de DI (injeção de dependência). Realizam a injeção de dependência dos serviços exigidos por componentes, diretivas, *pipes* presentes no módulo. Se for o módulo root, os serviços ficam disponíveis para toda a aplicação.

`bootstrap`
: A *view* principal do aplicativo, chamada de **componente raiz**, que hospeda todas as outras *views* do aplicativo. Apenas o `@NgModule` *root* (`AppModule`) deve definir a propriedade `bootstrap`.

## Lazy Loading

Uma vantagem de dividir a aplicação em módulos é a possibilidade de fazer o carregamento de determinados módulos somente quando houver necessidade. Quando se usa módulos *Lazy Loading*, o carregamento só é feito quando o usuário navega para a rota do respectivo módulo.



Continuar!!!!!!!!!!!!!!!!!!!!!
https://medium.com/rocketseat/implementando-lazy-loading-no-angular-d8a6541c0580


## Referências

- <https://angular.io/guide/ngmodules>
- <https://medium.com/rocketseat/implementando-lazy-loading-no-angular-d8a6541c0580>
