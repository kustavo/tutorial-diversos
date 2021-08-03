# Javadoc

[TOC]

## Introdução

*Javadoc* é um gerador de documentação criado pela *Sun Microsystems* para documentar a API dos programas em Java, a partir do código-fonte. O resultado é expresso em HTML. É constituído, basicamente, por algumas marcações muitos simples inseridas nos comentários do programa.

Este sistema é o padrão de documentação de classes em Java, e muitas dos IDEs desta linguagem irão automaticamente gerar um *Javadoc* em HTML.

Ele também provê uma API para a criação de *doclets* e *taglets*, que permitem a análise da estrutura de um aplicativo Java. É assim, por exemplo, que o *JDiff* consegue gerar relatórios de alterações feitas entre duas versões de uma API.

## Estrutura de um comentário do Javadoc

A estrutura básica de escrever os comentários é inseri-los em `/ ** ... * /`. As `tags` descritivas devem aparecer na seguinte ordem de prioridade.

| Tag e parâmetro                                                               | Usado                                                                                                        | Aplica em                             |
|-------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------|---------------------------------------|
| @**author** John Smith                                                        | Describes an author.                                                                                         | Class, Interface, Enum                |
| {@**docRoot**}                                                                | Represents the relative path to the generated document's root directory from any generated page.             | Class, Interface, Enum, Field, Method |
| @**version** version                                                          | Provides software version entry. Max one per Class or Interface.                                             | Class, Interface, Enum                |
| @**since** since-text                                                         | Describes when this functionality has first existed.                                                         | Class, Interface, Enum, Field, Method |
| @**see** reference                                                            | Provides a link to other element of documentation.                                                           | Class, Interface, Enum, Field, Method |
| @**param** name description                                                   | Describes a method parameter.                                                                                | Method                                |
| @**return** description                                                       | Describes the return value.                                                                                  | Method                                |
| @**exception** classname description <br /> @**throws** classname description | Describes an exception that may be thrown from this method.                                                  | Method                                |
| @**deprecated** description                                                   | Describes an outdated method.                                                                                | Class, Interface, Enum, Field, Method |
| {@**inheritDoc**}                                                             | Copies the description from the overridden method.                                                           | Overriding Method                     |
| {@**link** reference}                                                         | Link to other symbol.                                                                                        | Class, Interface, Enum, Field, Method |
| {@**linkplain** reference}                                                    | Identical to {@link}, except the link's label is displayed in plain text than code font.                     | Class, Interface, Enum, Field, Method |
| {@**value** #STATIC_FIELD}                                                    | Return the value of a static field.                                                                          | Static Field                          |
| {@**code** literal}                                                           | Formats literal text in the code font. It is equivalent to \<code>{@literal}\</code>.                        | Class, Interface, Enum, Field, Method |
| {@**literal** literal}                                                        | Denotes literal text. The enclosed text is interpreted as not containing HTML markup or nested javadoc tags. | Class, Interface, Enum, Field, Method |
| {@**serial** literal}                                                         | Used in the doc comment for a default serializable field.                                                    | Field                                 |
| {@**serialData** literal}                                                     | Documents the data written by the writeObject( ) or writeExternal( ) methods.                                | Field, Method                         |
| {@**serialField** literal}                                                    | Documents an ObjectStreamField component.                                                                    | Field                                 |
Exemplos de uso do Javadoc.

```java
/**
 * Short one line description.
 * <p>
 * Longer description. If there were any, it would be
 * here.
 * </p>
 * And even more explanations to follow in consecutive
 * paragraphs separated by HTML paragraph breaks.
 *
 * @param  variable Description text text text.
 * @return Description text text text.
 */
public int methodName (...) {
    // method body with a return statement
}

/**
 * Validates a chess move.
 *
 * <p>Use {@link #doMove(int theFromFile, int theFromRank, int theToFile, int theToRank)} to move a piece.
 *
 * @param theFromFile file from which a piece is being moved
 * @param theFromRank rank from which a piece is being moved
 * @param theToFile   file to which a piece is being moved
 * @param theToRank   rank to which a piece is being moved
 * @return            true if the move is valid, otherwise false
 * @since             1.0
 */
boolean isValidMove(int theFromFile, int theFromRank, int theToFile, int theToRank) {
    // ...body
}

/**
 * Moves a chess piece.
 *
 * @see java.math.RoundingMode
 */
void doMove(int theFromFile, int theFromRank, int theToFile, int theToRank)  {
    // ...body
}
```
