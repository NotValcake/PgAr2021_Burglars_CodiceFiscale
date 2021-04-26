package it.unibs.pgarnaldo.burglars.CodiceFiscale;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * classe di test per provare i metodi di XMLreader
 * @author burglars
 */

public class ReaderTEST {

    private static final String DIV = "===================================";
    private static final String FILE_COMUNI = "inputFiles/comuni.xml";
    private static final String FILE_PERSONE = "inputFiles/inputPersone.xml";

    public static void main(String[] args) throws XMLStreamException {

        String filepath = FILE_PERSONE;
        ArrayList<Persona> persone = XMLReader.readPersone(filepath);

        for (Persona p: persone) {
            System.out.println("Id: " + p.getId());
            System.out.println("Nome: " + p.getNome());
            System.out.println("Cognome: " + p.getCognome());
            System.out.println("Sesso: " + p.getSesso());
            System.out.println("Comune di nascita: " + p.getComune());
            System.out.println("Data di nascita: " +p.getData());
            System.out.println(DIV);
        }

        System.out.println(DIV);
        System.out.println(DIV);

        filepath = FILE_COMUNI;

        HashMap<String, String> comuni = XMLReader.readComuni(filepath);
        Set<String> keys = comuni.keySet();

        for (String key : keys) {
            System.out.println("comune: " + key);
            System.out.println("codice: " + comuni.get(key));
            System.out.println(DIV);
        }

        System.out.println(DIV);
        System.out.println(DIV);

        filepath = "inputFiles/codiciFiscali.xml";

        ArrayList<String> cfs = XMLReader.readTag("codice", filepath);

        for (String cf : cfs) {
            System.out.println("codice fiscale: " + cf);
            System.out.println(DIV);
        }
    }
}
