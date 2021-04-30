package it.unibs.pgarnaldo.burglars.CodiceFiscale;

/**Benvenuti nella classe main
 * @author burglars
 **/


import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.Collections;

public class CodiciFiscaliMain {

    public static void main(String[] args) throws XMLStreamException {

        ArrayList<String> cf = XMLReader.readTag(CFConstants.TAG_CODICE, CFConstants.FILE_CODICI);//legge i codici fiscali dal relativo file
        ArrayList<Persona> persone = XMLReader.readPersone(CFConstants.FILE_PERSONE);//legge e crea le persone a partire dal file

        ArrayList<String> invalidi = invalidi(cf);
        ArrayList<String> presenti = accoppiati(cf, persone);
        ArrayList<String> spaiati = cf;

        XMLWriter writer = new XMLWriter(CFConstants.FILE_OUTPUT);
        writer.getWriter().writeStartElement("output");
        writer.scriviPersone(persone, presenti);
        writer.getWriter().writeEndElement();
        writer.getWriter().writeStartElement("codici");
        writer.scriviCodici("invalidi", invalidi);
        writer.scriviCodici("spaiati", spaiati);
        writer.endWriter();
    }

    /**
     * confronta una Lista di codici fiscali con un elenco di persone
     *
     * @param cf ArrayList di stringhe contenenti i codici fiscali da controllare
     * @return una Arraylist contenente i CodiciFiscali che hanno una corrispondenza
     * @throws XMLStreamException
     */
    public static ArrayList<String> accoppiati(ArrayList<String> cf, ArrayList<Persona> persone) throws XMLStreamException {

        ArrayList<String> accoppiati = new ArrayList<>(); // i codici fiscali creati

        for (int i = persone.size() - 1; i >= 0; i--) {
            CodiceFiscale cod_fiscale = new CodiceFiscale(persone.get(i)); //inizializzo una nuova istanza di codice fiscale relativo alla i-esima persona
            if (cf.contains(cod_fiscale.getCodFiscale())) { //controllo se tale codice fiscale è contenuto nella lista di codici
                accoppiati.add(cod_fiscale.getCodFiscale());//lo aggiungo alla lista di codici fiscali corretti
                cf.remove(cod_fiscale.getCodFiscale()); //lo rimuovo dalla lista di cf da controllare
            } else {
                accoppiati.add(CFConstants.ASSENTE); //se il codice fiscale della persona non è presente segna ASSENTE nella posizione corrispondente
            }
        }
        //per fare in modo che la corrispondenza di ordine tra persona in persone e codice fiscale in accoppiati sia valida bisogna invertire l'arraylist accoppiati
        Collections.reverse(accoppiati);
        return accoppiati;
    }

    /**
     * controlla i codici fiscali errati
     *
     * @param cf un ArrayList di stringhe contenenti i cf da controllare
     * @return un ArrayList contenente i codici invalidi
     */
    public static ArrayList<String> invalidi(ArrayList<String> cf) {

        ArrayList<String> invalidi = new ArrayList<>(); //genera l'array contenente i codici invalidi

        for (int i = cf.size() - 1; i >= 0; i--) {
            CodiceFiscale codice = new CodiceFiscale(cf.get(i)); //genera un nuovo codice fiscale
            if (!codice.isValid()) { //se il codice non è valido
                invalidi.add(codice.toString()); //lo aggiunge a invalidi
                cf.remove(codice.toString()); //rimuove il codice fiscale errato da quelli da controllare
            }
        }
        return invalidi;
    }
}
