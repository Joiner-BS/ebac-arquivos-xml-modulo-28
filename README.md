# Leitura de arquivo e conversão para XML — Módulo 28

Exercício do curso Desenvolvedor Full Stack Java da EBAC.

## Arquivo base

O arquivo manual `dados/produtos.txt` usa UTF-8, sem cabeçalho, com um produto por linha:

```text
id;nome;quantidade
```

A linha acima descreve a estrutura. O arquivo contém apenas os registros. O ID deve ser inteiro positivo, o nome preenchido e a quantidade um inteiro maior ou igual a zero. O ponto e vírgula é reservado como separador. Linhas vazias e linhas com quantidade diferente de três campos são rejeitadas.

## Execução no IntelliJ IDEA

1. Abra a pasta que contém este README e selecione o JDK 25.
2. Marque `src` como **Sources Root**, caso necessário.
3. Execute o método `main` de `br.com.ebac.arquivos.ImportadorProdutos`.
4. Na configuração de execução, confira se **Working directory** aponta para a pasta do projeto, onde estão `src` e `dados`.

Um caminho alternativo pode ser informado em **Program arguments**. Use aspas se o caminho contiver espaços.

## Execução no Git Bash

A partir da pasta do projeto:

```bash
javac -encoding UTF-8 -d out src/br/com/ebac/arquivos/*.java
java -cp out br.com.ebac.arquivos.ImportadorProdutos
```

Saída esperada:

```xml
<produtos>
    <produto><id>1</id><nome>Teclado mecânico</nome><quantidade>10</quantidade></produto>
    <produto><id>2</id><nome>Mouse &amp; acessórios</nome><quantidade>25</quantidade></produto>
    <produto><id>3</id><nome>Hub USB-C</nome><quantidade>8</quantidade></produto>
</produtos>
```

## Organização

- `Produto` é o POJO com os campos do arquivo, os métodos de consulta e `toXml()`.
- `ProdutoConverter` separa os campos e transforma cada linha em um `Produto`.
- `ImportadorProdutos` lê uma linha por vez com `BufferedReader` e exibe o XML no console.
- A geração do XML usa StAX (`javax.xml.stream`), incluída no próprio Java. Caracteres como `&` são escapados automaticamente.

Se um registro for inválido, o programa informa o número da linha e termina com código 1. Como a saída é progressiva, uma falha pode deixar XML parcial no console. Corrija a linha e execute novamente para obter o documento completo.
