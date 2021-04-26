package it.unibs.pgarnaldo.burglars.CodiceFiscale;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import javax.xml.stream.XMLStreamConstants;
import java.util.ArrayList;
import java.util.HashMap;


public class XMLParser {


    public static final String NOME = "nome";
    public static final String PERSONA = "persona";
    private static final String COGNOME = "cognome";
    private static final String SESSO = "sesso";
    private static final String COMUNE_NASCITA = "comune_nascita";
    private static final String CODICE = "codice";
    private static final String DATA_NASCITA = "data_nascita";
    public static final String INIT_ERROR = "Errore nell'inizializzazione del reader:";


    /**
     * Funzione che esegue il parsing di un file in input alla ricerca di dati personali
     *
     * @param input_file file di cui eseguire il parsing
     * @return ArrayList contenente tutte le persone i cui dati eerano contenuti in input_file
     * @throws XMLStreamException bho, però avete detto che butta un casino di eccezioni e io mi fido...
     */
    public static ArrayList<Persona> readNomi(String input_file) throws XMLStreamException {

        ArrayList<Persona> persone = new ArrayList<Persona>();

        XMLStreamReader reader = streamReaderInit(input_file);
        while (reader.hasNext()) {
            Persona p = new Persona(); //crea una nuova persona di appoggio e ne raccoglie i dati
            if (reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
                switch (reader.getLocalName()) {
                    case PERSONA:
                        /**
                         * legge l'attributo "id" del tag persona, lo converte in Integer e lo unboxa nell'attributo id della classe persona
                         */
                        p.setId(Integer.parseInt(reader.getAttributeValue(0)));
                        break;
                    case NOME:
                        p.setNome(reader.getText().strip());
                        break;
                    case COGNOME:
                        p.setCognome(reader.getText().strip());
                        break;
                    case SESSO:
                        p.setSesso(reader.getText().strip().toCharArray()[0]);
                        break;
                    case COMUNE_NASCITA:
                        p.setComune(reader.getText().strip());
                        break;
                    case DATA_NASCITA:
                        ArrayList<Integer> data_nascita = parseDate(reader.getText());
                        p.setData(data_nascita);
                        break;
                }
            }
            persone.add(p); //aggiunge la persona appena creata all'hashmap
            reader.nextTag();
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

        XMLStreamReader reader = streamReaderInit(input_file);
        while (reader.hasNext()) {

            String[] comune_temp = new String[2];

            if (reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
                switch (reader.getLocalName()) {
                    case NOME:
                        comune_temp[0] = reader.getText();
                        break;
                    case CODICE:
                        comune_temp[1] = reader.getText();
                        break;
                }
            }

            comuni.put(comune_temp[0], comune_temp[1]);

        }

        return comuni;
    }


    /**
     * Legge e memorizza in un array di stringhe tutti i contenuti di un tag specifico
     * @param tag il tag che vogliamo leggere
     * @param input_file il file da cui vogliamo leggere
     * @return arraylist contenente il testo di tutte le occorrenze del tag specificato
     * @throws XMLStreamException
     */
    public static ArrayList<String> readTag(String tag, String input_file) throws XMLStreamException {

        ArrayList<String> readings = new ArrayList<>();

        XMLStreamReader reader = streamReaderInit(input_file);

        while(reader.hasNext()){
            if(reader.getEventType() == XMLStreamConstants.START_ELEMENT){
                if(reader.getLocalName().equals(tag)){
                    readings.add(reader.getText());
                }
            }
        }

        return readings;
    }

    /**
     * riceve una stringa contenente una data e ritorna una array di interi contente anno, mese, giorno
     * @param date stringa nel formato aaaa-MM-gg da cui estrarre la data
     * @return un array di interi {aaaa, MM, gg}
     */
    private static ArrayList<Integer> parseDate(String date){

        ArrayList<Integer> parsed_date = new ArrayList<>();
        char[] date_array = date.toCharArray();

        parsed_date.add(Integer.parseInt(String.copyValueOf(date_array, 0, 4)));
        parsed_date.add(Integer.parseInt(String.copyValueOf(date_array, 5, 2)));
        parsed_date.add(Integer.parseInt(String.copyValueOf(date_array, 7, 2)));

        return parsed_date;
    }

    /**
     * classe di utilità cheinizializza un nuovo XMLStreamReader
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
