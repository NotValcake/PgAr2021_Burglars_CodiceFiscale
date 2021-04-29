package it.unibs.pgarnaldo.burglars.CodiceFiscale;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;

public class ClassMain {

    public static void main(String[] args) throws XMLStreamException {

        //TODO ArrayList<String> codici_fiscali = new ArrayList<>();

        ArrayList<String> invalidi = new ArrayList<>();
        ArrayList<String> spaiati= new ArrayList<>();
        ArrayList<String> presenti= new ArrayList<>();

        ArrayList<String> cf = XMLReader.readTag(CFConstants.TAG_CODICE, CFConstants.FILE_CODICI);//legge i codici fiscali dal relativo file
        ArrayList<Persona> persone = XMLReader.readPersone(CFConstants.FILE_PERSONE);//legge e crea le persone a partire dal file

        invalidi = errati(cf);
        presenti = accoppiati(cf, persone);
        spaiati = cf;

        //stampa inizio
        //stampa accoppiati
        //stampa spaiati
        //stampa errati
        //chiudere output stream
    }

    /**
     * confronta una Lista di codici fiscali con un elenco di persone
     * @param cf ArrayList di stringhe contenenti i codici fiscali da controllare
     * @return una Arraylist contenente i CodiciFiscali che hanno una corrispondenza
     * @throws XMLStreamException
     */
    public static ArrayList<String> accoppiati(ArrayList<String> cf, ArrayList<Persona>persone) throws XMLStreamException {


        //TODO String cf_persone;
        ArrayList<String> accoppiati=new ArrayList<>(); // i codici fiscali creati
        //TODO ArrayList<String> spaiati=new ArrayList<>(); // i codici fiscali de

        for(int i=persone.size()-1;i>=0;i--){
            CodiceFiscale cf_persone = new CodiceFiscale(persone.get(i)); //inizializzo una nuova istanza di codice fiscale relativo alla i-esima persona
            if (cf.contains(cf_persone.getCodFiscale())){ //controllo se tale codice fiscale è contenuto nella lista di codici
                accoppiati.add(cf.get(i));//lo aggiungo alla lista di codici fiscali corretti
                cf.remove(i); //lo rimuovo dalla lista di cf da controllare
            }else{
                accoppiati.add(CFConstants.ASSENTE); //se il codice fiscale della persona non è presente segna ASSENTE nella posizione corrispondente
            }
        }
        return accoppiati;
    }

    /**
     * controlla i codici fiscali errati
     * @param cf un ArrayList di stringhe contenenti i cf da controllare
     * @return un ArrayList contenente i codici invalidi
     */

    public static ArrayList<String> errati(ArrayList<String> cf ){

        ArrayList<String> invalidi = new ArrayList<>(); //genera l'array contenente i codici invalidi

        for (int i=cf.size()-1; i>=0;i--){
            CodiceFiscale codice = new CodiceFiscale(cf.get(i)); //genera un nuovo codice fiscale
            if( !codice.isValid()){ //se il codice non è valido
                invalidi.add(cf.get(i)); //lo aggiunge a invalidi
                cf.remove(i); //rimuove il codice fiscale errato da quelli da controllare
            }
        }
        return invalidi;
    }

    
}
