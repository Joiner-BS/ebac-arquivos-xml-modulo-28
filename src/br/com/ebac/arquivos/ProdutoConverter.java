package br.com.ebac.arquivos;

// Converte uma linha no formato id;nome;quantidade em um objeto Produto.
public final class ProdutoConverter {
    public Produto converter(String linha) {
        // O limite -1 preserva campos vazios, inclusive no final da linha.
        String[] campos = linha.split(";", -1);
        if (campos.length != 3) {
            throw new IllegalArgumentException("Use três campos separados por ponto e vírgula.");
        }
        try {
            int id = Integer.parseInt(campos[0].strip());
            String nome = campos[1].strip();
            int quantidade = Integer.parseInt(campos[2].strip());
            return new Produto(id, nome, quantidade);
        } catch (NumberFormatException erro) {
            throw new IllegalArgumentException("ID e quantidade devem ser números inteiros.", erro);
        }
    }
}
