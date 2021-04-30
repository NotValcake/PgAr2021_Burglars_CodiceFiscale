package it.unibs.pgarnaldo.burglars.CodiceFiscale;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import javax.xml.stream.XMLStreamConstants;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * classe per la lettura dei dati di input da file .xml
 * @author burglars
 */
public class XMLReader {

    private static final String NOME = "nome";
    private static final String PERSONA = "persona";
    private static final String COGNOME = "cognome";
    private static final String SESSO = "sesso";
    private static final String COMUNE_NASCITA = "comune_nascita";
    private static final String CODICE = "codice";
    private static final String DATA_NASCITA = "data_nascita";
    private static final String INIT_ERROR = "Errore nell'inizializzazione del reader:";


    /**
     * Funzione che esegue il parsing di un file in input alla ricerca di dati personali
     *
     * @param input_file file di cui eseguire il parsing
     * @return ArrayList contenente tutte le persone i cui dati eerano contenuti in input_file
     * @throws XMLStreamException bho, però avete detto che butta un casino di eccezioni e io mi fido...
     */
    public static ArrayList<Persona> readPersone(String input_file) throws XMLStreamException {

        ArrayList<Persona> persone = new ArrayList<>();

        XMLStreamReader reader = streamReaderInit(input_file);
        Persona p = null; //crea una nuova persona di appoggio inizialmente null

        while (reader.hasNext()) {
            if (reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
                switch (reader.getLocalName()) {
                    case PERSONA:
                        if (p == null)//controlla se p non è stata inzializzata e in tal caso la inizializza con una nuova istanza di Persona
                            p = new Persona(); //crea una nuova persona di appoggio e ne raccoglie i dati
                        p.setId(Integer.parseInt(reader.getAttributeValue(0)));//legge l'attributo "id" del tag persona, lo converte in Integer e lo unboxa nell'attributo id della classe persona
                        break;
                    case NOME:
                        if (p == null)//se, per qualche oscuro motivo p non è stata inizializzata, la inizializzo
                            p = new Persona();
                        reader.next();
                        p.setNome(reader.getText().strip());
                        break;
                    case COGNOME:
                        if (p == null)
                            p = new Persona();
                        reader.next();
                        p.setCognome(reader.getText().strip());
                        break;
                    case SESSO:
                        if (p == null)
                            p = new Persona();
                        reader.next();
                        p.setSesso(reader.getText().strip().toCharArray()[0]);
                        break;
                    case COMUNE_NASCITA:
                        if (p == null)
                            p = new Persona();
                        reader.next();
                        p.setComune(reader.getText().strip());
                        break;
                    case DATA_NASCITA:
                        if (p == null)
                            p = new Persona();
                        reader.next();
                        ArrayList<Integer> data_nascita = parseDate(reader.getText());
                        p.setData(data_nascita);
                        persone.add(p);//aggiunge la persona appena creata all'Arraylist
                        p = null;//resetto la variabile di appoggio
                        break;
                }
            }
            reader.next();
        }
        return persone;
    }

    /**
     * Classe che legge il file di input contenente l'accoppiata comune-codice
     *
     * @param input_file file a cui leggere i dati
     * @return HashMap contenente le coppie nome (key)-codice (value) per i comuni
     * @throws XMLStreamException
     */
    public static HashMap<String, String> readComuni(String input_file) throws XMLStreamException {

        HashMap<String, String> comuni = new HashMap<>();
        String[] comune_temp = new String[2];

        XMLStreamReader reader = streamReaderInit(input_file);
        while (reader.hasNext()) {
            if (reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
                switch (reader.getLocalName()) {
                    case NOME:
                        reader.next();
                        comune_temp[0] = reader.getText();
                        break;
                    case CODICE:
                        reader.next();
                        comune_temp[1] = reader.getText();
                        comuni.put(comune_temp[0], comune_temp[1]);
                        break;
                }
            }
            reader.next();
        }

        return comuni;
    }



    /**
     * Legge e memorizza in un array di stringhe tutti i contenuti di un tag specifico
     *
     * @param tag        il tag che vogliamo leggere
     * @param input_file il file da cui vogliamo leggere
     * @return arraylist contenente il testo di tutte le occorrenze del tag specificato
     * @throws XMLStreamException
     */
    public static ArrayList<String> readTag(String tag, String input_file) throws XMLStreamException {

        ArrayList<String> readings = new ArrayList<>();

        XMLStreamReader reader = streamReaderInit(input_file);

        while (reader.hasNext()) {
            if (reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
                if (reader.getLocalName().equals(tag)) {
                    reader.next();
                    readings.add(reader.getText());
                }
            }
            reader.next();
        }
        return readings;
    }

    /**
     * riceve una stringa contenente una data e ritorna una array di interi contente anno, mese, giorno
     *
     * @param date stringa nel formato aaaa-MM-gg da cui estrarre la data
     * @return un array di interi {aaaa, MM, gg}
     */
    private static ArrayList<Integer> parseDate(String date) {

        ArrayList<Integer> parsed_date = new ArrayList<>();
        char[] date_array = date.toCharArray();

        parsed_date.add(Integer.parseInt(String.copyValueOf(date_array, 0, 4)));
        parsed_date.add(Integer.parseInt(String.copyValueOf(date_array, 5, 2)));
        parsed_date.add(Integer.parseInt(String.copyValueOf(date_array, 8, 2)));

        return parsed_date;
    }

    /**
     * classe di utilità che inizializza un nuovo XMLStreamReader
     *
     * @param input_file file di input da leggere
     * @return l'istanza del nuovo StreamReader inizializzato
     */
    private static XMLStreamReader streamReaderInit(String input_file) {
        XMLInputFactory factory = null;
        XMLStreamReader reader = null;
        try {
            factory = XMLInputFactory.newInstance();
            reader = factory.createXMLStreamReader(input_file, new FileInputStream(input_file));
        } catch (Exception e) {
            System.out.println(INIT_ERROR);
            System.out.println(e.getMessage());
        }
        return reader;
    }


}
