
package it.unibs.pgarnaldo.burglars.CodiceFiscale;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class XMLWriter {

    XMLStreamWriter writer;

    public void XMLWriter(String output_file){
        this.writer = writerInit(output_file);
    }

    public XMLStreamWriter writerInit(String output_file){

        XMLOutputFactory xmlof = null;
        XMLStreamWriter xmlw = null;

        try {
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(output_file), "utf-8");
            xmlw.writeStartDocument("utf-8", "1.0");
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del writer:");
            System.out.println(e.getMessage());
        }
        return xmlw;
    }

    public void scriviInizio(String opening_tag){

    }

    public void scriviPersone(ArrayList<Persona> persone, ArrayList<String> cf){
        //stampare persone con rispettivo cf
    }

    public void scriviSpaiati(ArrayList<String> spaiati){

    }

    public void scriviInvalidi(ArrayList<String> invalidi){

    }

    public void endWriter() throws XMLStreamException {
        //operazioni per la chiusura
        this.writer.close();
    }
}
