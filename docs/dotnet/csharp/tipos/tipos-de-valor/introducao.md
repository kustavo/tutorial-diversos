# Introdução

Tipos de valor e tipos de referência são as duas categorias principais de tipos C#. Uma variável de um tipo de valor contém uma instância do tipo. Isso difere de uma variável de um tipo de referência, que contém uma referência a uma instância do tipo.

Variáveis de tipos de referência armazenam referências em seus dados (objetos) enquanto que variáveis de tipos de valor contém diretamente seus dados. Com tipos de referência, duas variáveis podem fazer referência ao mesmo objeto; portanto, operações em uma variável podem afetar o objeto referenciado pela outra variável. Com tipos de valor, cada variável tem sua própria cópia dos dados e não é possível que as operações em uma variável afetem a outra.

Por padrão, na atribuição, passando um argumento para um método e retornando um resultado do método, os valores das variáveis ​​são copiados. No caso de variáveis ​​de tipo de valor, as instâncias de tipo correspondentes são copiadas. O exemplo a seguir demonstra esse comportamento:
