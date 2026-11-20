package br.com.ebac.arquivos;

import java.io.StringWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

// POJO que representa os três campos de cada linha do arquivo.
public final class Produto {
    private final int id;
    private final String nome;
    private final int quantidade;

    public Produto(int id, String nome, int quantidade) {
        if (id <= 0 || quantidade < 0 || nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "Informe ID positivo, nome preenchido e quantidade maior ou igual a zero.");
        }
        this.id = id;
        this.nome = nome.strip();
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String toXml() throws XMLStreamException {
        // StAX faz parte do Java e escapa caracteres como & e < no texto.
        StringWriter texto = new StringWriter();
        XMLStreamWriter xml = XMLOutputFactory.newFactory().createXMLStreamWriter(texto);
        try {
            xml.writeStartElement("produto");

            xml.writeStartElement("id");
            xml.writeCharacters(Integer.toString(id));
            xml.writeEndElement();

            xml.writeStartElement("nome");
            xml.writeCharacters(nome);
            xml.writeEndElement();

            xml.writeStartElement("quantidade");
            xml.writeCharacters(Integer.toString(quantidade));
            xml.writeEndElement();

            xml.writeEndElement();
            xml.flush();
            return texto.toString();
        } finally {
            xml.close();
        }
    }
}
