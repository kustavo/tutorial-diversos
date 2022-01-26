# Modificadores de acesso

A visibilidade de uma classe, método, variável ou propriedade nos informa como este item pode ser acessado.

Os modificadores de acesso são palavras-chave usadas para especificar a visibilidade ou acessibilidade declarada de um membro ou de um tipo.

Os seguintes níveis de acessibilidade podem ser especificados usando os modificadores de acesso:

- **public**: O acesso não é restrito.
- **private**: Pode ser acessado somente pelo código na mesma classe ou struct.
- **protected**: Pode ser acessado somente pelo código na mesma classe ou em uma classe derivada dessa classe.
- **internal**: Pode ser acessado por qualquer código no mesmo assembly, mas não de outro assembly.
- **protected internal**: Pode ser acessado por qualquer código no assembly no qual ele é declarado ou de uma classe derivada em outro assembly.
- **private protected**: Pode ser acessado somente dentro de seu assembly de declaração, pelo código na mesma classe ou em um tipo que é derivado dessa classe.

| Local de quem chama | public | protected internal | protected | internal | private protected | private |
| --- | --- | --- | --- | --- | --- | --- |
| Dentro da classe |✔️️|✔️|✔️|✔️|✔️|✔️|
| Classe derivada (mesmo assembly)|✔️|✔️|✔️|✔️|✔️|❌|
| Classe não derivada (mesmo assembly)|✔️|✔️|❌|✔️|❌|❌|
| Classe derivada (assembly diferente)|✔️|✔️|✔️|❌|❌|❌|
| Classe não derivada (assembly diferente)|✔️|❌|❌|❌|❌|❌|

!!! note "Notas"
    Uma boa prática é limitar a visibilidade de seus tipos e/ou membros restringindo-os a apenas a quem precisa realmente acessar o tipo ou membro. Quanto menos visibilidade houver, menor será a probabilidade de ocorrer uma alteração indesejada, ou seja, menor será a chance de outras partes do sistema mudar quando você fizer uma atualização. Quanto menos lugares puderem acessar um tipo ou membro, menos lugares você terá que modificar, para fazer uma alteração no seu código.

Os modificadores de acesso **não são permitidos em namespaces**. Namespaces não têm nenhuma restrição de acesso.

Os tipos de nível superior, que não estão aninhados em outros tipos, podem ter apenas a acessibilidade `internal` ou `public`. A acessibilidade de padrão para esses tipos, se não definida, é `internal`.

Tipos aninhados, que são membros de outros tipos, podem ter acessibilidades declaradas conforme indicado na tabela a seguir.

| Tipo aninhado | Acessibilidade padrão | Acessibilidades permitidas |
| --- | --- | --- |
| enum | `public` | nenhum |
| class | `private` | `public`, `protected`, `internal`, `private`, `protected internal`, `private protected` |
| interface | `public` | `public`, `protected`, `internal`, `private*`, `protected internal`, `private protected` |
| struct | `private` | `public`, `internal`, `private`|

\* Uma interface aninhada com acessibilidade `private` deve ter uma implementação *default*.

A acessibilidade de um tipo aninhado depende de seu domínio de acessibilidade, que é determinado pela acessibilidade declarada do membro e pelo domínio de acessibilidade do tipo imediatamente contido. No entanto, o domínio de acessibilidade de um tipo aninhado não pode exceder o do tipo que o contém.

## Referências

- <http://www.macoratti.net/19/07/c_tpvisib1.htm>
- <https://docs.microsoft.com/pt-br/dotnet/csharp/programming-guide/classes-and-structs/access-modifiers>
- <https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/keywords/accessibility-levels>
