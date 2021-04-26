package it.unibs.pgarnaldo.burglars.CodiceFiscale;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;

public class ReaderTEST {
    public static void main(String[] args) throws XMLStreamException {
        String filename = "inputFiles/inputPersone.xml";
        ArrayList<Persona> persone = XMLReader.readPersone(filename);
        for (Persona p: persone) {
            System.out.println("Id: " + p.getId());
            System.out.println("Nome: " + p.getNome());
            System.out.println("Cognome: " + p.getCognome());
            System.out.println("Sesso: " + p.getSesso());
            System.out.println("Comune di nascita: " + p.getComune());
            System.out.println("Data di nascita: " +p.getData());
            System.out.println("===================================");
        }
    }
}
