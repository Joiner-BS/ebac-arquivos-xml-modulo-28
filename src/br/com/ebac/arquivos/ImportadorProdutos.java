package br.com.ebac.arquivos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.xml.stream.XMLStreamException;

public final class ImportadorProdutos {
    public static void main(String[] args) {
        // O argumento opcional permite escolher outro arquivo de entrada.
        Path arquivo = Path.of(args.length == 0 ? "dados/produtos.txt" : args[0]);
        ProdutoConverter converter = new ProdutoConverter();
        int numeroLinha = 0;

        // try-with-resources fecha o leitor mesmo quando ocorre uma falha.
        try (BufferedReader leitor = Files.newBufferedReader(arquivo, StandardCharsets.UTF_8)) {
            System.out.println("<produtos>");
            String linha;
            while ((linha = leitor.readLine()) != null) {
                numeroLinha++;
                // Aceita a marca BOM que alguns editores incluem em arquivos UTF-8.
                if (numeroLinha == 1 && linha.startsWith("\uFEFF")) {
                    linha = linha.substring(1);
                }
                Produto produto = converter.converter(linha);
                System.out.println("    " + produto.toXml());
            }
            System.out.println("</produtos>");
        } catch (IOException erro) {
            System.err.println("Falha ao ler o arquivo " + arquivo.toAbsolutePath() + ": " + erro.getMessage());
            System.exit(1);
        } catch (IllegalArgumentException | XMLStreamException erro) {
            System.err.println("Erro na linha " + numeroLinha + ": " + erro.getMessage());
            System.exit(1);
        }
    }
}
