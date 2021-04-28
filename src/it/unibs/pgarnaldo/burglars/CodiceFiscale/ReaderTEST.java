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

    public static void main(String[] args) throws XMLStreamException {

        ArrayList<Persona> persone = XMLReader.readPersone(CFConstants.FILE_PERSONE);

        for (Persona p: persone) {
            System.out.println("Id: " + p.getId());
            System.out.println("Nome: " + p.getNome());
            System.out.println("Cognome: " + p.getCognome());
            System.out.println("Sesso: " + p.getSesso());
            System.out.println("Comune di nascita: " + p.getComune());
            System.out.println("Data di nascita: " +p.getData());
            System.out.println(CFConstants.DIV);
        }

        System.out.println(CFConstants.DIV);
        System.out.println(CFConstants.DIV);


        HashMap<String, String> comuni = XMLReader.readComuni(CFConstants.FILE_COMUNI);
        Set<String> keys = comuni.keySet();

        for (String key : keys) {
            System.out.println("comune: " + key);
            System.out.println("codice: " + comuni.get(key));
            System.out.println(CFConstants.DIV);
        }

        System.out.println(CFConstants.DIV);
        System.out.println(CFConstants.DIV);

        ArrayList<String> cfs = XMLReader.readTag("codice", CFConstants.FILE_CODICI);

        for (String cf : cfs) {
            System.out.println("codice fiscale: " + cf);
            System.out.println(CFConstants.DIV);
        }
    }
}
