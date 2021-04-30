
package it.unibs.pgarnaldo.burglars.CodiceFiscale;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class XMLWriter {

    XMLStreamWriter writer;

    public XMLWriter(String output_file){
        this.writer = writerInit(output_file);
    }

    public XMLStreamWriter writerInit(String output_file){

        XMLOutputFactory factory = null;
        XMLStreamWriter writer = null;

        try {
            factory = XMLOutputFactory.newInstance();
            writer = factory.createXMLStreamWriter(new FileOutputStream(output_file), "utf-8");
            writer.writeStartDocument("utf-8", "1.0");
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del writer:");
            System.out.println(e.getMessage());
        }
        return writer;
    }

    //TODO aggiornare
    public void scriviOutput(String opening_tag) throws XMLStreamException {
        writer.writeStartElement(opening_tag);
        writer.writeStartElement("codici");
    }

    public void scriviPersone(ArrayList<Persona> persone, ArrayList<String> cf) throws XMLStreamException {

        writer.writeStartElement("persone");
        writer.writeAttribute("numero", Integer.toString(persone.size()));//inserisce numero di persone

        for (int i = 0; i < persone.size(); i++) {
            scriviPersona(persone.get(i));
            writer.writeStartElement("codice_fiscale");
            writer.writeCharacters(cf.get(i));
            writer.writeEndElement();
            writer.writeEndElement();
        }
    }

    public void scriviPersona(Persona p) throws XMLStreamException {
        writer.writeStartElement("persona");
        writer.writeAttribute("id", Integer.toString(p.getId()));
        writer.writeStartElement("nome");
        writer.writeCharacters(p.getNome());
        writer.writeEndElement();
        writer.writeStartElement("cognome");
        writer.writeCharacters(p.getCognome());
        writer.writeEndElement();
        writer.writeStartElement("sesso");
        writer.writeCharacters(Character.toString(p.getSesso()));
        writer.writeEndElement();
        writer.writeStartElement("comune_nascita");
        writer.writeCharacters(p.getComune());
        writer.writeEndElement();
        writer.writeStartElement("data_nascita");
        writer.writeCharacters(String.format("%4d-%02d-%02d" ,p.getData().get(0), p.getData().get(1) , p.getData().get(2)));
        writer.writeEndElement();
    }

    public void scriviCodici(String tag, ArrayList<String> codici) throws XMLStreamException {
        //TODO spostare:
        writer.writeStartElement(tag);
        writer.writeAttribute("numero", Integer.toString(codici.size()));
        for (String cf:codici) {
            writer.writeStartElement("codice");
            writer.writeCharacters(cf);
            writer.writeEndElement();
        }
        writer.writeEndElement();
    }

    public void endWriter() throws XMLStreamException {
        this.writer.flush();
        this.writer.close();
    }
}
