
package it.unibs.pgarnaldo.burglars.CodiceFiscale;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class XMLWriter {

    public static final String INIT_ERROR = "Errore nell'inizializzazione del writer:";
    XMLStreamWriter writer;

    public XMLWriter(String output_file){
        this.writer = writerInit(output_file);
    }

    public XMLStreamWriter getWriter(){
        return this.writer;
    }

    /**
     * inizializza un nuovo writer con il realtivo OutputStream
     * @param output_file String contenente il nome del file da scrivere
     * @return un nuovo XMLStreamWriter
     */
    public XMLStreamWriter writerInit(String output_file){

        XMLOutputFactory factory = null;
        XMLStreamWriter writer = null;

        try {
            factory = XMLOutputFactory.newInstance();
            writer = factory.createXMLStreamWriter(new FileOutputStream(output_file), "utf-8");
            writer.writeStartDocument("utf-8", "1.0");
        } catch (Exception e) {
            System.out.println(INIT_ERROR);
            System.out.println(e.getMessage());
        }
        return writer;
    }

    /**
     * scrive in output un elenco di persone con il relativo codice fiscale formattato
     * @param persone ArrayList contenente un elenco di Persona
     * @param cf Arraylist di String contenente i codici fiscali
     * @throws XMLStreamException
     */
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

    /**
     * scrive sul i dati di una Persona formattata
     * @param p Persona di cui si vogliono stampare i dati
     * @throws XMLStreamException
     */
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

    /**
     * scrive in output un elenco di codici fiscali formattati, preceduto da un tag
     * @param tag tag che si vuole inserire prima dei codici
     * @param codici ArrayList di Stringhe contenente i codici da stampare
     * @throws XMLStreamException
     */
    public void scriviCodici(String tag, ArrayList<String> codici) throws XMLStreamException {
        writer.writeStartElement(tag);
        writer.writeAttribute("numero", Integer.toString(codici.size()));
        for (String cf:codici) {
            writer.writeStartElement("codice");
            writer.writeCharacters(cf);
            writer.writeEndElement();
        }
        writer.writeEndElement();
    }

    /**
     * chiude lo stream di output
     * @throws XMLStreamException
     */
    public void endWriter() throws XMLStreamException {
        this.writer.writeEndDocument();
        this.writer.flush();
        this.writer.close();
    }
}
